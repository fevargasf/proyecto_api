package co.gov.corantioquia.service;


import co.gov.corantioquia.models.dto.RadicarDTO;
import co.gov.corantioquia.models.dto.RadicarPdfDTO;

public interface RadicarCoeService {
    public  RadicarDTO radicar(RadicarDTO radicarDTO);

    public byte[] construirPdfRadicado(RadicarPdfDTO radicarPdfDTO);
}
