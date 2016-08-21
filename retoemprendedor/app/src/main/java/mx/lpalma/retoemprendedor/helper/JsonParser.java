package mx.lpalma.retoemprendedor.helper;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import mx.lpalma.retoemprendedor.db.RetoDBHelper;
import mx.lpalma.retoemprendedor.db.data.EvaluadorData;
import mx.lpalma.retoemprendedor.db.data.IntegranteData;
import mx.lpalma.retoemprendedor.db.data.ProyectoData;
import mx.lpalma.retoemprendedor.models.Evaluador;
import mx.lpalma.retoemprendedor.models.Integrante;
import mx.lpalma.retoemprendedor.models.Proyecto;
import mx.lpalma.retoemprendedor.models.Usuario;

/**
 * Created by lpalma on 20/08/2016.
 */
public class JsonParser
{
    public static boolean respose(String result){
        boolean objectResult = false;
        try {
            JSONObject Jsonresult = new JSONObject(result);
            JSONObject object = Jsonresult.getJSONObject("data");
            if(object != null){
                objectResult = object.getBoolean("response");
            }
        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data 1" + e.toString());
        }
        return objectResult;
    }

    public static Usuario parseUsuario(String json){
        Usuario user = null;
        try {
            JSONObject Jsonresult = new JSONObject(json);
            JSONObject object = Jsonresult.getJSONObject("data");
            if(object != null){
                boolean objectResult = object.getBoolean("response");
                if(objectResult){
                    user = new Usuario();
                    user.setId(Jsonresult.getInt("id")+"");
                    user.setNombre(Jsonresult.getString("nombre"));
                }
            }

        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data 2" + e.toString());
        }
        return user;
    }

    public static boolean parseProyecto(String json, Context context){
        Usuario user = null;
        ProyectoData datap = new ProyectoData();
        datap.delete(context);
        IntegranteData datati = new IntegranteData();
        datati.delete(context);
        EvaluadorData datate= new EvaluadorData();
        datati.delete(context);
        try {
            JSONObject Jsonresult = new JSONObject(json);
            JSONObject object = Jsonresult.getJSONObject("data");
            if(object != null){
                boolean objectResult = object.getBoolean("response");
                if(objectResult){
                    JSONArray array = object.getJSONArray("proyectos");
                    for (int i=0; i < array.length();i++) {
                        JSONObject data = array.getJSONObject(i);
                        Proyecto d = new Proyecto();
                        d.setId(data.getInt("id") + "");
                        d.setNombre(data.getString("nombre"));
                        d.setDescripcion(data.getString("descripcion"));
                        d.setConvocatoria(data.getString("convocatoria"));
                        d.setEstado(data.getString("estado"));
                        d.setCategoria("");
                        JSONArray inte = data.getJSONArray("integrantes");
                        for (int j=0; j < inte.length();j++) {
                            JSONObject idata = inte.getJSONObject(i);
                            Integrante in = new Integrante();
                            in.setId(idata.getInt("id") + "");
                            in.setNombre(idata.getString("nombre"));
                            in.setEmail(idata.getString("email"));
                            in.setProyecto(d.getId());
                            datati.insert(in,context);
                        }
                        JSONArray eva = data.getJSONArray("evaluadores");
                        for (int e=0; e < eva.length();e++) {
                            JSONObject idata = eva.getJSONObject(i);
                            Evaluador in = new Evaluador();
                            in.setNombre(idata.getString("nombre"));
                            in.setProyecto(d.getId());
                            datate.insert(in,context);
                        }
                        datap.insert(d,context);

                    }
                }
            }
            return true;
        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data 1 " + e.toString());
            return  false;
        }
    }
}
