package co.gov.corantioquia.utils;

import co.gov.corantioquia.models.dto.ConsultaObligaArchivosDTO;
import co.gov.corantioquia.models.dto.RadicarPdfDTO;
import com.google.common.io.Files;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.web.client.RestTemplate;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoMerge{

    public static byte[] merge(RadicarPdfDTO radicarPdfDTO) {
        List<InputStream> listaArchivos = new ArrayList<>();
        try {
            listaArchivos = validarExtension(radicarPdfDTO.getArchivos());
        } catch (SQLException | IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        byte[] pdf = new byte[4];
        try {
            pdf = construirPortada(radicarPdfDTO);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        byte[] files = new byte[4];
        if(listaArchivos.size()>0) {
            try {
                files = mezclarArchivos(listaArchivos);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }


            try {
                return mezclarArchivosByte(pdf, files);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                return new byte[4];
            }
        }else{
            return pdf;
        }
    }
    public static byte[] merge(List<ConsultaObligaArchivosDTO> archivos) {

        // List<Blob> listaArchivos = validarExtension(archivos);
        List<InputStream> listaArchivos = new ArrayList<>();
        try {
            listaArchivos = validarExtension(archivos);
        } catch (SQLException | IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }



        byte[] files = new byte[4];
        if(listaArchivos.size()>0){
            try {
                files = mezclarArchivos(listaArchivos);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        return files;
    }

    public static List<InputStream> validarExtension(List<ConsultaObligaArchivosDTO> archivos) throws SQLException, IOException {
        List<InputStream> listaArchivos = new ArrayList<>();

        boolean api = false;
        for (ConsultaObligaArchivosDTO dto : archivos) {
            // Validar que solo guarde tipo PDF. Validar extension
            if (dto.getArchivo() != null && dto.getArchivoNombre() !=null) {

               // String[] ext = dto.getArchivoNombre().split("\\.");
                String extension = Files.getFileExtension(dto.getArchivoNombre());

                switch (extension) {
                    case "pdf":
                        listaArchivos.add(dto.getArchivo().getBinaryStream());

                        // Si otra, convertir a PDF
                        // llamarAPI()
                       /* if (!api) {
                            byte[] file = Cloudmersive.documentToPdf(dto.getArchivo().getBinaryStream());
                            listaArchivos.add(new ByteArrayInputStream(file));
                            api = true;
                        }*/
                        break;

                    case "zip":

                        break;

                    default:
                        // Si otra, convertir a PDF
                        // llamarAPI()
                        /*
                       if (!api) {
                            byte[] file = Cloudmersive.documentToPdf(dto.getArchivo().getBinaryStream());
                            listaArchivos.add(new ByteArrayInputStream(file));
                            api = true;
                        }
                        */

                        break;
                }
            }
        }
        return listaArchivos;
    }

    public static void llamarAPI(Blob blob) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://dominio.com";

        Object response = restTemplate.getForObject(url, Class.class);
    }


    public static byte[] construirPortada(RadicarPdfDTO radicarPdfDTO) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document doc = new Document(pdfDoc);
        int contar= radicarPdfDTO.getArchivos().size();
        doc.add(new Paragraph("CORANTIOQUIA"));
        doc.add(new Paragraph("COMUNICACIONES OFICIALES EXTERNAS\n"));
        doc.add(new Paragraph("Anexos :"+contar));
        doc.add(new Paragraph("Radicado  :"+radicarPdfDTO.getRadicado()));//radicarcoe voRadicadoCoe doRadicado
        doc.add(new Paragraph("Nombre Responsable:" +radicarPdfDTO.getExpedientDTO().getRespon_nombre()));//crear inicial
        doc.add(new Paragraph("Email del responsable:"+radicarPdfDTO.getExpedientDTO().getRespon_correo()));//crear inicial
        doc.add(new Paragraph("Teléfono del responsable:"+radicarPdfDTO.getExpedientDTO().getTer_celular()));//crear inicial
        doc.add(new Paragraph("Cargo del responsable:"+radicarPdfDTO.getExpedientDTO().getRespon_cargo()));//crear inicial
        doc.add(new Paragraph("Municipio:"+radicarPdfDTO.getExpedientDTO().getTer_dir_municipio()));//crear inicial
        doc.add(new Paragraph("Fecha de diligenciamiento:"+radicarPdfDTO.getConsultaObligaArchivosDTO().getObligaFechaCreacion()));//crear inicial
        doc.add(new Paragraph("Señores Corantioquia,\n" +
                " A continuación, se reporta el estado de las obligaciones cumplidas dentro del trámite del\n" +
                "expediente"+""+radicarPdfDTO.getExpedientDTO().getExpediente()));//crear inicial
        doc.add(new Paragraph("Obligaciones del usuario"+radicarPdfDTO.getExpedientDTO().getRespon_nombre()+" de la autogestión de control y seguimiento:"));//crear inicial
        doc.add(new Paragraph("Número de la resolución :"+radicarPdfDTO.getConsultaObligaArchivosDTO().getDocSecResol()));//crear inicial
        doc.add(new Paragraph("Nota :Manifiesto que la información consignada y anexa a este formulario es veraz, por tanto, la Corporación \n"
                                    + "podrá verificarla en cualquier momento. "
                                   + " Entiendo que toda información que resulte contraria a la realidad podrá conllevar consecuencias \n"
                                           +" legales, por  lo que me comprometo a reportar las novedades que se presenten."));


        construirListaObligaciones(doc, radicarPdfDTO.getArchivos());

        doc.close();

        byte[] b;

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        baos.writeTo(os);

        b = baos.toByteArray();
        os.flush();
        os.close();

        return b;
    }

    public static void construirListaObligaciones(Document document, List<ConsultaObligaArchivosDTO> listaConsultaObligaArchivosDTO){
        int obligacionActual = 0;

        for (ConsultaObligaArchivosDTO dto: listaConsultaObligaArchivosDTO){
            if(obligacionActual!=dto.getObligaLinea()){
                //Cree encabezado de obligacion
                document.add(new Paragraph(""));
                document.add(new Paragraph(dto.getObligaDescripcion()));
                document.add(new Paragraph(dto.getObligaObservacion()));
                document.add(new Paragraph(dto.getCoorX().toString()));
                document.add(new Paragraph(dto.getCoorY().toString()));
                document.add(new Paragraph(dto.getFechaFoto().toString()));
                document.add(new Paragraph(""));
                obligacionActual = dto.getObligaLinea();
            }
            if(dto.getArchivoNombre() != null){
                document.add(new Paragraph(dto.getArchivoNombre()));
            }
        }
    }

    public static byte[] mezclarArchivos(List<InputStream> listaArchivos) throws SQLException, IOException {
        ByteArrayOutputStream destino = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(destino));
        PdfMerger merger = new PdfMerger(pdf);

        PdfDocument blobPdf;
        for (InputStream blobStream : listaArchivos) {
            // crear pdfDocument
            blobPdf = new PdfDocument(new PdfReader(blobStream));
            // mezclar
            try {
                merger.merge(blobPdf, 1, blobPdf.getNumberOfPages());
            }catch (Exception e){
                //validar pdf con clave
            }


            blobPdf.close();
            blobStream.close();
        }

        pdf.close();
        // destino.close();

        return destino.toByteArray();
    }

    public static byte[] mezclarArchivosByte(byte[] archivo1, byte[] archivo2) throws IOException {
        ByteArrayOutputStream destino = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(destino));
        PdfMerger merger = new PdfMerger(pdf);

        InputStream isArchivo1 = new ByteArrayInputStream(archivo1);
        PdfDocument blobPdf = new PdfDocument(new PdfReader(isArchivo1));
        merger.merge(blobPdf, 1, blobPdf.getNumberOfPages());

        InputStream isArchivo2 = new ByteArrayInputStream(archivo2);
        PdfDocument blobPdf2 = new PdfDocument(new PdfReader(isArchivo2));
        merger.merge(blobPdf2, 1, blobPdf2.getNumberOfPages());

        blobPdf2.close();
        blobPdf.close();
        pdf.close();

        return destino.toByteArray();
    }
}

