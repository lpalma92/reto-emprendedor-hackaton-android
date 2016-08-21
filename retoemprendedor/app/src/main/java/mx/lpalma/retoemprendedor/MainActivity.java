package mx.lpalma.retoemprendedor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import mx.lpalma.retoemprendedor.notificacion.ListNotificacionFragment;
import mx.lpalma.retoemprendedor.proyectos.ListProyectosFragment;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    public static final String FRAG ="frag";

    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ProgressDialog progress;

    private Toolbar toolbar;
    private int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = (NavigationView) findViewById(R.id.drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        setupDrawer();
        if(savedInstanceState != null){
            currentFragment = savedInstanceState.getInt(FRAG);
            setFragment(currentFragment);
            checkMenuItem(mDrawer.getMenu().getItem(currentFragment).getItemId());
        }else{
            setFragment(currentFragment);
            checkMenuItem(mDrawer.getMenu().getItem(currentFragment).getItemId());
            //progress = new ProgressDialog(this);
            //progress.setMessage("Descargando datos");
            //progress.show();
            //ObtenerInfoTask obtenerInfoTask = new ObtenerInfoTask(getApplicationContext());
            //obtenerInfoTask.execute((Void)null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAG, currentFragment);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_cerrar_sesion){
            //PreferencesHelper.cerrarSesion(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;
        }else if(item.getItemId() == R.id.action_proyectos) {
            checkMenuItem(item.getItemId());
            setFragment(0);
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else if(item.getItemId() == R.id.action_notificaciones){
            checkMenuItem(item.getItemId());
            setFragment(1);
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        return false;
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.action_drawer_open, R.string.action_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setFragment(int idFragment){
        switch (idFragment){
            case 0:
                setTitle(R.string.title_fragment_proyectos);
                currentFragment = 0;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment, ListProyectosFragment.newInstance(), "Proyecto")
                        .commit();
                break;
            case 1:
                setTitle(R.string.title_fragment_notificaciones);
                currentFragment = 1;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment, ListNotificacionFragment.newInstance(), "Notificacion")
                        .commit();
                break;
        }
    }

    public void checkMenuItem(int menuItemId){
        for(int i = 0;i < mDrawer.getMenu().size();i++){
            if(mDrawer.getMenu().getItem(i).getItemId() == menuItemId)
                mDrawer.getMenu().getItem(i).setChecked(true);
            else
                mDrawer.getMenu().getItem(i).setChecked(false);
        }
    }
}
