package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.ResponsableDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Repository
public class CrearInicialRepository {


    private final EntityManager entityManager;

    private static final String CREAR_INICIAL = "sirena.pks_autogestion_sgmto.crear_inicial";

    public CrearInicialRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String crearInicial(ResponsableDTO responsableDTO) {
        String voerror = null;

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(CREAR_INICIAL);
        storedProcedureQuery.registerStoredProcedureParameter("niSecExpe", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viNomResponsable", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viCargo", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viCorreoRespo", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viTelRespoFijo", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viTelRespoCelu", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("niSecExpe", responsableDTO.getSequenceThirdParty2());
        storedProcedureQuery.setParameter("niSecTer", responsableDTO.getSequenceThirdParty());
        storedProcedureQuery.setParameter("viNomResponsable", responsableDTO.getNombre());
        storedProcedureQuery.setParameter("viCargo", responsableDTO.getCargo());
        storedProcedureQuery.setParameter("viCorreoRespo", responsableDTO.getCorreo());
        storedProcedureQuery.setParameter("viTelRespoFijo", responsableDTO.getTelefonoFijo());
        storedProcedureQuery.setParameter("viTelRespoCelu", responsableDTO.getTelefonoCelular());

        storedProcedureQuery.execute();

        voerror = (String) storedProcedureQuery.getOutputParameterValue("voError");

        entityManager.close();
        return voerror;

    }

    public void save(Integer sequenceThirdParty2, Integer sequenceThirdParty, String responsable, String cargo, String email, String phone, String mobile) {
    }
}



