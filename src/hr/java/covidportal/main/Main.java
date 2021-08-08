package hr.java.covidportal.main;

import hr.java.covidportal.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int counter = 0;

    public static void main(String[] args) {

        Scanner us = new Scanner(System.in);
        Country[] countries = new Country[3];
      //  Symptom[] symptoms = new Symptom[5];
        List<Symptom> symptoms = new ArrayList<>();
     // Disease[] diseases = new Disease[4];
        List<Disease> diseases = new ArrayList<>();  //Array of diseases or viruses, using polymorphism power
        Person[] persons = new Person[3]; // contains only the 3 main people entered by user
        List<Person> people = new ArrayList<>(); // contains all persons with their contacted persons

        // Initiating disease list with 2 diseases and 2 viruses



        System.out.println("Enter data for 3 countries, 3 symptoms, 3 diseases & 3 persons...");
/*


      //----------REQUESTING DATA OF COUNTRY----------------------
             System.out.println("--------------------------------");

        System.out.println("\nEnter data for 3 countries...");
        for(int i=0;i<3;i++){
            System.out.println("Insert "+(i+1)+".Country>>>");
            countries[i] = enterCountry(us);
        }
*/
        //----------REQUESTING DATA OF SYMPTOMS----------------------
        System.out.println("\nEnter data for 3 symptoms...");
        System.out.println("--------------------------------");
        for(int i=0;i<3;i++){
            System.out.println("Insert "+(i+1)+".Sypmtom>>>");
            symptoms.add(enterSymptom(us));
        }

    //----------REQUESTING DATA OF DISEASES----------------------
        System.out.println("\nEnter data for 3 diseases/viruses...");
        System.out.println("--------------------------------");
        for(int i=0;i<3;i++){
            System.out.println("Insert "+(i+1)+". Disease OR Virus>>>");
            diseases.add(enterDisease(us,symptoms));
        }

        //----------REQUESTING DATA OF PERSONS----------------------
        System.out.println("\nEnter data for 3 persons...");
        System.out.println("--------------------------------");
        boolean offerListOfPeople = false;// TO PRESENT TO THE USER THE LIST OF EXISTING PERSONS WHEN HE ENTERS DATA OF SECOND PERSON
        for (int i = 0; i < 3; i++) {
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
    public static Disease enterDisease(Scanner scanner, List<Symptom> listOfSymtoms) {
        int i = 0;

       label0: while(true) {
           System.out.println("1. Disease");
           System.out.println("2. Virus");
           System.out.print("SELECTION >>> ");
           char response = scanner.next().charAt(0);
            if (response == '2') {
                System.out.print("Enter virus name: ");
                String name = scanner.next();
                System.out.println("Enter virus symptoms >>>");
                Symptom[] symptoms = new Symptom[6];
                symptoms[i] = selectFromSymptoms(listOfSymtoms,scanner);
                label:
                while (true) {


                    System.out.println("Insert another symptom ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("SELECTION >>> ");
                    char reply = scanner.next().charAt(0);
                    if (reply == '1') {
                        i++;
                        symptoms[i] = selectFromSymptoms(listOfSymtoms,scanner);

                    } else if (reply == '2') {
                        System.out.println("Data succesfully inserted...\n");
                        break;
                    } else {
                        System.out.println("Wrong input, try again...");
                        continue label;

                    }
                }

                return new Virus(name,symptoms);


            }
            else if (response =='1') {
                System.out.print("Enter disease name: ");
                String name = scanner.next();
                System.out.println("Enter disease symptoms...");
                Symptom[] symptoms = new Symptom[6];
                symptoms[i] = selectFromSymptoms(listOfSymtoms,scanner);
                label:
                while (true) {


                    System.out.println("Insert another symptom ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("SELECTION >>> ");
                    char reply = scanner.next().charAt(0);
                    if (reply == '1') {
                        i++;
                        symptoms[i] = selectFromSymptoms(listOfSymtoms,scanner);

                    } else if (reply == '2') {
                        System.out.println("Data succesfully inserted...\n");
                        break;
                    } else {
                        System.out.print("Wrong input, try again...");
                        continue label;

                    }
                }
                return new Disease(name,symptoms);

            } else {
                System.out.println("Wrong input, try again...");
                continue label0;

            }
        }


    }

    //----------METHOD FOR ENTERING DATA OF PERSONS----------------------
    public static Person enterPerson(Scanner scanner, List<Person> people, List<Disease>diseases, boolean offerListOfPeople) {



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

        label:
        while (true) {
            if (counter == 0 && offerListOfPeople == false) {
                System.out.println("Is there a contacted person ?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("SELECTION >>> ");
                char reply = scanner.next().charAt(0);
                if (reply == '1') {
                    counter++;
                    System.out.println("Enter the contacted person details >>>");
                    Person temp = new Person();
                    temp = contactedPersonDetails(scanner, temp);
                    contactedPersons.add(temp);
                    people.add(temp);


                } else if (reply == '2') {
                    System.out.println("Data succesfully inserted...\n");

                    break;
                } else {
                    System.out.print("Wrong input, try again...");
                    continue label;

                }
            } else if (counter != 0 && counter <= 3 && offerListOfPeople || people.get(0)!=null) {

                System.out.println("Is there a contacted person ?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("SELECTION >>> ");
                char reply = scanner.next().charAt(0);
                if (reply == '1') {
                    counter++;

                    // System.out.println ("Enter the contacted person details >>>");
                    System.out.println("Choose from this list below please...");
                    displayPersons(people);

                    label2:
                    while (true) {
                        System.out.print("Enter exact Name from the list above: ");
                        String contactedName = scanner.next();
                        boolean checker = false;

                        for (Person p : people) {
                            if (p != null && p.getName().equals(contactedName)) {
                                contactedPersons.add(p);
                                checker = true;
                               System.out.println(p.getName()+" "+p.getSurname()+"✓");
                                break label2;

                            }

                        }
                        if (checker == false) {
                            System.out.print("Wrong input, try again...");
                            continue label2;
                        }
                    }


                } else if (reply == '2') {
                    System.out.println("Data succesfully inserted...\n");
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


        return new Person.Builder(name).withSurname(surname).withAge(age).withCountry(country).withDisease(disease).withContactedPersons(contactedPersons).build();
    }









    /* ---------------------------------------HELPER METHODS FOR enterPerson METHOD-------------------------------------------------------*/


    //---METHOD FOR ENTERING CONTACTED PERSONS DETAILS----------------
    public static Person contactedPersonDetails(Scanner scanner, Person relevantPerson) {

        List<Person> contactedPersons = new ArrayList<>();
        contactedPersons.add(relevantPerson);
        System.out.print("Enter person name: ");
        String name = scanner.next();
        System.out.print("Enter person surname: ");
        String surname = scanner.next();
        System.out.print("Enter person age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter person country details >>>");
        Country country = enterCountry(scanner);
        Disease disease = new Disease(null,null);
        return new Person.Builder(name).withSurname(surname).withAge(age).withCountry(country).withDisease(disease).withContactedPersons(contactedPersons).build();
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

  //------------------METHOD FOR DISPLAYING DISEASES OR VIRUSES----------------------
    public static Disease selectFromDiseases(List<Disease> diseases, Scanner scanner){

        int j=0;

        System.out.println("Select the disease OR virus the person is infected with >>>");
        for(Disease e : diseases)
        {

            System.out.println(j+") "+diseases.get(j).getName());
            j++;


        }

        label111:  while(true) {
            System.out.println("SELECTION >>>");
            char c = scanner.next().charAt(0);
            if (c == '0') {
                return diseases.get(0);

            } else if (c == '1') {
                return diseases.get(1);


            } else if (c == '2') {
                return diseases.get(2);

            } else {
                System.out.print("Wrong input, Select the number and try again...");
                continue label111;
            }
        }


    }


    //------------------METHOD FOR SELECTING FROM SYMPTOMS----------------------
    public static Symptom selectFromSymptoms(List<Symptom> symptoms, Scanner scanner){
        int j=0;


        for(Symptom s : symptoms)
        {

            System.out.println(j+". "+symptoms.get(j).getName()+" ("+symptoms.get(j).getValue()+")");
            j++;


        }

        label111:  while(true) {
            System.out.print("SELECTION >>>");
            char c = scanner.next().charAt(0);
            if (c == '0') {
                return symptoms.get(0);

            } else if (c == '1') {
                return symptoms.get(1);


            } else if (c == '2') {
                return symptoms.get(2);

            } else {
                System.out.print("Wrong input, Select the number and try again...");
                continue label111;
            }
        }

    }

    /* ---------------------------------------------------------------------------------------------------------------------------*/

}


// NOTICE FOR TOMORROW : Refine the methods by implementing adding more contacted persons, more disease ..
// ALSO, for the person class, there is an issue because the class contains itself as an object instance
// thus it is repeating while entering data into an instance of it