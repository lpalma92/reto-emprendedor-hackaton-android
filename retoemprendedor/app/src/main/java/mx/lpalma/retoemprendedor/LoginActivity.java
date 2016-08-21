package mx.lpalma.retoemprendedor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mx.lpalma.retoemprendedor.helper.JsonParser;
import mx.lpalma.retoemprendedor.helper.PreferencesHelper;
import mx.lpalma.retoemprendedor.models.Usuario;

public class LoginActivity extends AppCompatActivity {

    private LoginTask mLoginTask;

    EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText) findViewById(R.id.email);
        edtPassword =  (EditText) findViewById(R.id.password);
        /*
        if(PreferencesHelper.isLogin(getApplicationContext())){
            mLoginTask = new LoginTask(PreferencesHelper.getEmail(this), PreferencesHelper.getPassword(this));
            mLoginTask.execute((Void) null);
        }
        */
    }


    public void login(View v){
        edtEmail.setError(null);
        edtPassword.setError(null);

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edtPassword.setError(getString(R.string.error_invalid_password));
            focusView = edtPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError(getString(R.string.error_field_required));
            focusView = edtEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            edtEmail.setError(getString(R.string.error_invalid_email));
            focusView = edtEmail;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            mLoginTask = new LoginTask(email, password);
            mLoginTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public class LoginTask extends AsyncTask<Void,Void,Boolean>{

        //http://192.168.100.206:8000/login/movil/{email}/{password}

        private String urlroute = "login/movil/";
        private ProgressDialog progressDialog;
        private StringBuilder result;

        public String email, password;

        public LoginTask(String email,String password){
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setTitle("Iniciando sesión");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            result = new StringBuilder();
            try{
                String surl = Application.url + urlroute + email + "/" + password;
                Log.i("URL", surl);
                URL url =  new URL(surl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result.append(line).append("\n");
                }
                return true;
            }catch (IOException e){
                Log.i("NET",e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean bresult) {
            super.onPostExecute(bresult);
            if(bresult){
                if(JsonParser.respose(result.toString())){
                    Usuario usuario = JsonParser.parseUsuario(result.toString());
                    boolean b = JsonParser.parseProyecto(result.toString(),getApplicationContext());
                    if(usuario != null){
                        mLoginTask = null;
                        PreferencesHelper.guardarSesion(getApplicationContext(),email,password,usuario);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                Log.i("Request", result.toString());
            }
            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }
    }
}
