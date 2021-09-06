package co.gov.corantioquia.models.dto;

public class GuardarObligacionRequest extends BaseDTO{
    private int secResol;
    private String descripcion;
    private String observaciones;
    private int linea;

    public int getSecResol() {
        return secResol;
    }

    public void setSecResol(int secResol) {
        this.secResol = secResol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getLinea() {return linea;}

    public void setLinea(int linea) {
        this.linea = linea;
    }
}
