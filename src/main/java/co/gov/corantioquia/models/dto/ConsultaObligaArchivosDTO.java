package co.gov.corantioquia.models.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class ConsultaObligaArchivosDTO {
    private int obligaLinea;
    private String expediente;
    private String obligaDescripcion;
    private String obligaObservacion;
    private Date obligaFechaCreacion;
    private BigDecimal docSecResol;
    private String radicadoResolucion;
    private BigDecimal archivoNro;
    private String archivoNombre;
    private Blob archivo;
    private Date fechaIngresoArchivo;
    private Date fechaFoto;
    private BigDecimal coorX;
    private BigDecimal coorY;

    public int getObligaLinea() {
        return obligaLinea;
    }

    public void setObligaLinea(int obligaLinea) {
        this.obligaLinea = obligaLinea;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getObligaDescripcion() {
        return obligaDescripcion;
    }

    public void setObligaDescripcion(String obligaDescripcion) {
        this.obligaDescripcion = obligaDescripcion;
    }

    public String getObligaObservacion() {
        return obligaObservacion;
    }

    public void setObligaObservacion(String obligaObservacion) {
        this.obligaObservacion = obligaObservacion;
    }
    public Date getObligaFechaCreacion() {
        return obligaFechaCreacion;
    }

    public void setObligaFechaCreacion(Date obligaFechaCreacion) {
        this.obligaFechaCreacion = obligaFechaCreacion;
    }

    public BigDecimal getArchivoNro() {
        return archivoNro;
    }

    public void setArchivoNro(BigDecimal archivoNro) {
        this.archivoNro = archivoNro;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public Blob getArchivo() {
        return archivo;
    }

    public void setArchivo(Blob archivo) {
        this.archivo = archivo;
    }

    public Date getFechaIngresoArchivo() {
        return fechaIngresoArchivo;
    }

    public void setFechaIngresoArchivo(Date fechaIngresoArchivo) {
        this.fechaIngresoArchivo = fechaIngresoArchivo;
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

    public Date getFechaFoto() {
        return fechaFoto;
    }

    public void setFechaFoto(Date fechaFoto) {
        this.fechaFoto = fechaFoto;
    }

    public BigDecimal getCoorX() {
        return coorX;
    }

    public void setCoorX(BigDecimal coorX) {
        this.coorX = coorX;
    }

    public BigDecimal getCoorY() {
        return coorY;
    }

    public void setCoorY(BigDecimal coorY) {
        this.coorY = coorY;
    }
}

