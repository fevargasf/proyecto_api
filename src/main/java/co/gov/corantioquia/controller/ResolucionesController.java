package co.gov.corantioquia.controller;

import co.gov.corantioquia.service.ResolucionesService;
import co.gov.corantioquia.models.dto.ListaResoluciones;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resoluciones")
public class ResolucionesController {

    private ResolucionesService resolucionesService;

    public ResolucionesController(ResolucionesService resolucionesService) {
        this.resolucionesService = resolucionesService;
    }

    @PostMapping(value = "/lista-resoluciones")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ListaResoluciones listaResoluciones(@RequestParam(name = "sequenceThirdParty2") Integer sequenceThirdParty2) {
        return this.resolucionesService.listarResoluciones(sequenceThirdParty2);
    }


}
