package mx.lpalma.retoemprendedor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mx.lpalma.retoemprendedor.R;
import mx.lpalma.retoemprendedor.models.Notificacion;
import mx.lpalma.retoemprendedor.models.Proyecto;

/**
 * Created by lpalma on 21/08/2016.
 */
public class NotificacionAdapter extends ArrayAdapter<Notificacion> {

    Context context;
    List<Notificacion> objects;

    public NotificacionAdapter(Context context, List<Notificacion> objects) {
        super(context, R.layout.item_proyecto, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notificacion usuario = objects.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_proyecto, parent, false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.nombre);
        nombre.setText(usuario.getMensaje());
        TextView convocatoria = (TextView) convertView.findViewById(R.id.convocatoria);
        convocatoria.setText(usuario.getFecha());
        TextView estado = (TextView) convertView.findViewById(R.id.estado);
        //estado.setText(usuario.getEstado());

        return convertView;
    }
}
