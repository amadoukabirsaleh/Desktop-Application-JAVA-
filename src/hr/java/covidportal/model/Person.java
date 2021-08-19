package hr.java.covidportal.model;
import java.util.List;


public class Person {
    private String name;
    private String surname;
    private int age;
    private Country country;
    private Disease infectedWithDisease;
    private List<Person> contactedPersons;

    public static class Builder {
        private String name;
        private String surname;
        private int age;
        private Country country;
        private Disease infectedWithDisease;
        private List<Person> contactedPersons;

        public Builder(String name) {
            this.name = name;
        }
        public Builder withSurname(String surname){
            this.surname = surname;

            return this;  //By returning the builder each time, we can create a fluent interface.
        }


        public Builder withAge(int age){
            this.age = age;

            return this;
        }
        public Builder withCountry(Country country){
            this.country = country;

            return this;
        }
        public Builder withDisease(Disease disease){
            this.infectedWithDisease = disease;

            return this;
        }
        public Builder withContactedPersons(List<Person> contactedPersons){
            this.contactedPersons = contactedPersons;

            return this;
        }

        public Person build(){
            //Here we create the actual Person object, which is always in a fully initialised state when it's returned.
            Person person = new Person();  //Since the builder is in the Person class, we can invoke its private constructor.


            person.name=this.name;
            person.surname=this.surname;
            person.age=this.age;
            person.country=this.country;
            person.infectedWithDisease=this.infectedWithDisease;
            person.contactedPersons=this.contactedPersons;



            return person;
        }




    }
   public Person(){
  // TO BE REMOVED
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
