package co.gov.corantioquia.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.util.Base64;


import com.cloudmersive.client.ConvertDocumentApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.util.IOUtils;

import javax.xml.crypto.OctetStreamData;

public class CloudMersiveFiles {

    public static byte[] documentToPdf(InputStream bytes) throws IOException {

        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure API key authorization: Apikey
        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("ddb9588d-2848-4b48-9a17-35ec436421e8");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token"
        // (defaults to null)
        // Apikey.setApiKeyPrefix("Token");
        File targetFile = new File("C:\\Users\\daniela bonilla\\Desktop\\temp\\targetFile.tmp");
        java.nio.file.Files.copy(bytes, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(bytes);

        ConvertDocumentApi apiInstance = new ConvertDocumentApi();

        try {
            byte[] result = apiInstance.convertDocumentAutodetectToPdf(targetFile);
            System.out.println(result);
            return result;
        } catch (ApiException e) {
            System.err.println("Exception when calling ConvertDocumentApi#convertDocumentDocToPdf");
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] httpCloudMersive(String fileName, byte[] fileToConvert) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost("https://api.cloudmersive.com/convert/autodetect/to/pdf");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
        //Add Headers
        /*
        *  "Content-Type": "multipart/form-data",
          "Apikey": "YOUR-API-KEY-HERE"
        * */
        uploadFile.addHeader("Content-Type", "multipart/form-data");
        uploadFile.addHeader("Apikey", PropApl.getInstance().get("APIKEY_CLOUDMERSIVE"));
// This attaches the file to the POST:

        builder.addBinaryBody(
                "file",
                fileToConvert,
                ContentType.APPLICATION_OCTET_STREAM,
                fileName
        );

        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();

       // OctetStreamData data = new OctetStreamData(responseEntity.getContent());

       // String responseOctet = IStoString(responseEntity.getContent());

        //return Base64.getDecoder().decode(responseOctet);
        return IOUtils.toByteArray(responseEntity.getContent());
    }

    public static String IStoString(InputStream is) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))){
            int c = 0;
            while((c = reader.read()) != -1){
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }
}
