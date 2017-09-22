package edu.galileo.android.poo_ejercicio1;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.galileo.android.poo_ejercicio1.Entidades.Usuario;
import edu.galileo.android.poo_ejercicio1.Procesos.ProcesoUsuario;

public class MainActivity extends AppCompatActivity {

    private ProcesoUsuario procesoUsuario;
    private EditText txtUsername;
    private EditText txtEdad;
    private TextView lbMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        procesoUsuario = new ProcesoUsuario(this);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        lbMensaje = (TextView) findViewById(R.id.lbMensaje);
    }

    public void Insertar(View view)
    {
        procesoUsuario.Open();
        this.InsertarUsuario();
        procesoUsuario.Close();

    }

    public void Consultar(View view)
    {
        procesoUsuario.Open();
        this.ConsultarUsuario();
        procesoUsuario.Close();
    }

    public void Listar(View view)
    {
        procesoUsuario.Open();
        this.ListarUsuarios();
        procesoUsuario.Close();
    }

    public void Eliminar(View view) {
        procesoUsuario.Open();
        this.EliminarUsuario();
        procesoUsuario.Close();
    }

    public void Modificar(View view) {
        procesoUsuario.Open();
        this.ModificarUsuario();
        procesoUsuario.Close();
    }

    private void InsertarUsuario()
    {
        try
        {
            Usuario usuario = new Usuario();
            usuario.setUsuario(txtUsername.getText().toString());
            usuario.setEdad(Integer.parseInt(txtEdad.getText().toString()));

            if (procesoUsuario.ValidarRepeticionUsuario(txtUsername.getText().toString())==false)
            {
                long insercion = procesoUsuario.Insertar(usuario);
                if (insercion>0)
                {
                    Toast.makeText(this, "Insercion Correcta", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Usuario Repetido", Toast.LENGTH_SHORT).show();
            }
        }
        catch (SQLiteException e)
        {
            Toast.makeText(this, ""+ e, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    private void ConsultarUsuario()
    {
        String contenido ="";
        ArrayList<Usuario> listaUsuario = procesoUsuario.Consultar(txtUsername.getText().toString());

        if (listaUsuario.size()>0)
        {
            for (int i=0; i<listaUsuario.size(); i++)
            {
                Usuario usuario = listaUsuario.get(i);

                int id_usuario = usuario.getId();
                String username = usuario.getUsuario();
                int edad = usuario.getEdad();

                contenido = "\nID_USUARIO: "+id_usuario
                        + "\nUSERNAME: " + username
                        + "\nEDAD: " + edad;
            }
            lbMensaje.setText("");
            lbMensaje.setText(contenido);
        }
        else
        {
            Toast.makeText(this, "Datos No Encontrado", Toast.LENGTH_SHORT).show();
            lbMensaje.setText("");
        }
    }

    private void ListarUsuarios()
    {

        ArrayList<Usuario> listaUsuario = procesoUsuario.Listar();

        if (listaUsuario.size()>0)
        {
            for (int i=0; i<listaUsuario.size();i++)
            {
                Usuario usuario = listaUsuario.get(i);

                int id_usuario = usuario.getId();
                String username = usuario.getUsuario();
                int edad = usuario.getEdad();

               String contenido = "\nID_USUARIO: "+id_usuario
                        + "\nUSERNAME: " + username
                        + "\nEDAD: " + edad;

                Toast.makeText(this, contenido, Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Datos No Encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private void EliminarUsuario()
    {
        try
        {
            long operacion = procesoUsuario.Eliminar(txtUsername.getText().toString());

            if (operacion >0)
            {
                Toast.makeText(this, "Eliminacion Correcta", Toast.LENGTH_SHORT).show();
            }
            lbMensaje.setText("");
        }
        catch (SQLiteException e)
        {
            Toast.makeText(this, ""+e,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    private void ModificarUsuario()
    {
        try
        {
            String usuario = txtUsername.getText().toString();
            int edad = Integer.parseInt(txtEdad.getText().toString());
            long operacion = procesoUsuario.Modificar(usuario, edad);

            if (operacion > 0)
            {
                Toast.makeText(this, "Modificacion Correcta", Toast.LENGTH_SHORT).show();
            }
        }catch (SQLiteException e)
        {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e,Toast.LENGTH_SHORT).show();
        }
    }
}
