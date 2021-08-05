package hr.java.covidportal.main;

import hr.java.covidportal.model.Country;
import hr.java.covidportal.model.Disease;
import hr.java.covidportal.model.Person;
import hr.java.covidportal.model.Symptom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int counter = 0;

    public static void main(String[] args) {
        Scanner us = new Scanner(System.in);
        Country[] countries = new Country[3];
        Symptom[] symptoms = new Symptom[3];
        Disease[] diseases = new Disease[3];
        Person[] persons = new Person[3]; // contains only the 3 main people entered by user
        List<Person> people = new ArrayList<>(); // contains all persons with their contacted persons

        System.out.println("Enter data for 3 countries, 3 symptoms, 3 diseases & 3 persons...");
/*


      //----------REQUESTING DATA OF COUNTRY----------------------
             System.out.println("--------------------------------");

        System.out.println("\nEnter data for 3 countries...");
        for(int i=0;i<3;i++){
            System.out.println("Insert "+(i+1)+".Country>>>");
            countries[i] = enterCountry(us);
        }

        //----------REQUESTING DATA OF SYMPTOMS----------------------
        System.out.println("\nEnter data for 3 symptoms...");
        System.out.println("--------------------------------");
        for(int i=0;i<3;i++){
            System.out.println("Insert "+(i+1)+".Sypmtom>>>");
            symptoms[i] = enterSymptom(us);
        }

    //----------REQUESTING DATA OF DISEASES----------------------
        System.out.println("\nEnter data for 3 diseases...");
        System.out.println("--------------------------------");
        for(int i=0;i<3;i++){
            System.out.println("Insert "+(i+1)+".Disease>>>");
            diseases[i] = enterDisease(us);
        }
*/
        //----------REQUESTING DATA OF PERSONS----------------------
        System.out.println("\nEnter data for 3 persons...");
        System.out.println("--------------------------------");
        boolean offerListOfPeople = false;// TO PRESENT TO THE USER THE LIST OF EXISTING PERSONS WHEN HE ENTERS DATA OF SECOND PERSON
        for (int i = 0; i < 3; i++) {
            System.out.println("Insert " + (i + 1) + ".Person>>>");
            persons[i] = enterPerson(us, people, offerListOfPeople);
            people.add(persons[i]);
            offerListOfPeople = true;

        }


    }


    //----------METHOD FOR ENTERING DATA OF COUNTRY----------------------
    public static Country enterCountry(Scanner scanner) {
        System.out.print("Enter country name:");
        String name = scanner.next();
        System.out.print("Enter country number of inhabitants:");
        int inhabitants = scanner.nextInt();
        scanner.nextLine();
        return new Country(name, inhabitants);
    }

    //----------METHOD FOR ENTERING DATA OF SYMPTOM----------------------
    public static Symptom enterSymptom(Scanner scanner) {
        System.out.print("Enter sypmtom name:");
        String name = scanner.next();
        System.out.print("Enter symptom value:");
        String value = scanner.next();
        return new Symptom(name, value);
    }

    //----------METHOD FOR ENTERING DATA OF DISEASE----------------------
    public static Disease enterDisease(Scanner scanner) {
        int i = 0;
        System.out.print("Enter disease name:");
        String name = scanner.next();
        System.out.println("Enter disease symptoms >>>");
        Symptom[] symptoms = new Symptom[6];
        symptoms[i] = enterSymptom(scanner);
        label:
        while (true) {


            System.out.println("Insert another symptom ? (Y/N)");
            char reply = scanner.next().charAt(0);
            if (reply == 'Y' || reply == 'y') {
                i++;
                symptoms[i] = enterSymptom(scanner);

            } else if (reply == 'N' || reply == 'n') {
                break;
            } else {
                System.out.print("Wrong input, try again...");
                continue label;

            }
        }

        return new Disease(name, symptoms);
    }

    //----------METHOD FOR ENTERING DATA OF PERSONS----------------------
    public static Person enterPerson(Scanner scanner, List<Person> people, boolean offerListOfPeople) {


        List<Person> contactedPersons = new ArrayList<>();


        System.out.print("Enter person name :");
        String name = scanner.next();
        System.out.print("Enter person surname:");
        String surname = scanner.next();
        System.out.print("Enter person age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter person country details >>>");
        Country country = enterCountry(scanner);
        System.out.println("Enter the details of the disease the person is infected with >>>");
        Disease disease = enterDisease(scanner);
        label:
        while (true) {
            if (counter == 0 && offerListOfPeople == false) {
                System.out.println("Is there a contacted person ? (Y/N)");
                char reply = scanner.next().charAt(0);
                if (reply == 'Y' || reply == 'y') {
                    counter++;
                    System.out.println("Enter the contacted person details >>>");
                    Person temp = new Person();
                    temp = contactedPersonDetails(scanner, temp);
                    contactedPersons.add(temp);
                    people.add(temp);

                } else if (reply == 'N' || reply == 'n') {
                    break;
                } else {
                    System.out.print("Wrong input, try again...");
                    continue label;

                }
            } else if (counter != 0 && counter <= 3 && offerListOfPeople) {
                System.out.println("Is there a contacted person ? (Y/N)");
                char reply = scanner.next().charAt(0);
                if (reply == 'Y' || reply == 'y') {
                    counter++;

                    // System.out.println ("Enter the contacted person details >>>");
                    System.out.println("Choose from this list below please...");
                    displayPersons(people);

                    label2:
                    while (true) {
                        System.out.println("Enter exact Name from the list above :");
                        String contactedName = scanner.next();
                        boolean checker = false;

                        for (Person p : people) {
                            if (p != null && p.getName().equals(contactedName)) {
                                contactedPersons.add(p);
                                checker = true;
                                break label2;
                            }

                        }
                        if (checker == false) {
                            System.out.print("Wrong input, try again...");
                            continue label2;
                        }
                    }


                } else if (reply == 'N' || reply == 'n') {
                    System.out.println("Data succesfully inserted...");
                    break;
                } else {
                    System.out.print("Wrong input, try again...");
                    continue label;

                }
            } else {
                System.out.println("Data succesfully inserted...\n");
                break;
            }
        }


        return new Person(name, surname, age, country, disease, contactedPersons);
    }









    /* ---------------------------------------HELPER METHODS FOR enterPerson METHOD-------------------------------------------------------*/


    //---METHOD FOR ENTERING CONTACTED PERSONS DETAILS----------------
    public static Person contactedPersonDetails(Scanner scanner, Person relevantPerson) {

        List<Person> contactedPersons = new ArrayList<>();
        contactedPersons.add(relevantPerson);
        System.out.print("Enter person name:");
        String name = scanner.next();
        System.out.print("Enter person surname:");
        String surname = scanner.next();
        System.out.print("Enter person age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter person country details >>>");
        Country country = enterCountry(scanner);
        System.out.println("Enter the details of the disease the person is infected with >>>");
        Disease disease = enterDisease(scanner);
        return new Person(name, surname, age, country, disease, contactedPersons);

    }


    //-------------METHOD FOR DISPLAYING PERSONS DATA--------------
    public static void displayPersons(List<Person> people) {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %30s %20s", "NAME", "SURNAME", "AGE");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (Person p : people) {
            if (p != null) {
                System.out.format("%10s %30s %20d",
                        p.getName(), p.getSurname(), p.getAge());
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------");
            } else
                break;
        }
    }






    /* ---------------------------------------------------------------------------------------------------------------------------*/

}


// NOTICE FOR TOMORROW : Refine the methods by implementing adding more contacted persons, more disease ..
// ALSO, for the person class, there is an issue because the class contains itself as an object instance
// thus it is repeating while entering data into an instance of it