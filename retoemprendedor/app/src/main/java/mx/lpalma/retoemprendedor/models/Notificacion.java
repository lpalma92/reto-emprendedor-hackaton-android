package mx.lpalma.retoemprendedor.models;

import java.io.Serializable;

/**
 * Created by lpalma on 21/08/2016.
 */
public class Notificacion implements Serializable{

    String mensaje;
    String fecha;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
