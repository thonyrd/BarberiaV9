package cl.scvg.barberia.clases;

public class Cita {

    private String ID;
    private String peluquero;
    private String lugar;
    private String fecha;

    public Cita() {
        this.ID = ID;
        this.peluquero = peluquero;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public Cita(String ID, String peluquero, String lugar,String fecha) {
        this.ID = ID;
        this.peluquero = peluquero;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPeluquero() {
        return peluquero;
    }

    public void setPeluquero(String peluquero) {
        this.peluquero = peluquero;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "ID='" + ID + '\'' +
                ", peluquero='" + peluquero + '\'' +
                ", lugar='" + lugar + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
