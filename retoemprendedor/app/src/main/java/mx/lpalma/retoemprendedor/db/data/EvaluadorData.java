package mx.lpalma.retoemprendedor.db.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.lpalma.retoemprendedor.db.RetoDBHelper;
import mx.lpalma.retoemprendedor.models.Evaluador;
import mx.lpalma.retoemprendedor.models.Integrante;

/**
 * Created by lpalma on 21/08/2016.
 */
public class EvaluadorData {
    RetoDBHelper retoDBHelper;

    public boolean insert(Evaluador unidad, Context context) {
        boolean result = false;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", unidad.getNombre());
            values.put("proyecto_id", unidad.getProyecto());
            db.insert("evaluador", null, values);
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
            db.delete("evaluador", "id = " + id, null);
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
            db.delete("evaluador", " ", null);
            db.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public List<Evaluador> getAll(String id, Context context) {
        List<Evaluador> lisU = new ArrayList<Evaluador>();
        Cursor cursor = null;
        try {
            retoDBHelper = new RetoDBHelper(context);
            SQLiteDatabase db = retoDBHelper.getWritableDatabase();
            String[] columns = {"nombre", "proyecto_id"};
            cursor = db.query("integrante", columns, "proyecto_id = " + id, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Evaluador u = new Evaluador();
                        u.setNombre(cursor.getString(0));
                        u.setProyecto(cursor.getString(1));
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


