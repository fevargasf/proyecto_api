package co.gov.corantioquia.controller;


import co.gov.corantioquia.models.dto.EliminarObligacionDTO;
import co.gov.corantioquia.models.dto.GuardarObligacionRequest;
import co.gov.corantioquia.models.dto.GuardarObligacionResponse;
import co.gov.corantioquia.models.dto.ListaObligaciones;
import co.gov.corantioquia.service.ObligationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/obligations")
public class ObligationController {

    @Autowired
    private ObligationService obligationService;

    @PostMapping(value = "/guardar-obligacion")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    GuardarObligacionResponse guardarObligacion(@RequestBody GuardarObligacionRequest guardarObligacionRequest) {
        return obligationService.guardarObligacion(guardarObligacionRequest);
    }

    @GetMapping(value = "/consultar-obligacion")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ListaObligaciones listaObligaciones(
            @RequestParam(name = "sequenceThirdParty") Integer sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") Integer sequenceThirdParty2
    ) {
        return obligationService.listaObligaciones(sequenceThirdParty,sequenceThirdParty2);
    }

    @CrossOrigin("*")
    @DeleteMapping(value = "/borrar-obligacion")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String eliminarObligacion(
            @RequestParam(name = "niLinea") int niLinea,
            @RequestParam(name = "sequenceThirdParty") int sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") int sequenceThirdParty2
    ) {
        EliminarObligacionDTO dto = new EliminarObligacionDTO();
        dto.setniLinea(niLinea);
        dto.setSequenceThirdParty(sequenceThirdParty);
        dto.setSequenceThirdParty2(sequenceThirdParty2);

        return (String) obligationService.eliminarObligacion(dto);
    }

}