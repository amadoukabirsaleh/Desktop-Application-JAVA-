package hr.java.covidportal.model;

public class Disease extends NamedEntity{


    private Symptom [] symptoms;


    public Disease(String name, Symptom[] symptoms) {
        super(name);
        this.symptoms = symptoms;
    }





    public Symptom[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Symptom[] symptoms) {
        this.symptoms = symptoms;
    }
}
