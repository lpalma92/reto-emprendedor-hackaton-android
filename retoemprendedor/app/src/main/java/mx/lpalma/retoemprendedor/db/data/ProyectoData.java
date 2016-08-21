package mx.lpalma.retoemprendedor.db.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.lpalma.retoemprendedor.db.RetoDBHelper;
import mx.lpalma.retoemprendedor.models.Proyecto;

/**
 * Created by lpalma on 21/08/2016.
 */
public class ProyectoData {
    RetoDBHelper retoDBHelper;


    public boolean insert(Proyecto unidad, Context context) {
        boolean result = false;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", unidad.getId());
            values.put("nombre", unidad.getNombre());
            values.put("descripcion", unidad.getDescripcion());
            values.put("estado", unidad.getEstado());
            values.put("convocatoria", unidad.getConvocatoria());
            values.put("categoria", unidad.getCategoria());
            db.insert("proyecto", null, values);
            db.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean deleteOne(Context context, String id) {
        boolean result = false;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            db.delete("proyecto", "id = " + id, null);
            db.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean delete(Context context) {
        boolean result = false;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            db.delete("proyecto", " ", null);
            db.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public List<Proyecto> getAll(Context context) {
        List<Proyecto> lisU = new ArrayList<Proyecto>();
        Cursor cursor = null;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            String[] columns = {"id","nombre","descripcion" ,"estado", "convocatoria", "categoria"};
            cursor = db.query("proyecto", columns, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Proyecto u = new Proyecto();
                        u.setId(cursor.getString(0));
                        u.setNombre(cursor.getString(1));
                        u.setDescripcion(cursor.getString(2));
                        u.setEstado(cursor.getString(3));
                        u.setConvocatoria(cursor.getString(4));
                        u.setCategoria(cursor.getString(5));
                        lisU.add(u);
                    } while (cursor.moveToNext());
                }
            }
            db.close();
        } catch (Exception e) {
            return lisU;
        }
        return lisU;
    }
}
