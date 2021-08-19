package hr.java.covidportal.main;

import hr.java.covidportal.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int counter = 0; // This static variable is used to check whether it is or not the first person details that is being entered by user;)
    private static final int NUMBER_OF_COUNTRIES = 3;
    private static final int NUMBER_OF_PERSONS = 3;
    private static final int NUMBER_OF_SYMPTOMS = 3;
    private static final int NUMBER_OF_DISEASE = 3;


    public static void main(String[] args) {

        Scanner us = new Scanner(System.in);
        Country[] countries = new Country[NUMBER_OF_COUNTRIES];
        Symptom symptoms [] = new Symptom[NUMBER_OF_SYMPTOMS];
        Disease diseases [] = new Disease[NUMBER_OF_DISEASE];
        Person[] persons = new Person[NUMBER_OF_PERSONS]; // contains only the 3 main people entered by user
        List<Person> people = new ArrayList<>(); // contains all persons with their contacted persons




        System.out.println("Enter data for 3 countries, 3 symptoms, 3 diseases & 3 persons...");



      //----------REQUESTING DATA OF COUNTRY----------------------
             System.out.println("--------------------------------");

        System.out.println("\nEnter data for "+ NUMBER_OF_COUNTRIES+ " countries...");
        for(int i=0;i<NUMBER_OF_COUNTRIES;i++){
            System.out.println("Insert "+(i+1)+".Country>>>");
            countries[i] = enterCountry(us);
        }

        //----------REQUESTING DATA OF SYMPTOMS----------------------
        System.out.println("\nEnter data for 3 symptoms...");
        System.out.println("--------------------------------");
        for(int i=0;i<NUMBER_OF_SYMPTOMS;i++){
            System.out.println("Insert "+(i+1)+".Sypmtom>>>");
            symptoms[i] = enterSymptom(us);
        }

    //----------REQUESTING DATA OF DISEASES----------------------
        System.out.println("\nEnter data for 3 diseases/viruses...");
        System.out.println("--------------------------------");
        for(int i=0;i<NUMBER_OF_DISEASE;i++){
            System.out.println("Insert "+(i+1)+". Disease OR Virus>>>");
            diseases [i] = enterDisease(us, symptoms);
        }

        //----------REQUESTING DATA OF PERSONS----------------------
        System.out.println("\nEnter data for 3 persons...");
        System.out.println("--------------------------------");
        boolean offerListOfPeople = false;// TO PRESENT TO THE USER THE LIST OF EXISTING PERSONS WHEN HE ENTERS DATA OF SECOND PERSON
        for (int i = 0; i < NUMBER_OF_PERSONS; i++) {
            System.out.println("Insert " + (i + 1) + ".Person>>>");
            persons[i] = enterPerson(us, people, diseases, offerListOfPeople);
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
        System.out.print("Enter symptom value (RARE,MEDIUM OR FREQUENT):");
        String value = scanner.next();
        return new Symptom(name, value);
    }

    //----------METHOD FOR ENTERING DATA OF DISEASE----------------------
    public static Disease enterDisease(Scanner scanner, Symptom [] arrayOfSymtoms) {
        int arrayIndexOfSymptom = 0;
        boolean moreSymptomNeeded = true;

       while(true) {
           System.out.println("1. Disease");
           System.out.println("2. Virus");
           System.out.print("SELECTION >>> ");
           char response = scanner.next().charAt(0);
            if (response=='2') {
                System.out.print("Enter virus name: ");
                String name = scanner.next();
                System.out.println("Enter virus symptoms >>>");
                Symptom[] symptoms = new Symptom[NUMBER_OF_SYMPTOMS];
                symptoms[arrayIndexOfSymptom] = selectFromSymptoms(arrayOfSymtoms,scanner);

                while (moreSymptomNeeded) {


                    System.out.println("Insert another symptom ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("SELECTION >>> ");
                    char reply = scanner.next().charAt(0);
                    if (reply=='1') {
                        arrayIndexOfSymptom++;
                        symptoms[arrayIndexOfSymptom] = selectFromSymptoms(arrayOfSymtoms,scanner);

                    } else if (reply=='2') {
                        System.out.println("Data succesfully inserted...\n");
                        moreSymptomNeeded=false;
                    } else {
                        System.out.println("Wrong input, try again...");
                        moreSymptomNeeded=true;

                    }
                }

                return new Virus(name,symptoms);


            }
            else if (response=='1') {
                System.out.print("Enter disease name: ");
                String name = scanner.next();
                System.out.println("Enter disease symptoms...");
                Symptom[] symptoms = new Symptom[NUMBER_OF_SYMPTOMS];
                symptoms[arrayIndexOfSymptom] = selectFromSymptoms(arrayOfSymtoms,scanner);
                moreSymptomNeeded=true;
                while (moreSymptomNeeded) {


                    System.out.println("Insert another symptom ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("SELECTION >>> ");
                    char reply = scanner.next().charAt(0);
                    if (reply=='1') {
                        arrayIndexOfSymptom++;
                        symptoms[arrayIndexOfSymptom] = selectFromSymptoms(arrayOfSymtoms,scanner);

                    } else if (reply=='2') {
                        System.out.println("Data succesfully inserted...\n");
                        moreSymptomNeeded=false;
                    } else {
                        System.out.print("Wrong input, try again...");
                        moreSymptomNeeded=true; // going back to ask the user entering the right selection again

                    }
                }
                return new Disease(name,symptoms);

            } else {
                System.out.println("Wrong input, try again...");
                continue ; // going back to ask the user entering the right selection again

            }
        }


    }

    //----------METHOD FOR ENTERING DATA OF PERSONS (Pretty Big one)----------------------
    public static Person enterPerson(Scanner scanner, List<Person> people,Disease [] diseases, boolean offerListOfPeople) {



        List<Person> contactedPersons = new ArrayList<>();


        System.out.print("Enter person name: ");
        String name = scanner.next();
        System.out.print("Enter person surname: ");
        String surname = scanner.next();
        System.out.print("Enter person age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter person country details >>>");
        Country country = enterCountry(scanner);

        Disease disease = selectFromDiseases(diseases,scanner);
        boolean morePersonsNeeded = true;

        while (morePersonsNeeded) {
            if (counter == 0 && offerListOfPeople == false) {//this if statement executes when it is the first persons details being entered
                // the variable "offerListOfPeople" actually helps me to ensure showing to the user already existing people
                System.out.println("\nData succesfully inserted...\n");
                morePersonsNeeded = false; // exits the while loop

            }
            else if (counter != 0 && counter <= 3 && offerListOfPeople || people.get(0)!=null) {  // this executes when it is the second persons details being entered


                System.out.println("Is there a/another contacted person ?");

                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("SELECTION >>> ");
                char reply = scanner.next().charAt(0);
                if (reply=='1') {
                    counter++;
                    // Showing to the user the persons details he/she already inserted before
                    System.out.println("Choose from this list below please...");
                    displayPersons(people);

                   // The flag is already set to true if the previous if block did not execute
                    while (morePersonsNeeded) {
                        System.out.print("Enter the exact Name or Surname from the table above: ");
                        String contactedName = scanner.next();
                        boolean checker = false;

                        for (Person p : people) {
                            if (p != null && (p.getName().equals(contactedName) || p.getSurname().equals(contactedName))) {
                                contactedPersons.add(p); // ADDING THE Contacted person to the contactedPersons list of the current person
                                checker = true;
                               System.out.println(p.getName()+" "+p.getSurname()+"✓");
                                System.out.println("\nData succesfully inserted...\n");

                                morePersonsNeeded=false;

                            }
                        }
                        if (checker == false) {
                            System.out.print("Wrong input, try again...\n");
                            morePersonsNeeded=true;
                        }


                    }


                } else if (reply=='2') {
                    System.out.println("\nData succesfully inserted...\n");
                    morePersonsNeeded=false;
                } else {
                    System.out.print("Wrong input, try again...");
                    morePersonsNeeded=true;

                }
            }
            else {
                System.out.println("Data succesfully inserted...\n");
                 morePersonsNeeded=false;
            }
        }


        return new Person.Builder(name).withSurname(surname).withAge(age).withCountry(country).withDisease(disease).withContactedPersons(contactedPersons).build();
    }









    /* ---------------------------------------HELPER METHODS FOR enterPerson METHOD-------------------------------------------------------*/

    //-------------METHOD FOR DISPLAYING PERSONS DATA--------------
    public static void displayPersons(List<Person> people) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %30s %20s %20s %20s", "NAME", "SURNAME", "AGE", "COUNTRY", "DISEASE");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (Person p : people) {
            if (p != null) {
                System.out.format("%10s %30s %20d %20s %20s",
                        p.getName(), p.getSurname(), p.getAge(),p.getCountry().getName(),p.getInfectedWithDisease().getName());
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else
                break;
        }
    }

  //------------------METHOD FOR DISPLAYING DISEASES OR VIRUSES----------------------
    public static Disease selectFromDiseases(Disease [] diseases, Scanner scanner){

        int diseaseNumber=0;

        System.out.println("Select the disease OR virus the person is infected with >>>");
        for(Disease e : diseases)
        {

            System.out.println(diseaseNumber+". "+diseases[diseaseNumber].getName());
            diseaseNumber++;


        }

        while(true) {
            System.out.print("SELECTION >>>");
            // REMEMBER THAT WHEN A STRING IS ENTERED INSTEAD OF INT, ELSE BLOCK DOES NOT EXECUTE BECAUSE IT THROWS AN EXCEPTION
            int chosenDisease = scanner.nextInt();
             if(chosenDisease >= 0 && chosenDisease < NUMBER_OF_DISEASE){ // dont forget to number of disease
                return diseases[chosenDisease];
            }

           else {
                System.out.print("Wrong input, Select the number and try again...\n");
                continue;
            }
        }


    }


    //------------------METHOD FOR SELECTING FROM SYMPTOMS----------------------
    public static Symptom selectFromSymptoms(Symptom[] symptoms, Scanner scanner){
        int arrayIndexOfSymptom=0;

        for(Symptom s : symptoms)
        {

            System.out.println(arrayIndexOfSymptom+". "+symptoms[arrayIndexOfSymptom].getName()+" ("+symptoms[arrayIndexOfSymptom].getValue()+")");
            arrayIndexOfSymptom++;


        }

        while(true) {
            System.out.print("SELECTION >>>");
            int chosenSymptom = scanner.nextInt();
            if (chosenSymptom >= 0 && chosenSymptom < NUMBER_OF_SYMPTOMS) {
                return symptoms[chosenSymptom];
            }
            else {
                System.out.print("Wrong input, Select the number and try again...\n");
                continue;
            }
        }

    }

    /* ---------------------------------------------------------------------------------------------------------------------------*/

}

