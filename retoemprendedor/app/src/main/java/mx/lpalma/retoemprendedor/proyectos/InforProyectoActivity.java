package mx.lpalma.retoemprendedor.proyectos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.analytics.ecommerce.Product;

import java.util.EventListener;
import java.util.List;

import mx.lpalma.retoemprendedor.R;
import mx.lpalma.retoemprendedor.db.data.EvaluadorData;
import mx.lpalma.retoemprendedor.db.data.IntegranteData;
import mx.lpalma.retoemprendedor.models.Evaluador;
import mx.lpalma.retoemprendedor.models.Integrante;
import mx.lpalma.retoemprendedor.models.Proyecto;

public class InforProyectoActivity extends AppCompatActivity {

    Proyecto pro;
    private Toolbar toolbar;
    private TextView txtdesc, txtestado, txtcate;
    private TextView integrantes, evaluadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_proyecto);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pro = (Proyecto) getIntent().getExtras().getSerializable("Proyecto");
        setTitle(pro.getNombre());
        txtestado = (TextView)findViewById(R.id.estado);
        txtestado.setText(pro.getEstado());
        txtdesc = (TextView)findViewById(R.id.descripcion);
        txtdesc.setText(pro.getDescripcion());
        txtcate = (TextView)findViewById(R.id.convocatoria);
        txtcate.setText(pro.getConvocatoria());
        integrantes = (TextView)findViewById(R.id.integrantes);
        evaluadores = (TextView) findViewById(R.id.evaluadores);
        List<Integrante> inte = new IntegranteData().getAll(pro.getId(),getApplicationContext());
        String text = "";
        for (Integrante i:inte) {
            text +=  i.getNombre()+ "\n";
        }
        integrantes.setText(text);
        text = "";
        List<Evaluador> e = new EvaluadorData().getAll(pro.getId(),getApplicationContext());
        for (Evaluador i:e) {
            text +=  i.getNombre()+ "\n";
        }
        Log.i("ID", pro.getId());
    }
}
