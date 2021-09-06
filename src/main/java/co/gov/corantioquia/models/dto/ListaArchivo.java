package co.gov.corantioquia.models.dto;

import java.util.List;

public class ListaArchivo {
    private List<ConsultaObligaArchivosDTO> archivos;

    public List<ConsultaObligaArchivosDTO> getConsultaObligaArchivos() {
        return archivos;
    }
    public void setConsultaObligaArchivos(List<ConsultaObligaArchivosDTO> archivos) {
        this.archivos = archivos;
    }
}
