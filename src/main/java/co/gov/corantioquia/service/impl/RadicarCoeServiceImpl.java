package co.gov.corantioquia.service.impl;


import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.RadicarDTO;
import co.gov.corantioquia.models.dto.RadicarPdfDTO;
import co.gov.corantioquia.repository.ConsultaArchivoRepository;
import co.gov.corantioquia.repository.RadicarCoeRepository;
import co.gov.corantioquia.service.RadicarCoeService;
import co.gov.corantioquia.utils.CoMerge;
import co.gov.corantioquia.utils.ConsultaObligaArchivoUtil;
import co.gov.corantioquia.utils.Documento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadicarCoeServiceImpl implements RadicarCoeService {

    private RadicarCoeRepository radicarCoeRepository;

    @Autowired
    private ConsultaArchivoRepository consultaArchivoRepository;

    public RadicarCoeServiceImpl(RadicarCoeRepository radicarCoeRepository){
        this.radicarCoeRepository=radicarCoeRepository;
    }



    public RadicarDTO radicar(RadicarDTO radicarDTO) {
        radicarDTO = radicarCoeRepository.radicar(radicarDTO);
        return radicarDTO;
    }

    public byte[] construirPdfRadicado(RadicarPdfDTO radicarPdfDTO){
        //Consultar archivos
        List<Object> listaDesdeSP = consultaArchivoRepository.consultaObligaArchivos(radicarPdfDTO.getSequenceThirdParty(),radicarPdfDTO.getSequenceThirdParty2());
        List<ConsultaObligaArchivosDTO> lista = ConsultaObligaArchivoUtil.objectToConsultaObligaArchivosDTO(listaDesdeSP);

        radicarPdfDTO.setArchivos(lista);

        byte[] pdfRadicado =  CoMerge.merge(radicarPdfDTO);

        //Realizar copia a servidor
        Documento documento = new Documento(radicarPdfDTO.getRuta(), pdfRadicado);
        ConsultaObligaArchivoUtil.asociarArchivoDelDocumento(documento);

        return pdfRadicado;
    }

}
