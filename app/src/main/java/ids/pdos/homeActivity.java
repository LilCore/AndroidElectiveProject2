package ids.pdos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import ids.pdos.DBHelper.ConexionSQLiteHelper;
import ids.pdos.entidades.Tarea;

public class homeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    ConexionSQLiteHelper conn;
    ListView listViewTareasNoCompletadas;
    ArrayList<String> listaInformacion;
    ArrayList<Tarea> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Evento del boton fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                OpenCreateActivity(view);
            }
        });

        //Instancia a listview
        //listViewTareasNoCompletadas = (ListView) findViewById(R.id.listViewTareasNoCompletadas);

        //Obtiene un handle para el RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);

        //Instanciación del helper de la db
        conn = new ConexionSQLiteHelper(this, "db_tarea", null, 1);

        //Trae tareas no completadas de la db
        consultarTareasNoCompletadas();

        //Si existen tareas para mostrar
       if(listaTareas.size()>0){
        //ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        //listViewTareasNoCompletadas.setAdapter(adaptador);

           // Crea un adapter y suministra los datos a ser mostrados.
           mAdapter = new WordListAdapter(this, listaInformacion);
           // Conecta el adapter con el RecyclerView.
           mRecyclerView.setAdapter(mAdapter);
           // Le da al RecyclerView un layout manager por defecto.
           mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void OpenCreateActivity(View view) {
        Intent messageIntent = new Intent(this, createActivity.class);
        startActivity(messageIntent);
    }


    /**
     * Trae las tareas sin completar y las muestra en un listview
     */
    public void consultarTareasNoCompletadas(){

        SQLiteDatabase db = conn.getWritableDatabase();

        Tarea tarea = null;
        listaTareas = new ArrayList<Tarea>();

        //Realiza la query
        Cursor cursor = db.rawQuery("SELECT nombre, fecha_creacion, descripcion FROM tarea WHERE estado = 0 ORDER BY fecha_creacion DESC", null);

        try {

            //Si existen tareas sin completar
            if(cursor != null & cursor.getCount() > 0){

                //cursor.moveToFirst();

                while (cursor.moveToNext()) {


                        String nombre = cursor.getString(0);
                        String fecha_creacion = cursor.getString(1);
                        String descripcion = cursor.getString(2);

                        tarea = new Tarea();
                        tarea.setDescripcion(descripcion);
                        tarea.setNombre(nombre);
                        tarea.setFecha_creacion(fecha_creacion);

                        listaTareas.add(tarea);
                }

                obtenerLista();

            }else{

                //Muestra mensaje de que no existen items para mostrar


            }

        } finally {
            cursor.close();
            db.close();
        }

    }

    /**
     *
     */
    private void obtenerLista(){

        listaInformacion = new ArrayList<String>();

        for (int i=0; i<listaTareas.size();i++){

            Tarea t = listaTareas.get(i);
            listaInformacion.add("\n"+t.getNombre()+"  "+t.getFecha_creacion()+"\n\n"+t.getDescripcion()+"\n");

        }

    }
}
