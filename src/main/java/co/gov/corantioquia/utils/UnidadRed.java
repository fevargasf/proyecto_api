package co.gov.corantioquia.utils;

//import conseres.generico.PropApl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;


public class UnidadRed
{
    private String user;
    private String password;
    private String dominio;
    private String host;
    private String unidad;
    private String ip;

    public UnidadRed()
    {

    }

    public void setIp (String ip)                 { this.ip = ip;  }
    public void setUser (String user)             { this.user = user; }
    public void setPassword (String password)     { this.password = password; }
    public void setDominio (String dom)           { this.dominio = dom; }
    public void setHost (String host)             { this.host = host; }
    public void setUnidad (String unidad)         { this.unidad = unidad; }

    public void conectar() throws Exception{
        try{
			/*PropertyResourceBundle resb = (PropertyResourceBundle)
							ResourceBundle.getBundle("properties.unidad");*/
            setHost(PropApl.getInstance().get("HOST_DIR_DOCUNET"));
            setIp(PropApl.getInstance().get("IP_DIR_DOCUNET"));
            setUser(PropApl.getInstance().get("USUARIO_DIR_DOCUNET"));
            setPassword(PropApl.getInstance().get("PASS_DIR_DOCUNET"));
            setDominio(PropApl.getInstance().get("DOMINIO_DIR_DOCUNET"));
            setUnidad(PropApl.getInstance().get("RUTA_DIR_DOCUNET"));
            jcifs.Config.setProperty(this.host,this.ip);

            jcifs.Config.setProperty( "jcifs.smb.client.domain", this.dominio);
            jcifs.Config.setProperty( "jcifs.netbios.wins", this.ip );
            jcifs.Config.setProperty( "jcifs.smb.client.username", this.user);
            jcifs.Config.setProperty( "jcifs.smb.client.password", this.password );
            jcifs.Config.registerSmbURLHandler();
        }
        catch (Exception e) {
            throw e;
        }
    }
    public NtlmPasswordAuthentication autenticar(){
        return new NtlmPasswordAuthentication(this.dominio, this.user, this.password);
    }
    public String apuntarPath () throws Exception
    {
        conectar();
        String opath = "smb://" + this.host + "/" + this.unidad + "/";
        if(PropApl.getInstance().isDebug()){
            System.out.println(opath);
        }
        return opath;
    }
    public void copiarFicheroDesdeUnidad (SmbFile in, File out)
    {
        SmbFileInputStream fis = null;
        FileOutputStream fos = null;

        try
        {
            fis = new SmbFileInputStream(in);
            fos = new FileOutputStream(out);
            byte[] buf = new byte[1024];

            int i = 0;
            while ((i=fis.read(buf)) != -1)
            {
                fos.write(buf, 0, i);
            }
        }
        catch (Exception e) {}
        finally
        {
            try
            {
                fis.close();
                fos.close();
            }
            catch (Exception e) {};
        }
    }
    public void obtenerFicheroDesdeUnidad (SmbFile in, OutputStream fos)
    {
        SmbFileInputStream fis = null;
        try
        {
            fis = new SmbFileInputStream(in);
            byte[] buf = new byte[1024];

            int i = 0;
            while ((i=fis.read(buf)) != -1)
            {
                fos.write(buf, 0, i);
            }
        }
        catch (Exception e) {}
        finally
        {
            try
            {
                fis.close();
                fos.close();
            }
            catch (Exception e) {};
        }
    }
    public void copiarBytesAUnidad (byte[] archivo, SmbFile out) throws Exception
    {
        SmbFileOutputStream fos = null;
        SmbFile f = new SmbFile(out.getParent());
        if(!f.exists()){
            f.mkdirs();
        }
        try
        {
            fos = new SmbFileOutputStream(out);

            fos.write(archivo);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (Exception e) {
                throw e;
            };
        }
    }
    public void copiarFicheroAUnidad (File in, SmbFile out)
    {
        FileInputStream fis = null;
        SmbFileOutputStream fos = null;

        try
        {
            fis = new FileInputStream(in);
            fos = new SmbFileOutputStream(out);
            byte[] buf = new byte[1024];

            int i = 0;
            while ((i=fis.read(buf)) != -1)
            {
                fos.write(buf, 0, i);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fis.close();
                fos.close();
            }
            catch (Exception e) {};
        }
    }
	/*public static void main(String[] arr){
		SmbFile archivo = null;

		String fichero = "";

		try
		{
			UnidadRed unidadRed = new UnidadRed();
			unidadRed.copiarFicheroAUnidad(new File("C:/Nicolas/0018.tiff"),new SmbFile(unidadRed.apuntarPath() + fichero));
			System.out.println(unidadRed.apuntarPath() + fichero);
			archivo = new SmbFile(unidadRed.apuntarPath() + fichero);
			if(archivo.isDirectory()){
				for(int i=0; i< archivo.list().length;i++){
					System.out.println(archivo.list()[i]);
				}

			}
		}

		catch (Exception e)
		{
					e.printStackTrace();
		}
	}*/
}
