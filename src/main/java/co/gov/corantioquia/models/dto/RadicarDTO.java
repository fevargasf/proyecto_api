package co.gov.corantioquia.models.dto;

import java.util.Date;

public class RadicarDTO extends BaseDTO {
    private String noSecDocCoe;
    private String voRadicadoCoe;
    private String doRadicado;
    private String voRutaCoe;
    private String voError;

    public String getNoSecDocCoe() {
        return noSecDocCoe;
    }

    public void setNoSecDocCoe(String noSecDocCoe) {
        this.noSecDocCoe = noSecDocCoe;
    }

    public String getVoRadicadoCoe() {
        return voRadicadoCoe;
    }

    public void setVoRadicadoCoe(String voRadicadoCoe) {
        this.voRadicadoCoe = voRadicadoCoe;
    }

    public String getDoRadicado() {
        return doRadicado;
    }

    public void setDoRadicado(String doRadicado) {
        this.doRadicado = doRadicado;
    }

    public String getVoRutaCoe() {
        return voRutaCoe;
    }

    public void setVoRutaCoe(String voRutaCoe) {
        this.voRutaCoe = voRutaCoe;
    }

    public String getVoError() {return voError;}

    public void setVoError(String voError) {this.voError = voError;}
}
