package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.ExpedientDTO;
import co.gov.corantioquia.models.dto.ListaExpedientes;
import co.gov.corantioquia.service.ExpedienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expedientes")
public class ExpedientesController {

    private ExpedienteService  expedienteService;

    public ExpedientesController (ExpedienteService expedienteService) {
        this.expedienteService = expedienteService;
    }

    @PostMapping(value = "/lista-expedientes")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ListaExpedientes listaExpedientes(@RequestParam(name = "sequenceThirdParty") Integer sequenceThirdParty) {
        return this.expedienteService.listarExpedientes(sequenceThirdParty);
    }

    @PostMapping(value = "/consultar-expedientes")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ExpedientDTO consultarExpedientes(
            @RequestParam(name = "sequenceThirdParty") Integer sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") Integer sequenceThirdParty2
    ) {
        return this.expedienteService.consultarExpedientes(sequenceThirdParty, sequenceThirdParty2);
    }

}