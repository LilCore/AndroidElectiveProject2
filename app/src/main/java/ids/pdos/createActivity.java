package ids.pdos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ids.pdos.DBHelper.ConexionSQLiteHelper;
import ids.pdos.utilidades.Utilidades;

public class createActivity extends AppCompatActivity {

    EditText campoNombre, campoDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        campoNombre = (EditText) findViewById(R.id.nombre);
        campoDescripcion = (EditText) findViewById(R.id.descripcion);


    }


    public void onClick(View view){

        String campoNombre = this.campoNombre.getText().toString();
        String campoDescripcion = this.campoDescripcion.getText().toString();

        if(  campoNombre == null || campoDescripcion == null  ||
                campoNombre.replace(" ","").isEmpty() || campoDescripcion.replace(" ","").isEmpty() ){

            Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();

        }else{

            registrarTarea();
        }

    }


    /**
     * Registra una tarea en la base de datos
     */
    private void registrarTarea(){


        //Instanciación del helper de la db y abre guarda en una variable la conexion
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db_tarea", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();


        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = dateFormat.format(date);

        //Creacion de content values
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_DESCRIPCION,campoDescripcion.getText().toString());
        //values.put(Utilidades.CAMPO_FECHA_CREACION,fecha);
        values.put(Utilidades.CAMPO_FECHA_CREACION,fecha);

        //Inserta
        Long idResultante = db.insert(Utilidades.TABLA_TAREA, Utilidades.CAMPO_ID, values);

        //Muestra mensaje
        //Toast.makeText(getApplicationContext(), "Id Registro: "+idResultante, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Tarea creada con exito!", Toast.LENGTH_SHORT).show();

        Intent messageIntent = new Intent(this, homeActivity.class);
        startActivity(messageIntent);


        //Cierra la conexión
        db.close();

    }

}
