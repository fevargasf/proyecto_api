package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.ResolutionDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class SelfManagementRepository {

    private final EntityManager entityManager;


    private String splistarResoluciones = "sirena.pks_autogestion_sgmto.consulta_resoluciones";


    public SelfManagementRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    public List<Object> listarResoluciones(Integer sequenceThirdParty2) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(splistarResoluciones);

        storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("coResultados", ResolutionDTO.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("niSecExp", sequenceThirdParty2);

        storedProcedureQuery.execute();

        return storedProcedureQuery.getResultList();
    }



}