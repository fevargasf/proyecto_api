package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.EliminarArchivoDTO;
import co.gov.corantioquia.models.dto.GuardarArchivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class ConsultaArchivoRepository {

    private final EntityManager entityManager;

    private String spConsultaObligaArchivos = "sirena.pks_autogestion_sgmto.consulta_obliga_archivos";
    private String GUARDAR_ARCHIVO = "sirena.pks_autogestion_sgmto.guardar_archivo";
    private String ELIMINAR_ARCHIVO = "sirena.pks_autogestion_sgmto.borrar_archivo";
    @Autowired
    public ConsultaArchivoRepository(EntityManager entity) {
        this.entityManager = entity;
    }

    public List<Object> consultaObligaArchivos(Integer sequenceThirdParty, Integer sequenceThirdParty2) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(spConsultaObligaArchivos);

        storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("coResultados", ConsultaObligaArchivosDTO.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("niSecTer", sequenceThirdParty);
        storedProcedureQuery.setParameter("niSecExp", sequenceThirdParty2);

        storedProcedureQuery.execute();



        return storedProcedureQuery.getResultList();
    }

    public String guardarArchivo(GuardarArchivoDTO guardarArchivoDTO){

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(GUARDAR_ARCHIVO);
        storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niLineaObligacion", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viNombre", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("bliArchivo", Blob.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("diFoto", Date.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niCoordX", Double.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niCoordY", Double.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("noNroArchivo", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("niSecExp", guardarArchivoDTO.getSequenceThirdParty2());
        storedProcedureQuery.setParameter("niSecTer", guardarArchivoDTO.getSequenceThirdParty());
        storedProcedureQuery.setParameter("niLineaObligacion", guardarArchivoDTO.getLine());
        storedProcedureQuery.setParameter("viNombre", guardarArchivoDTO.getNombreArchivo());
        storedProcedureQuery.setParameter("bliArchivo", guardarArchivoDTO.getArchivo());
        storedProcedureQuery.setParameter("diFoto", guardarArchivoDTO.getFechaFoto());
        storedProcedureQuery.setParameter("niCoordX", guardarArchivoDTO.getCoorX());
        storedProcedureQuery.setParameter("niCoordY", guardarArchivoDTO.getCoorY());


        storedProcedureQuery.execute();


        Integer noNroArchivo = (Integer) storedProcedureQuery.getOutputParameterValue("noNroArchivo");

        entityManager.close();

        return String.valueOf(noNroArchivo);

    }

    public void save(Integer sequenceThirdParty2,
                     Integer sequenceThirdParty,
                     Integer line ,
                     String nombreObligacion,
                     Blob archivo,
                     Date fechaIngresoArchivo,
                     Date fechaFoto,
                     BigDecimal coorX,
                     BigDecimal coorY){ }

    public String eliminarArchivo(EliminarArchivoDTO eliminarArchivoDTO) {


        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(ELIMINAR_ARCHIVO);
        storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niLineaObligacion", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niNroArchivo", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("niSecExp", eliminarArchivoDTO.getSequenceThirdParty2());
        storedProcedureQuery.setParameter("niSecTer", eliminarArchivoDTO.getSequenceThirdParty());
        storedProcedureQuery.setParameter("niLineaObligacion", eliminarArchivoDTO.getNiLineaObligacion());
        storedProcedureQuery.setParameter("niNroArchivo", eliminarArchivoDTO.getNiNroArchivo());
        storedProcedureQuery.execute();




        entityManager.close();

        return "ok";

    }

}
