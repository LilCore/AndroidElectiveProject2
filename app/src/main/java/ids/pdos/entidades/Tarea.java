package ids.pdos.entidades;



public class Tarea {

    //Campos de la tabla
    private int id;
    private String nombre;
    private String fecha_creacion;
    private String fecha_finalizacion;
    private String descripcion;
    private int estado;


    public Tarea(int id, String nombre, String fecha_creacion, String fecha_finalizacion, String descripcion, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.fecha_finalizacion = fecha_finalizacion;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Tarea(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(String fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
