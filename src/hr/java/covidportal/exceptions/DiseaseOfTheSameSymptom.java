package hr.java.covidportal.exceptions;

public class DiseaseOfTheSameSymptom extends RuntimeException{
    public DiseaseOfTheSameSymptom (){
        super();
    }
    public DiseaseOfTheSameSymptom (String message){
        super(message);
    }
    public DiseaseOfTheSameSymptom (Throwable cause) {
        super (cause);
    }
    public DiseaseOfTheSameSymptom(String message, Throwable cause) {
        super (message, cause);
    }
}
