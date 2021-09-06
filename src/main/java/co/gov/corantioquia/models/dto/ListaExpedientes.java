package co.gov.corantioquia.models.dto;

import java.util.List;

public class ListaExpedientes {

    private Boolean result;
    private List<ExpedientDTO> lista_expedientes;

    public ListaExpedientes() {
        this.result = false;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<ExpedientDTO> getLista_expedientes() {
        return lista_expedientes;
    }

    public void setLista_expedientes(List<ExpedientDTO> lista_expedientes) {
        this.lista_expedientes = lista_expedientes;
    }


}

