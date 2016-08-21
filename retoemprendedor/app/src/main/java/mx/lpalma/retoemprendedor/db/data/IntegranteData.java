package mx.lpalma.retoemprendedor.db.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.lpalma.retoemprendedor.db.RetoDBHelper;
import mx.lpalma.retoemprendedor.models.Integrante;
import mx.lpalma.retoemprendedor.models.Proyecto;

/**
 * Created by lpalma on 21/08/2016.
 */
public class IntegranteData {
    RetoDBHelper retoDBHelper;

    public boolean insert(Integrante unidad, Context context) {
        boolean result = false;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", unidad.getId());
            values.put("nombre", unidad.getNombre());
            values.put("email", unidad.getEmail());
            values.put("proyecto_id", unidad.getProyecto());
            db.insert("integrante", null, values);
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
            db.delete("integrante", "", null);
            db.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public List<Integrante> getAll(String id, Context context) {
        List<Integrante> lisU = new ArrayList<Integrante>();
        Cursor cursor = null;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            String[] columns = {"id", "nombre", "email", "proyecto_id"};
            cursor = db.query("integrante", columns, "proyecto_id = '" + id +"'", null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Integrante u = new Integrante();
                        u.setId(cursor.getString(0));
                        u.setNombre(cursor.getString(1));
                        u.setEmail(cursor.getString(2));
                        u.setProyecto(cursor.getString(3));
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
