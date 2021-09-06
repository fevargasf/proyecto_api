package co.gov.corantioquia.utils;

public class Documento {
    private String ruta;
    private byte[] archivo;

    public Documento(String ruta, byte[] archivo){
        this.ruta = ruta;
        this.archivo = archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
}
