package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.RadicarDTO;
import co.gov.corantioquia.models.dto.RadicarPdfDTO;
import co.gov.corantioquia.service.RadicarCoeService;
import co.gov.corantioquia.utils.CoMerge;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping(value="/finish")
public class RadicarCoeController {

    private RadicarCoeService radicarCoeService;
    public RadicarCoeController(RadicarCoeService radicarCoeService) {
        this.radicarCoeService = radicarCoeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    RadicarDTO radicar(@RequestBody RadicarDTO radicarDTO){
        return radicarCoeService.radicar(radicarDTO);
    }


    @PostMapping("/construir-pdf-radicado")
    public void construirPdfRadicado(HttpServletResponse response, @RequestBody RadicarPdfDTO radicarPdfDTO) throws IOException {
        //Consultar archivos
        //Consultar reponsable
        //Armar pdf con numero de radicado
        String mimeType = "application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

        byte[] inStream =  radicarCoeService.construirPdfRadicado(radicarPdfDTO);
        response.setContentLength(inStream.length);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }



  /*      @GetMapping("/download")
        public void downloadFile(HttpServletResponse response) throws IOException {
            CoMerge generator = new CoMerge();
            byte[] pdfReport = generator.merge().toByteArray();

            String mimeType =  "application/pdf";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

            response.setContentLength(pdfReport.length);

            ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

            FileCopyUtils.copy(inStream, response.getOutputStream());
        }
*/

}
