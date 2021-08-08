package hr.java.covidportal.model;

public class Symptom extends NamedEntity{


    private String value;

    public Symptom(String name, String value) {
        super(name);
        this.value = value;
    }



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
