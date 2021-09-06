package co.gov.corantioquia.models.dto;

import java.util.List;

public class ListaObligaciones {
    private List<ConsultaObDTO> obligaciones;

    public List<ConsultaObDTO> getObligaciones() {
        return obligaciones;
    }
    public void setObligaciones(List<ConsultaObDTO> obligaciones) {
        this.obligaciones = obligaciones;
    }
}
