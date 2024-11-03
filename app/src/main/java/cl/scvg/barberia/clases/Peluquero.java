package cl.scvg.barberia.clases;

public class Peluquero {

    private String ID;
    private String nombre;
    private String codigo;

    public Peluquero() {
        this.ID = ID;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Peluquero(String ID, String nombre, String codigo) {
        this.ID = ID;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Peluquero{" +
                "ID='" + ID + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
