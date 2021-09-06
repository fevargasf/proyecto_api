package co.gov.corantioquia.models.dto;
import java.math.BigDecimal;
import java.util.Date;

public class ResolutionDTO {

    private BigDecimal expe_sec;
    private String radicado;
    private Date fecha;
    private String descriptor;
    private String link_descarga;


    public BigDecimal getexpe_sec() {
        return expe_sec;
    }

    public void setexpe_sec(BigDecimal expe_sec) {
        this.expe_sec = expe_sec;
    }

    public String  getRadicado() {
        return radicado;
    }

    public void setRadicado(String  radicado) {
        this.radicado = radicado;
    }

    public Date  getFecha() {
        return fecha;
    }

    public void setFecha(Date  fecha) {
        this.fecha = fecha;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getLink_descarga() {
        return link_descarga;
    }

    public void setLink_descarga(String link_descarga) {
        this.link_descarga = link_descarga;
    }
}
