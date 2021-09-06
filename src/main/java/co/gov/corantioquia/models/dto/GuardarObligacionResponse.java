package co.gov.corantioquia.models.dto;

public class GuardarObligacionResponse {

    private Boolean result = false;
    private String line;

    public GuardarObligacionResponse() {

    }

    public GuardarObligacionResponse(Boolean result, String line) {
        this.result = result;
        this.line = line;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
