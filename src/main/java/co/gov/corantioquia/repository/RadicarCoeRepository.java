package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.RadicarDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;

@Repository
public class RadicarCoeRepository {

    private final EntityManager entityManager;

    private static final String RADICAR_COE = "sirena.pks_autogestion_sgmto.terminar";

    public RadicarCoeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public RadicarDTO radicar(RadicarDTO radicarDTO) {


        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(RADICAR_COE);
        storedProcedureQuery.registerStoredProcedureParameter("niSecExpe", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("noSecDocCoe", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voRadicadoCoe", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("doRadicado", Date.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voRutaCoe", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("niSecExpe", radicarDTO.getSequenceThirdParty2());
        storedProcedureQuery.setParameter("niSecTer", radicarDTO.getSequenceThirdParty());



        storedProcedureQuery.execute();


        radicarDTO.setNoSecDocCoe(String.valueOf(storedProcedureQuery.getOutputParameterValue("noSecDocCoe")));;
        radicarDTO.setVoRadicadoCoe(String.valueOf(storedProcedureQuery.getOutputParameterValue("voRadicadoCoe")));
        radicarDTO.setDoRadicado(String.valueOf(storedProcedureQuery.getOutputParameterValue("doRadicado")));
        radicarDTO.setVoRutaCoe(String.valueOf( storedProcedureQuery.getOutputParameterValue("voRutaCoe")));
        radicarDTO.setVoError(String.valueOf(storedProcedureQuery.getOutputParameterValue("voError")));

        entityManager.close();
        return radicarDTO;

    }
}
