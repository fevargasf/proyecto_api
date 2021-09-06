package co.gov.corantioquia.models.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ConsultaObDTO extends BaseDTO{
    private BigDecimal linea;
    private String descripcion;
    private String observacion;
    private Date FechaCreacion;
    private BigDecimal docSecResol;
    private String radicadoResolucion;


    public BigDecimal getLinea() {
        return linea;
    }

    public void setLinea(BigDecimal linea) {
        this.linea = linea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public BigDecimal getDocSecResol() {
        return docSecResol;
    }

    public void setDocSecResol(BigDecimal docSecResol) {
        this.docSecResol = docSecResol;
    }

    public String getRadicadoResolucion() {
        return radicadoResolucion;
    }

    public void setRadicadoResolucion(String radicadoResolucion) {
        this.radicadoResolucion = radicadoResolucion;
    }
}
