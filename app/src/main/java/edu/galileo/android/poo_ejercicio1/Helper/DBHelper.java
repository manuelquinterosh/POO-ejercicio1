package edu.galileo.android.poo_ejercicio1.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by postgres on 20/9/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper helper = null;

    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;

    //Clase que representa la tabla usuarios
    public static class TablaUsuarios
    {
        public static String TABLA_USUARIOS = "Usuarios";
        public static String CAMPO_ID = "id_username";
        public static String CAMPO_USUARIO = "username";
        public static String CAMPO_EDAD = "edad";
    }

    //Sentencia para crear tabla usuarios
    private static final String USUARIO_CREATE = "create table " + TablaUsuarios.TABLA_USUARIOS + " ( "
            + TablaUsuarios.CAMPO_ID + " integer primary key autoincrement, "
            + TablaUsuarios.CAMPO_USUARIO + " text not null unique, "
            + TablaUsuarios.CAMPO_EDAD + " text not null);";

    //Patron singleton
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        if (helper == null) {
            helper = new DBHelper(context.getApplicationContext());
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
             sqLiteDatabase.execSQL(USUARIO_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("delete table if exist " + TablaUsuarios.TABLA_USUARIOS);
          onCreate(sqLiteDatabase);
    }
}
