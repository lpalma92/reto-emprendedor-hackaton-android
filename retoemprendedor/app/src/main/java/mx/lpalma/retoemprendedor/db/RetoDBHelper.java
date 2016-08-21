package mx.lpalma.retoemprendedor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lpalma on 21/08/2016.
 */
public class RetoDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "reto.db";
    public static final int VERSION = 1;

    public RetoDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS proyecto(id VARCHAR(3) ,nombre VARCHAR(50), descripcion TEXT, estado VARCHAR(50), convocatoria VARCHAR(50),categoria VARCHAR(50))");
        db.execSQL("CREATE TABLE IF NOT EXISTS integrante(id VARCHAR(3) , nombre VARCHAR(20), email TEXT, proyecto_id VARCHAR(3))");
        db.execSQL("CREATE TABLE IF NOT EXISTS evaluador(nombre VARCHAR(50), proyecto_id VARCHAR(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
