package co.gov.corantioquia.service.impl;

import co.gov.corantioquia.models.dto.ResolutionDTO;
import co.gov.corantioquia.models.dto.ListaResoluciones;
import co.gov.corantioquia.repository.SelfManagementRepository;
import co.gov.corantioquia.service.ResolucionesService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResolucionesServiceImpl implements ResolucionesService {

    private SelfManagementRepository selfManagementRepository;

    public ResolucionesServiceImpl(SelfManagementRepository selfManagementRepository) {
        this.selfManagementRepository = selfManagementRepository;
    }


    @Override
    public ListaResoluciones listarResoluciones(Integer sequenceThirdParty2) {
        ListaResoluciones listaResoluciones = new ListaResoluciones ();

        List<Object> objects = selfManagementRepository.listarResoluciones(sequenceThirdParty2);

        List<ResolutionDTO> resolutions = new ArrayList<>();


        for (Object resolution : objects) {

            Object[] resolAux = (Object[]) resolution;
            ResolutionDTO resolutionDTO = new ResolutionDTO();

            resolutionDTO.setexpe_sec(((BigDecimal) resolAux[0]));
            resolutionDTO.setRadicado(((String ) resolAux[1]));
            resolutionDTO.setFecha(((Date) resolAux[2]));
            resolutionDTO.setDescriptor(((String ) resolAux[3]));
            resolutionDTO.setLink_descarga(((String ) resolAux[4]));
            resolutions.add( resolutionDTO);
        }
        listaResoluciones.setResoluciones(resolutions);

        return listaResoluciones;
    }
}
