package co.gov.corantioquia.models.dto;

import java.util.Date;
import java.util.List;

public class RadicarPdfDTO extends BaseDTO{


   private  List<ConsultaObligaArchivosDTO> archivos;
    private ExpedientDTO expedientDTO;
    private ConsultaObligaArchivosDTO consultaObligaArchivosDTO;
    private String radicado;
    private Date fechaRadicado;
    private String ruta;


    public ConsultaObligaArchivosDTO getConsultaObligaArchivosDTO() {
        return consultaObligaArchivosDTO;
    }

    public void setConsultaObligaArchivosDTO(ConsultaObligaArchivosDTO consultaObligaArchivosDTO) {
        this.consultaObligaArchivosDTO = consultaObligaArchivosDTO;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<ConsultaObligaArchivosDTO> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ConsultaObligaArchivosDTO> archivos) {
        this.archivos = archivos;
    }

    public ExpedientDTO getExpedientDTO() {
        return expedientDTO;
    }

    public void setExpedientDTO(ExpedientDTO expedientDTO) {
        this.expedientDTO = expedientDTO;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }
}
