package hr.java.covidportal.model;

import java.io.LineNumberInputStream;
import java.util.List;

public class Person {

    private String name;
    private String surname;
    private int age;
    private Country country;
    private Disease infectedWithDisease;
    private List<Person> contactedPersons;
    public static int instanceCount =0;
    public Person(){

    }

    public Person(String name, String surname, int age, Country country, Disease infectedWithDisease, List<Person> contactedPersons) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.country = country;
        this.infectedWithDisease = infectedWithDisease;
        this.contactedPersons = contactedPersons;
        instanceCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Disease getInfectedWithDisease() {
        return infectedWithDisease;
    }

    public void setInfectedWithDisease(Disease infectedWithDisease) {
        this.infectedWithDisease = infectedWithDisease;
    }

    public List<Person> getContactedPersons() {
        return contactedPersons;
    }

    public void setContactedPersons(List<Person> contactedPersons) {
        this.contactedPersons = contactedPersons;
    }



}
