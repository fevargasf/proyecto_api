package co.gov.corantioquia.models.dto;

public class ConexionDTO {

    private  String coraconn;
    private  String noSecTercero;
    private  String voDocTercero;
    private  String voNomTercero;
    private  String voEMail;

    public String getCoraconn() {
        return coraconn;
    }

    public void setCoraconn(String coraconn) {
        this.coraconn = coraconn;
    }

    public String getNoSecTercero() {
        return noSecTercero;
    }

    public void setNoSecTercero(String noSecTercero) {
        this.noSecTercero = noSecTercero;
    }

    public String getVoDocTercero() {
        return voDocTercero;
    }

    public void setVoDocTercero(String voDocTercero) {
        this.voDocTercero = voDocTercero;
    }

    public String getVoNomTercero() {
        return voNomTercero;
    }

    public void setVoNomTercero(String voNomTercero) {
        this.voNomTercero = voNomTercero;
    }

    public String getVoEMail() {
        return voEMail;
    }

    public void setVoEMail(String voEMail) {
        this.voEMail = voEMail;
    }
}
