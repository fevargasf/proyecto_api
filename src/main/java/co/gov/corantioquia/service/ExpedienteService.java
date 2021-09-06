package co.gov.corantioquia.service;

import co.gov.corantioquia.models.dto.ExpedientDTO;
import co.gov.corantioquia.models.dto.ListaExpedientes;


public interface ExpedienteService {

        ListaExpedientes listarExpedientes(Integer sequenceThirdParty);

        ExpedientDTO consultarExpedientes(Integer sequenceThirdParty, Integer sequenceThirdParty2);
}
