package co.gov.corantioquia.service.impl;

import co.gov.corantioquia.models.dto.ResponsableDTO;
import co.gov.corantioquia.repository.CrearInicialRepository;
import co.gov.corantioquia.service.CrearInicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrearInicialServiceImpl implements CrearInicialService {

    @Autowired
    private CrearInicialRepository crearInicialRepository;

    public CrearInicialServiceImpl(CrearInicialRepository crearInicialRepository) {
        this.crearInicialRepository = crearInicialRepository;
    }

    @Override
    public String crearInicial(ResponsableDTO responsableDTO) {
        return crearInicialRepository.crearInicial(responsableDTO);
    }


}
