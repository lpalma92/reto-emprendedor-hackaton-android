package mx.lpalma.retoemprendedor.notificacion;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.lpalma.retoemprendedor.R;
import mx.lpalma.retoemprendedor.adapters.NotificacionAdapter;
import mx.lpalma.retoemprendedor.adapters.ProyectoAdapter;
import mx.lpalma.retoemprendedor.models.Notificacion;
import mx.lpalma.retoemprendedor.models.Proyecto;
import mx.lpalma.retoemprendedor.proyectos.InforProyectoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListNotificacionFragment extends Fragment {


    private ListView lista;
    private TextView mensaje;

    public ListNotificacionFragment() {
        // Required empty public constructor
    }

    public static ListNotificacionFragment newInstance() {
        ListNotificacionFragment fragment = new ListNotificacionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_list_notificacion, container, false);
        lista = (ListView)view.findViewById(R.id.lst);
        mensaje = (TextView) view.findViewById(R.id.txt_mensaje);
        List<Notificacion> usuarios = new ArrayList<Notificacion>();
        Notificacion p = new Notificacion();
        p.setFecha("ccccc");
        p.setMensaje("ffff");
        usuarios.add(p);
        Log.i("LISTA_USU", usuarios.size() +"");
        if(!usuarios.isEmpty()) {
            ArrayAdapter<Notificacion> adapter = new NotificacionAdapter(getActivity(), usuarios);
            lista.setAdapter(adapter);
            mensaje.setVisibility(View.GONE);
            lista.setVisibility(View.VISIBLE);
        }else{
            lista.setVisibility(View.GONE);
            mensaje.setVisibility(View.VISIBLE);
        }
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Notificacion p = (Notificacion) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), InforProyectoActivity.class);
                intent.putExtra("Proyecto", p);
                startActivity(intent);*/
        }
        });
        return view;
    }

}
