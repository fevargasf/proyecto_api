package co.gov.corantioquia.models.dto;

public class EliminarObligacionDTO extends BaseDTO {

    public int niLinea;
    public  String voError;

    public String getVoError() {
        return voError;
    }

    public void setVoError(String voError) {
        this.voError = voError;
    }

    public int getniLinea() {
        return niLinea;
    }

    public void setniLinea(Integer niLinea) {
        this.niLinea = niLinea;
    }
}
