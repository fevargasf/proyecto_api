package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.ResponsableDTO;
import co.gov.corantioquia.service.CrearInicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save-PDF")
public class CrearInicialController {

    @Autowired
    private CrearInicialService crearInicialService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String crearInicial(@RequestBody ResponsableDTO responsableDTO) {
        return crearInicialService.crearInicial(responsableDTO);
    }
}







