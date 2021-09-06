package co.gov.corantioquia.service;

import co.gov.corantioquia.models.dto.EliminarObligacionDTO;
import co.gov.corantioquia.models.dto.GuardarObligacionRequest;
import co.gov.corantioquia.models.dto.GuardarObligacionResponse;
import co.gov.corantioquia.models.dto.ListaObligaciones;

public interface ObligationService {
    GuardarObligacionResponse guardarObligacion(GuardarObligacionRequest guardarObligacionRequest);

    ListaObligaciones listaObligaciones(Integer sequenceThirdParty, Integer sequenceThirdParty2);

    public Object eliminarObligacion(EliminarObligacionDTO eliminarObligacionDTO);
}
