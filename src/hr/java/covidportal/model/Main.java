package hr.java.covidportal.model;

import java.util.Scanner;

public class Main {
    static int counter = 0;

    public static void main(String[] args) {
        Scanner us = new Scanner(System.in);

        Country[] countries = new Country[3];
        Symptom[] symptoms = new Symptom[3];
        Disease[] diseases = new Disease[3];
        Person[] persons = new Person[3];

        System.out.println("Enter data for 3 countries, 3 symptoms, 3 diseases & 3 persons...");

     /*   //----------REQUESTING DATA OF COUNTRY----------------------
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
        for(int i=0;i<3;i++){
            System.out.println("Insert "+(i+1)+".Person>>>");
            persons[i] = enterPerson(us);
        }


    }



    //----------METHOD FOR ENTERING DATA OF COUNTRY----------------------
    public static Country enterCountry (Scanner scanner) {
        System.out.println ("Enter country name:");
        String name = scanner.next();
        System.out.println ("Enter country number of inhabitants:");
        int inhabitants = scanner.nextInt ();

        return new Country (name,inhabitants);
    }

    //----------METHOD FOR ENTERING DATA OF SYMPTOM----------------------
    public static Symptom enterSymptom (Scanner scanner) {
        System.out.println ("Enter sypmtom name:");
        String name = scanner.next();
        System.out.println ("Enter symptom value:");
        String value = scanner.next();

        return new Symptom (name,value);
    }

    //----------METHOD FOR ENTERING DATA OF DISEASE----------------------
    public static Disease enterDisease (Scanner scanner) {
        int i =0;
        System.out.println ("Enter disease name:");
        String name = scanner.next();
        System.out.println ("Enter disease symptoms:");
        Symptom [] symptoms = new Symptom[5];
        symptoms[i] = enterSymptom(scanner);
       label : while (true){


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

        return new Disease (name,symptoms);
    }
    //----------METHOD FOR ENTERING DATA OF PERSONS----------------------
    public static Person enterPerson (Scanner scanner) {

        int i =0;
        Person contactedPersons[] = new Person[3];

        System.out.println ("Enter person name:");
        String name = scanner.next();
        System.out.println ("Enter person surname:");
        String surname = scanner.next();
        System.out.println ("Enter person age:");
        int age = scanner.nextInt();
        System.out.println ("Enter person country:");
        Country country = enterCountry(scanner);
        System.out.println ("Enter the details of the disease the person is infected with:");
        Disease disease = enterDisease(scanner);
      label :   while(true) {
            if (counter == 0) {
                System.out.println("Is there a contacted person ? (Y/N)");
                char reply = scanner.next().charAt(0);
                if (reply == 'Y' || reply == 'y') {
                    counter++;
                    System.out.println ("Enter the contacted person details >>>");
                    contactedPersons[i]=contactedPersonDetails(scanner,contactedPersons[i]);
                    i++;

                } else if (reply == 'N' || reply == 'n') {
                    break;
                } else {
                    System.out.print("Wrong input, try again...");
                    continue label;

                }
            }
            else if(counter!=0 && counter < 3){

                System.out.println("Is there another contacted person ? (Y/N)");
                char reply = scanner.next().charAt(0);
                if (reply == 'Y' || reply == 'y') {
                    counter++;
                    System.out.println ("Enter the contacted person details >>>");
                    contactedPersons[i]=contactedPersonDetails(scanner,contactedPersons[i]);
                    i++;

                } else if (reply == 'N' || reply == 'n') {
                    break;
                  }
                else {
                    System.out.print("Wrong input, try again...");
                    continue label;

                }
            }
            else {
                System.out.println("Data succesfully inserted...");
                break;
            }
        }



        return new Person (name,surname,age,country,disease,contactedPersons);
    }

    //---METHOD FOR ENTERING CONTACTED PERSONS DETAILS----------------
    public static Person contactedPersonDetails(Scanner scanner, Person contactedPerson){

        Person contactedPersons[] = new Person[3];

        System.out.println ("Enter person name:");
        String name = scanner.next();
        System.out.println ("Enter person surname:");
        String surname = scanner.next();
        System.out.println ("Enter person age:");
        int age = scanner.nextInt();
        System.out.println ("Enter person country:");
        Country country = enterCountry(scanner);
        System.out.println ("Enter the details of the disease the person is infected with:");
        Disease disease = enterDisease(scanner);
        return new Person (name,surname,age,country,disease,contactedPersons);

    }
}


// NOTICE FOR TOMORROW : Refine the methods by implementing adding more contacted persons, more disease ..
// ALSO, for the person class, there is an issue because the class contains itself as an object instance
// thus it is repeating while entering data into an instance of it