package co.gov.corantioquia.service.impl;

import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.EliminarArchivoDTO;
import co.gov.corantioquia.models.dto.GuardarArchivoDTO;
import co.gov.corantioquia.models.dto.ListaArchivo;
import co.gov.corantioquia.repository.ConsultaArchivoRepository;
import co.gov.corantioquia.service.ConsultaObligaArchivoService;
import co.gov.corantioquia.utils.CoMerge;
import co.gov.corantioquia.utils.ConsultaObligaArchivoUtil;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConsultaObligaArchivoServiceImpl implements ConsultaObligaArchivoService {
    @Autowired
    private ConsultaArchivoRepository consultaArchivoRepository;

    public ConsultaObligaArchivoServiceImpl(ConsultaArchivoRepository consultaArchivoRepository) {
        this.consultaArchivoRepository = consultaArchivoRepository;
    }
    @Override
    public byte[] listarArchivos(Integer sequenceThirdParty, Integer sequenceThirdParty2)  {
        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        ListaArchivo listaArchivo = new ListaArchivo ();

        List<Object> listaDesdeSP = consultaArchivoRepository.consultaObligaArchivos(sequenceThirdParty, sequenceThirdParty2);

        List<ConsultaObligaArchivosDTO> lista = ConsultaObligaArchivoUtil.objectToConsultaObligaArchivosDTO(listaDesdeSP);
        listaArchivo.setConsultaObligaArchivos(lista);

        return CoMerge.merge(lista);
    }
    @Override
    public String guardarArchivo(GuardarArchivoDTO guardarArchivoDTO) {
        //Validar que no sea zip
        //Si es zip, entonces no guarda archivo en BD sino que lo guarda en servidor y en BD guardar la ruta donde se guardo
            //Generar nombre de archivo (ruta de archivo)
        return consultaArchivoRepository.guardarArchivo(guardarArchivoDTO);
    }

    @Override
    public String eliminarArchivo(EliminarArchivoDTO eliminarArchivoDTO) {
        String o = (String) consultaArchivoRepository.eliminarArchivo(eliminarArchivoDTO);
        return o;
    }
}
