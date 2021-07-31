package hr.java.covidportal.model;

public class Country {

    private String name;
    private int inhabitants;

    public Country(String name, int inhabitants) {
        this.name = name;
        this.inhabitants = inhabitants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(int inhabitants) {
        this.inhabitants = inhabitants;
    }
}
