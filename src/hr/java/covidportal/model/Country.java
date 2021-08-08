package hr.java.covidportal.model;

public class Country extends NamedEntity{


    private int inhabitants;

    public Country(String name, int inhabitants) {
        super(name);
        this.inhabitants = inhabitants;
    }



    public int getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(int inhabitants) {
        this.inhabitants = inhabitants;
    }
}
