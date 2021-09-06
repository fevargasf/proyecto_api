package co.gov.corantioquia.service.impl;


import co.gov.corantioquia.models.dto.*;

import co.gov.corantioquia.repository.GuardarObligacionRepository;
import co.gov.corantioquia.service.ObligationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ObligationServiceImpl implements ObligationService {
    @Autowired
    private GuardarObligacionRepository guardarObligacionRepository;

    public ObligationServiceImpl(GuardarObligacionRepository guardarObligacionRepository) {
        this.guardarObligacionRepository = guardarObligacionRepository;
    }

    @Override
    public GuardarObligacionResponse guardarObligacion(GuardarObligacionRequest guardarObligacionRequest) {
        String line = guardarObligacionRepository.guardarObligacion(guardarObligacionRequest);
        return new GuardarObligacionResponse(true, line);
    }

    @Override
    public ListaObligaciones listaObligaciones(Integer sequenceThirdParty, Integer sequenceThirdParty2) {

        ListaObligaciones listaObligaciones = new ListaObligaciones ();
        List<Object> objects = guardarObligacionRepository.consultarObligacion(sequenceThirdParty, sequenceThirdParty2);
        List<ConsultaObDTO > obligaciones = new ArrayList<>();

        for (Object obligacion : objects) {

            Object[] value = (Object[]) obligacion;

            ConsultaObDTO dto = new ConsultaObDTO();

            dto.setLinea(((BigDecimal) value[0]));
            dto.setDescripcion(((String) value[1]));
            dto.setObservacion(String.valueOf(value[2]));
            dto.setFechaCreacion((Date) value[3]);
            dto.setDocSecResol(((BigDecimal) value[4]));
            dto.setRadicadoResolucion(String.valueOf(value[5]));

            obligaciones.add(dto );

        }
        listaObligaciones.setObligaciones(obligaciones );

        return listaObligaciones;
    }
    @Override
    public String eliminarObligacion(EliminarObligacionDTO eliminarObligacionDTO) {
        String o = (String) guardarObligacionRepository.eliminarObligacion(eliminarObligacionDTO);

        return o;
    }
}
