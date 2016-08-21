package mx.lpalma.retoemprendedor.proyectos;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
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

import mx.lpalma.retoemprendedor.adapters.ProyectoAdapter;
import mx.lpalma.retoemprendedor.R;
import mx.lpalma.retoemprendedor.db.data.ProyectoData;
import mx.lpalma.retoemprendedor.models.Proyecto;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ListProyectosFragment extends Fragment {

    private ListView lista;
    private TextView mensaje;

    public ListProyectosFragment() {
        // Required empty public constructor
    }

    public static ListProyectosFragment newInstance() {
        ListProyectosFragment fragment = new ListProyectosFragment();
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
        final View view = inflater.inflate(R.layout.fragment_list_proyectos, container, false);
        lista = (ListView)view.findViewById(R.id.lst);
        mensaje = (TextView) view.findViewById(R.id.txt_mensaje);
        ProyectoData datap = new ProyectoData();
        List<Proyecto> usuarios = datap.getAll(getActivity());
        Log.i("LISTA_USU", usuarios.size() +"");
        if(!usuarios.isEmpty()) {
            ArrayAdapter<Proyecto> adapter = new ProyectoAdapter(getActivity(), usuarios);
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
                Proyecto p = (Proyecto) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), InforProyectoActivity.class);
                intent.putExtra("Proyecto", p);
                startActivity(intent);
            }
        });
        return view;
    }

}
