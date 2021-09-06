package co.gov.corantioquia.utils;

import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import jcifs.smb.SmbFile;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaObligaArchivoUtil {
    public static List<ConsultaObligaArchivosDTO> objectToConsultaObligaArchivosDTO(List<Object> listaDesdeSP){
        List<ConsultaObligaArchivosDTO> lista = new ArrayList<>();

        for (Object object : listaDesdeSP) {
            Object[] archivoAux= (Object[]) object;

            ConsultaObligaArchivosDTO consulta = new ConsultaObligaArchivosDTO();

            consulta.setExpediente((String) archivoAux[1]);
            consulta.setObligaDescripcion((String) archivoAux[2]);
            consulta.setObligaObservacion((String) archivoAux[3]);
            consulta.setObligaFechaCreacion((Date) archivoAux[4]);
            consulta.setDocSecResol((BigDecimal) archivoAux[5]);
            consulta.setRadicadoResolucion((String) archivoAux[6]);
            consulta.setArchivoNro((BigDecimal) archivoAux[7]);
            consulta.setArchivoNombre((String) archivoAux[8]);
            consulta.setArchivo((Blob) archivoAux[9]);
            consulta.setFechaIngresoArchivo((Date) archivoAux[10]);
            consulta.setFechaFoto((Date) archivoAux[11]);
            consulta.setCoorX((BigDecimal) archivoAux[12]);
            consulta.setCoorY((BigDecimal) archivoAux[13]);


            lista.add(consulta);
        }
        return lista;
    }

    /**
     * Metodo que realiza la carga de un archivo a la ruta asignada al documento
     *
     **/
    public static void asociarArchivoDelDocumento(Documento documento){
        try{
            UnidadRed ur = new UnidadRed();
            SmbFile archivo = new SmbFile(ur.apuntarPath() + documento.getRuta(),ur.autenticar());
            ur.copiarBytesAUnidad(documento.getArchivo(),archivo);
        }catch(Exception e){
            //getUrl().setError(e);
            e.printStackTrace();
        }
    }
}
