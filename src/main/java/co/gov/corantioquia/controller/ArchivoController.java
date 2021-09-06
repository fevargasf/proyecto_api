package co.gov.corantioquia.controller;

import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.EliminarArchivoDTO;
import co.gov.corantioquia.models.dto.GuardarArchivoDTO;
import co.gov.corantioquia.service.ConsultaObligaArchivoService;
import co.gov.corantioquia.service.impl.ConsultaObligaArchivoServiceImpl;
import co.gov.corantioquia.utils.CloudMersiveFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/listar-archivos")
public class ArchivoController {

    @Autowired
    private ConsultaObligaArchivoService consultaObligaArchivoService;

    @GetMapping(value ="/consulta-archivos")
    @ResponseStatus(HttpStatus.OK)
    public void listarArchivos(
            HttpServletResponse response,
            @RequestParam(name = "sequenceThirdParty") Integer sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") Integer sequenceThirdParty2
    ) throws IOException {

        String mimeType = "application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

        byte[] inStream =  consultaObligaArchivoService.listarArchivos(sequenceThirdParty, sequenceThirdParty2);
        response.setContentLength(inStream.length);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }

    @PostMapping(value = "/guardar-archivo")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String guardarArchivo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("sequenceThirdParty2") Integer sequenceThirdParty2,
            @RequestParam("sequenceThirdParty") Integer sequenceThirdParty,
            @RequestParam("line") String line,
            @RequestParam("coorX") String coorX,
            @RequestParam("coorY") String coorY,
            @RequestParam("fechaFoto") Date fechaFoto
            ) throws IOException, SQLException {
        GuardarArchivoDTO dto = new GuardarArchivoDTO();
        dto.setArchivo(new SerialBlob(file.getBytes()));
        dto.setCoorX(Double.parseDouble(coorX));
        dto.setCoorY(Double.parseDouble(coorY));
        dto.setFechaFoto(fechaFoto);
        dto.setLine(Integer.parseInt(line));
        dto.setSequenceThirdParty(sequenceThirdParty);
        dto.setSequenceThirdParty2(sequenceThirdParty2);
        dto.setNombreArchivo(file.getOriginalFilename());

        return this.consultaObligaArchivoService.guardarArchivo(dto);
    }

    @DeleteMapping(value = "/borrar-archivo")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String eliminarArchivo(
            @RequestParam(name = "niLineaObligacion") int niLineaObligacion,
            @RequestParam(name="niNroArchivo") int niNroArchivo,
            @RequestParam(name = "sequenceThirdParty") int sequenceThirdParty,
            @RequestParam(name = "sequenceThirdParty2") int sequenceThirdParty2
           ) {
            EliminarArchivoDTO dto = new EliminarArchivoDTO();
            dto.setNiLineaObligacion(niLineaObligacion);
            dto.setNiNroArchivo(niNroArchivo);
            dto.setSequenceThirdParty(sequenceThirdParty);
            dto.setSequenceThirdParty2(sequenceThirdParty2);
        return (String) consultaObligaArchivoService.eliminarArchivo(dto);
    }

    @GetMapping("/cloud")
    public void cloud(@RequestParam("file") MultipartFile file,HttpServletResponse response
                      ) throws IOException {
        String mimeType = "application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

        byte[] inStream = CloudMersiveFiles.httpCloudMersive(file.getOriginalFilename(),file.getBytes());
        response.setContentLength(inStream.length);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
}

