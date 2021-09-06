package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.ConexionDTO;
import co.gov.corantioquia.service.ConexionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/coraconn")
public class ConexionController {


    private ConexionService conexionService;

    public ConexionController(ConexionService conexionService) {
        this.conexionService = conexionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ConexionDTO conexion(HttpServletRequest request, @RequestBody ConexionDTO conexionDTO) {
        return conexionService.conexion(conexionDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  @ResponseBody
    ConexionDTO cookie(HttpServletRequest request,HttpServletResponse response,@CookieValue(value = "coraconn") String coraconn) {

       ConexionDTO conexionDTO = new ConexionDTO();
       conexionDTO.setCoraconn(coraconn);
        conexionDTO = conexionService.conexion(conexionDTO);

        Cookie cookie = new Cookie("coraconn",coraconn);
        response.addCookie(cookie);

        String origin = request.getHeader("Origin");

        response.setHeader("Access-Control-Allow-Origin",
                origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,POST, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

        return conexionDTO;
    }

}
