package co.gov.corantioquia.service.impl;

import co.gov.corantioquia.models.dto.ConexionDTO;
import co.gov.corantioquia.repository.ConexionRepository;
import co.gov.corantioquia.service.ConexionService;
import org.springframework.stereotype.Service;

@Service
public class ConexionServiceImpl implements ConexionService {

    private ConexionRepository conexionRepository;

    public ConexionServiceImpl (ConexionRepository conexionRepository) {
        this.conexionRepository  = conexionRepository;
    }


    public ConexionDTO conexion(ConexionDTO conexionDTO) {
        return conexionRepository.conexion(conexionDTO);
    }
}
