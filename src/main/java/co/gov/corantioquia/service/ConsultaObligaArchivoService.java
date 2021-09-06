package co.gov.corantioquia.service;

import co.gov.corantioquia.models.dto.EliminarArchivoDTO;
import co.gov.corantioquia.models.dto.EliminarObligacionDTO;
import co.gov.corantioquia.models.dto.GuardarArchivoDTO;

public interface ConsultaObligaArchivoService {

   String eliminarArchivo(EliminarArchivoDTO eliminarArchivoDTO);

    byte[] listarArchivos(Integer sequenceThirdParty, Integer sequenceThirdParty2);

    String guardarArchivo(GuardarArchivoDTO guardarArchivoDTO);


}
