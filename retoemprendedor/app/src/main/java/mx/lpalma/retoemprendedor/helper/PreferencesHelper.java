package mx.lpalma.retoemprendedor.helper;

import android.content.Context;
import android.content.SharedPreferences;

import mx.lpalma.retoemprendedor.models.Usuario;

/**
 * Created by lpalma on 20/08/2016.
 */
public class PreferencesHelper {

    private static final String USER_PREFERENCES = "userPreferences";
    private static final String USER_ID = "id";
    private static final String USER_EMAIL = "userEmail";
    private static final String USER_NOMBRE = "nombre";
    private static final String USER_PASSWORD = "password";

    public PreferencesHelper(){}

    public static void cerrarSesion(Context context){
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(USER_EMAIL);
        editor.remove(USER_NOMBRE);
        editor.apply();
    }

    public static void guardarSesion(Context context, String email, String password, Usuario usuario){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_PASSWORD, password);
        editor.putString(USER_ID, usuario.getId());
        editor.putString(USER_NOMBRE, usuario.getNombre());
        editor.apply();
    }

    public static String getId(Context context){
        SharedPreferences preferences = getSharedPreferences(context);
        final String id = preferences.getString(USER_ID, null);
        return id;
    }

    public static String getPassword(Context context){
        SharedPreferences preferences = getSharedPreferences(context);
        final String password = preferences.getString(USER_PASSWORD, null);
        return password;
    }

    public static String getEmail(Context context){
        SharedPreferences preferences = getSharedPreferences(context);
        final String password = preferences.getString(USER_EMAIL, null);
        return password;
    }


    public static String getNombre(Context context){
        SharedPreferences preferences = getSharedPreferences(context);
        final String password = preferences.getString(USER_NOMBRE, null);
        return password;
    }

    public static boolean isLogin(Context context){
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(USER_EMAIL) &&
                preferences.contains(USER_PASSWORD);
    }

    public static Usuario getUsuario(Context context){
        Usuario usuario = new Usuario();
        SharedPreferences preferences = getSharedPreferences(context);
        usuario.setId(preferences.getString(USER_ID, null));
        usuario.setNombre(preferences.getString(USER_NOMBRE, null));
        usuario.setEmail(preferences.getString(USER_EMAIL, null));
        return usuario;
    }

    public static void updatePreferences(Usuario usuario,Context context){
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(USER_NOMBRE);
        editor.putString(USER_NOMBRE, usuario.getNombre());
        editor.commit();
        editor.apply();
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }
}
