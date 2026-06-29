package modelo;

public class Estudiante implements Comparable<Estudiante> {

    private int codigoEstudiante;
    private String nombreCompleto;
    private double promedioGeneral;

    public Estudiante(int codigoEstudiante, String nombreCompleto, double promedioGeneral) {
        this.codigoEstudiante = codigoEstudiante;
        this.nombreCompleto = nombreCompleto;
        this.promedioGeneral = promedioGeneral;
    }

    public int getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(int codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getPromedioGeneral() {
        return promedioGeneral;
    }

    public void setPromedioGeneral(double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    @Override
    public String toString() {
        return String.format("Codigo: %d | Nombre: %-25s | Promedio: %.2f",
                codigoEstudiante, nombreCompleto, promedioGeneral);
    }

    @Override
    public int compareTo(Estudiante otro) {
        return Integer.compare(this.codigoEstudiante, otro.codigoEstudiante);
    }
}
