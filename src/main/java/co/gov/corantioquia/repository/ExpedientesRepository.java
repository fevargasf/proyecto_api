package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.ExpedientDTO;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ExpedientesRepository {

    private final EntityManager entityManager;


    private static final String LISTAR_EXPEDIENTE = "sirena.pks_autogestion_sgmto.lista_expedientes";
    private static final String CONSULTAR_EXPEDIENTE = "sirena.pks_autogestion_sgmto.consulta_expediente";



    public ExpedientesRepository (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object> listarExpedientes(Integer sequenceThirdParty) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(LISTAR_EXPEDIENTE);

        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("coResultados", Object.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("niSecTer", sequenceThirdParty);

        storedProcedureQuery.execute();

        return storedProcedureQuery.getResultList();
    }

    public List<Object> consultarExpedientes(Integer sequenceThirdParty, Integer sequenceThirdParty2) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(CONSULTAR_EXPEDIENTE);

        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("coResultados", ExpedientDTO.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("niSecTer", sequenceThirdParty);
        storedProcedureQuery.setParameter("niSecExp", sequenceThirdParty2);

        storedProcedureQuery.execute();

        return storedProcedureQuery.getResultList();
    }
}
