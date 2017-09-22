package edu.galileo.android.poo_ejercicio1.Procesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import edu.galileo.android.poo_ejercicio1.Entidades.Usuario;
import edu.galileo.android.poo_ejercicio1.Helper.DBHelper;
import edu.galileo.android.poo_ejercicio1.Helper.DBHelper.TablaUsuarios;

/**
 * Created by postgres on 20/9/2017.
 */

public class ProcesoUsuario {

    private SQLiteDatabase db;
    private DBHelper helper;

    public ProcesoUsuario(Context context) { //Obtenemos una sola instancia de la clase BBhelper
        helper = DBHelper.getInstance(context);
    }

    public void Open() {
        db = helper.getWritableDatabase();
    }

    public void Close() {
        helper.close();
    }

    public long Insertar(Usuario usuario) {

        long operacion =0;
        try
        {
            ContentValues registros = new ContentValues();
            registros.put(TablaUsuarios.CAMPO_USUARIO, usuario.getUsuario());
            registros.put(TablaUsuarios.CAMPO_EDAD, usuario.getEdad());
            operacion = db.insert(TablaUsuarios.TABLA_USUARIOS, null, registros);
        }
        catch (SQLiteException e ) {
            new SQLiteException("Error al Insertar");
        }
        catch (Exception e) {
            new Exception("Error en el metodo Insertar");
        }
        return operacion;
    }

    public boolean ValidarRepeticionUsuario(String codigo)
    {
        boolean operacion = false;
        try
        {
            final String query = "select * from " + TablaUsuarios.TABLA_USUARIOS
                    + " where " + TablaUsuarios.CAMPO_USUARIO + " = '" + codigo + "'";
            Cursor fila = db.rawQuery(query, null);

            while (fila.moveToNext())
            {
                String usuario = fila.getString(1);
                if (codigo.equals(usuario)) {
                    operacion = true;
                }
            }
        }
        catch (SQLiteException e)
        {
            new SQLiteException("Error al Insertar");
        }
        catch (Exception e)
        {
            new Exception("Error en el metodo Insertar");
        }
        return operacion;
    }

    public ArrayList<Usuario> Consultar(String codigo) {

        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try
        {
            final String query = "select * from " + TablaUsuarios.TABLA_USUARIOS
                    + " where " + TablaUsuarios.CAMPO_USUARIO + " = '" + codigo + "'";
            Cursor fila = db.rawQuery(query, null);

            if (fila.moveToFirst())
            {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(fila.getString(0)));
                usuario.setUsuario(fila.getString(1));
                usuario.setEdad(Integer.parseInt(fila.getString(2)));

                listaUsuario.add(usuario);
            }
        }
        catch (SQLiteException e)
        {
            new SQLiteException("Error al Consultar");
        }
        catch (Exception e)
        {
            new Exception("Error en el metodo Consultar");
        }
        return listaUsuario;
    }

    public ArrayList<Usuario> Listar()
    {
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try
        {
            Cursor fila = db.rawQuery("SELECT*FROM"+TablaUsuarios.TABLA_USUARIOS, null);

            while (fila.moveToNext())
            {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(fila.getString(0)));
                usuario.setUsuario(fila.getString(1));
                usuario.setEdad(Integer.parseInt(fila.getString(2)));

                listaUsuario.add(usuario);
            }
        }
        catch (SQLiteException e)
        {
            new SQLiteException("Error al Listar");
        }
        catch (Exception e)
        {
            new Exception("Error en el metodo Listar");
        }
        return listaUsuario;
    }

    public long Eliminar(String codigo)
    {
        long operacion =0;
        try
        {

        }
        catch (SQLiteException e)
        {
            new SQLiteException("Error al Eliminar");
        }
        catch (Exception e)
        {
            new Exception("Error en el metodo Eliminar");
        }
        return operacion;
    }

    public long Modificar(String codigo, int edad)
    {
        long operacion =0;
        try
        {
           ContentValues registros = new ContentValues();
            registros.put(TablaUsuarios.CAMPO_EDAD, edad);
            operacion = db.update(TablaUsuarios.TABLA_USUARIOS, registros, TablaUsuarios.CAMPO_USUARIO + " = '" + codigo + "'", null);

        }catch (SQLiteException e)
        {
            new SQLiteException("Error al Modificar");
        }
        catch (Exception e)
        {
            new Exception("Error en el metodo Modificar");
        }
        return operacion;
    }
}
