package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.ConsultaObDTO;
import co.gov.corantioquia.models.dto.EliminarObligacionDTO;
import co.gov.corantioquia.models.dto.GuardarObligacionRequest;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class GuardarObligacionRepository {

    private final EntityManager entityManager;

    private static final String GUARDAR_OBLIGACION = "sirena.pks_autogestion_sgmto.guardar_obligacion";
    private static final String CONSULTAR_OBLIGACION = "sirena.pks_autogestion_sgmto.consulta_obligaciones";
    private static final String ELIMINAR_OBLIGACION = "sirena.pks_autogestion_sgmto.borrar_obligacion";

    public GuardarObligacionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public  String guardarObligacion(GuardarObligacionRequest guardarObligacionRequest){
       Integer linea = guardarObligacionRequest.getLinea()>0? guardarObligacionRequest.getLinea():null;
        String voerror = null;


        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(GUARDAR_OBLIGACION );
        storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("nioLinea", Integer.class, ParameterMode.INOUT);

        storedProcedureQuery.registerStoredProcedureParameter("niSecResol", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viDescripcion", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viObservacion", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("niSecExp", guardarObligacionRequest.getSequenceThirdParty2());
        storedProcedureQuery.setParameter("niSecTer", guardarObligacionRequest.getSequenceThirdParty());
        storedProcedureQuery.setParameter("niSecResol", guardarObligacionRequest.getSecResol());
        storedProcedureQuery.setParameter("nioLinea", linea);

        storedProcedureQuery.setParameter("viDescripcion", guardarObligacionRequest.getDescripcion());
        storedProcedureQuery.setParameter("viObservacion", guardarObligacionRequest.getObservaciones());


        storedProcedureQuery.execute();


            linea = (Integer) storedProcedureQuery.getOutputParameterValue("nioLinea");
            voerror = (String) storedProcedureQuery.getOutputParameterValue("voError");


            entityManager.close();

                return String.valueOf(linea);


        }



        public void save(Integer sequenceThirdParty2, Integer sequenceThirdParty, Integer resolucion , String descripcion, String observacion, Integer numeroObligacion) {
    }




    public List<Object> consultarObligacion(Integer sequenceThirdParty, Integer sequenceThirdParty2) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(CONSULTAR_OBLIGACION);

        storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("coResultados", ConsultaObDTO.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter("niSecTer", sequenceThirdParty);
        storedProcedureQuery.setParameter("niSecExp", sequenceThirdParty2);

        storedProcedureQuery.execute();

        return storedProcedureQuery.getResultList();
    }

    public Object eliminarObligacion(EliminarObligacionDTO eliminarObligacionDTO) {


            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(ELIMINAR_OBLIGACION );
            storedProcedureQuery.registerStoredProcedureParameter("niSecExp", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("niSecTer", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("niLinea", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("voError", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("niSecExp", eliminarObligacionDTO.getSequenceThirdParty2());
            storedProcedureQuery.setParameter("niSecTer", eliminarObligacionDTO.getSequenceThirdParty());
            storedProcedureQuery.setParameter("niLinea", eliminarObligacionDTO.getniLinea());

            storedProcedureQuery.execute();
            entityManager.close();

        Object voError=  storedProcedureQuery.getOutputParameterValue("voError");
            return voError;

        }

    }


