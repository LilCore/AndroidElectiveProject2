package ids.pdos.utilidades;



public class Utilidades {

    //Constantes campos tabla tarea
    public static final String TABLA_TAREA="tarea";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_FECHA_CREACION="fecha_creacion";
    public static final String CAMPO_FECHA_FINALIZACION="fecha_finalizacion";
    public static final String CAMPO_ESTADO="estado";
    public static final String CAMPO_DESCRIPCION="descripcion";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE tarea (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, fecha_creacion TEXT, fecha_finalizacion STRING DEFAULT NULL, descripcion STRING, estado INTEGER DEFAULT 0)";


}
