package hr.java.covidportal.model;

public class Virus extends Disease implements Contagious{

    public Virus(String name, Symptom[] symptoms) {
        super(name, symptoms);
    }

    @Override
    public void transferInfectionToPerson(Person person) {
         person.setInfectedWithDisease(this);
    }
}


