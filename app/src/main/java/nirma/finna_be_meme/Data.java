package nirma.finna_be_meme;

import java.sql.Date;

/**
 * Created by rajiv on 10/26/2015.
 */
public class Data {
    int Patient_id;
    String Patient_name;

    public Data() {

    }

    public Data(int Patient_id, String Patient_name) {
        this.Patient_id = Patient_id;
        this.Patient_name = Patient_name;
    }

    public int getPatient_id() {
        return this.Patient_id;
    }

    public String getPatient_name() {
        return this.Patient_name;
    }

    public void setPatient_id(int Patient_id) {
        this.Patient_id = Patient_id;
    }

    public void setPatient_name(String Patient_name) {
        this.Patient_name = Patient_name;
    }
}
