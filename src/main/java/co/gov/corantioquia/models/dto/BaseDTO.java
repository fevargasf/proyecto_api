package co.gov.corantioquia.models.dto;

public class BaseDTO {

    private int sequenceThirdParty;
    private int sequenceThirdParty2;
   /* private int sequenceThirdParty;
    private int sequenceThirdParty2;*/

    public  int getSequenceThirdParty() {return sequenceThirdParty;}

    public void setSequenceThirdParty(int sequenceThirdParty) {
        this.sequenceThirdParty = sequenceThirdParty;
    }

    public int getSequenceThirdParty2() {
        return sequenceThirdParty2;
    }

    public void setSequenceThirdParty2(int sequenceThirdParty2) {
        this.sequenceThirdParty2 = sequenceThirdParty2;
    }
}
