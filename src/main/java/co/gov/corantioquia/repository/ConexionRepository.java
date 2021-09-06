package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.ConexionDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Repository
public class ConexionRepository {

    private final EntityManager entityManager;

    private static final String CONEXION = "sirena.pk_conexion.consultar_conexion";

    public ConexionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ConexionDTO  conexion(ConexionDTO conexionDTO) {


        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(CONEXION );
        storedProcedureQuery.registerStoredProcedureParameter("viIdCone", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("noSecTercero", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voDocTercero", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voNomTercero", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voEMail", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("viIdCone", conexionDTO.getCoraconn());

        storedProcedureQuery.execute();

        conexionDTO.setNoSecTercero(String.valueOf(storedProcedureQuery.getOutputParameterValue("noSecTercero")));
        conexionDTO.setVoDocTercero(String.valueOf(storedProcedureQuery.getOutputParameterValue("voDocTercero")));
        conexionDTO.setVoNomTercero(String.valueOf(storedProcedureQuery.getOutputParameterValue("voNomTercero")));
        conexionDTO.setVoEMail(String.valueOf(storedProcedureQuery.getOutputParameterValue("voEMail")));

        entityManager.close();
        return conexionDTO ;
    }
}
