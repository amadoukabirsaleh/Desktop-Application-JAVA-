package hr.java.covidportal.main;

import hr.java.covidportal.exceptions.DiseaseOfTheSameSymptom;
import hr.java.covidportal.exceptions.DuplicatedContactPersons;
import hr.java.covidportal.model.*;

import java.util.*;

public class Main {

    private static final int NUMBER_OF_COUNTRIES = 3;
    private static final int NUMBER_OF_PERSONS = 3;
    private static final int NUMBER_OF_SYMPTOMS = 3;
    private static final int NUMBER_OF_DISEASE = 3;
    static int counter = 0; // This static variable is used to check whether it is or not the first person details that is being entered by user;)


    static Country[] countries = new Country[NUMBER_OF_COUNTRIES];
    static Symptom symptoms[] = new Symptom[NUMBER_OF_SYMPTOMS];
    static Disease diseases[] = new Disease[NUMBER_OF_DISEASE];

    public static void main(String[] args) throws DuplicatedContactPersons {

        Scanner us = new Scanner(System.in);
        Person[] persons = new Person[NUMBER_OF_PERSONS]; // contains only the 3 main people entered by user
        List<Person> people = new ArrayList<>(); // contains all persons with their contacted persons


        System.out.println("Enter data for 3 countries, 3 symptoms, 3 diseases & 3 persons...");


        //----------REQUESTING DATA OF COUNTRY----------------------
        System.out.println("--------------------------------");

        System.out.println("\nEnter data for " + NUMBER_OF_COUNTRIES + " countries...");
        for (int i = 0; i < NUMBER_OF_COUNTRIES; i++) {
            System.out.println("Insert " + (i + 1) + ".Country>>>");
            countries[i] = enterCountry(us);
        }

        //----------REQUESTING DATA OF SYMPTOMS----------------------
        System.out.println("\nEnter data for 3 symptoms...");
        System.out.println("--------------------------------");
        for (int i = 0; i < NUMBER_OF_SYMPTOMS; i++) {
            System.out.println("Insert " + (i + 1) + ".Sypmtom>>>");
            symptoms[i] = enterSymptom(us);
        }

        //----------REQUESTING DATA OF DISEASES----------------------
        System.out.println("\nEnter data for 3 diseases/viruses...");
        System.out.println("--------------------------------");
        for (int i = 0; i < NUMBER_OF_DISEASE; i++) {
            System.out.println("Insert " + (i + 1) + ". Disease OR Virus>>>");
            diseases[i] = enterDisease(us);
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
        int inhabitants = takeUserInputSafely();
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
    public static Disease enterDisease(Scanner scanner) {
        int arrayIndexOfSymptom = 0;
        boolean moreSymptomNeeded = true;

        while (true) {
            System.out.println("1. Disease");
            System.out.println("2. Virus");
            System.out.print("SELECTION >>> ");
            int response = takeUserInputSafely();
            if (response == 2) {
                System.out.print("Enter virus name: ");
                String name = scanner.next();
                System.out.println("Enter virus symptoms >>>");
                Symptom[] CurrentDiseaseSymptoms = new Symptom[NUMBER_OF_SYMPTOMS];
                CurrentDiseaseSymptoms[arrayIndexOfSymptom] = selectFromSymptoms(CurrentDiseaseSymptoms,scanner);

                while (moreSymptomNeeded) {


                    System.out.println("Insert another symptom ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("SELECTION >>> ");
                    int reply = takeUserInputSafely();
                    if (reply == 1) {
                        boolean AllSymptomSelected = false;
                        boolean NotAllSymptomSelected = false;
                        arrayIndexOfSymptom++;
                        CurrentDiseaseSymptoms[arrayIndexOfSymptom] = selectFromSymptoms(CurrentDiseaseSymptoms,scanner);
                        // To check if all symptoms in symptoms List were already selected and not showing again symptom list
                        for (Symptom s : symptoms) {
                            List<Symptom> symptomsArrayConvertedToList = Arrays.asList(CurrentDiseaseSymptoms);
                            if (symptomsArrayConvertedToList.contains(s))
                                AllSymptomSelected = true;
                            else
                                NotAllSymptomSelected = true;
                        }

                        if (AllSymptomSelected == true && NotAllSymptomSelected == false)
                            moreSymptomNeeded = false;
                        else
                            moreSymptomNeeded = true;


                    } else if (reply == 2) {
                        System.out.println("Data succesfully inserted...\n");
                        moreSymptomNeeded = false;
                    } else {
                        System.out.println("Wrong input, try again...");
                        moreSymptomNeeded = true;

                    }
                }

                return new Virus(name, CurrentDiseaseSymptoms);


            } else if (response == 1) {
                System.out.print("Enter disease name: ");
                String name = scanner.next();
                System.out.println("Enter disease symptoms...");
                Symptom[] CurrentDiseaseSymptoms = new Symptom[NUMBER_OF_SYMPTOMS];
                CurrentDiseaseSymptoms[arrayIndexOfSymptom] = selectFromSymptoms(CurrentDiseaseSymptoms, scanner);
                moreSymptomNeeded = true;
                while (moreSymptomNeeded) {


                    System.out.println("Insert another symptom ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("SELECTION >>> ");
                    int reply = takeUserInputSafely();
                    if (reply == 1) {
                        boolean AllSymptomSelected = false;
                        boolean NotAllSymptomSelected = false;
                        arrayIndexOfSymptom++;
                        CurrentDiseaseSymptoms[arrayIndexOfSymptom] = selectFromSymptoms(CurrentDiseaseSymptoms,scanner);
                        for (Symptom s : symptoms) {
                            List<Symptom> symptomsArrayConvertedToList = Arrays.asList(CurrentDiseaseSymptoms);
                            if (symptomsArrayConvertedToList.contains(s))
                                AllSymptomSelected = true;
                            else
                                NotAllSymptomSelected = true;
                        }

                        if (AllSymptomSelected == true && NotAllSymptomSelected == false)
                            moreSymptomNeeded = false;
                        else
                            moreSymptomNeeded = true;

                    } else if (reply == 2) {
                        System.out.println("Data succesfully inserted...\n");
                        moreSymptomNeeded = false;
                    } else {
                        System.out.print("Wrong input, try again...");
                        moreSymptomNeeded = true; // going back to ask the user entering the right selection again

                    }
                }
                return new Disease(name, CurrentDiseaseSymptoms);

            } else {
                System.out.println("Wrong input, try again...");
                moreSymptomNeeded = true; // going back to ask the user entering the right selection again

            }
        }


    }

    //----------METHOD FOR ENTERING DATA OF PERSONS (Pretty Big one)----------------------
    public static Person enterPerson(Scanner scanner, List<Person> people, Disease[] diseases, boolean offerListOfPeople) throws DuplicatedContactPersons {


        List<Person> contactedPersons = new ArrayList<>();


        System.out.print("Enter person name: ");
        String name = scanner.next();
        System.out.print("Enter person surname: ");
        String surname = scanner.next();
        System.out.print("Enter person age: ");
        int age = takeUserInputSafely();
        scanner.nextLine();
        System.out.println("Enter person country details >>>");
        Country country = enterCountry(scanner);

        Disease disease = selectFromDiseases(diseases, scanner);
        boolean morePersonsNeeded = true;

        while (morePersonsNeeded && people.size() > contactedPersons.size()) {
            if (counter == 0 && offerListOfPeople == false) {//this if statement executes when it is the first persons details being entered
                // the variable "offerListOfPeople" actually helps me to ensure showing to the user already existing people
                System.out.println("\nData succesfully inserted...\n");
                counter++;
                morePersonsNeeded = false; // exits the while loop

            } else if (counter != 0 && counter <= 3 && offerListOfPeople || people.get(0) != null) {  // this executes when it is the second persons details being entered

                while (morePersonsNeeded) {


                    System.out.println("Is there a/another contacted person ?");

                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("SELECTION >>> ");
                    int reply = takeUserInputSafely();

                    if (reply == 1) {
                        boolean AllPersonSelected = false;
                        boolean NotAllPersonSelected = false;
                        counter++;
                        // Showing to the user the persons details he/she already inserted before
                        contactedPersons.add(selectFromPersons(people, contactedPersons));

                        // To check if all persons in people List were already selected and not showing again people list
                        for (Person p : people) {
                            if (contactedPersons.contains(p))
                                AllPersonSelected = true;
                            else
                                NotAllPersonSelected = true;
                        }

                        if (AllPersonSelected == true && NotAllPersonSelected == false)
                            morePersonsNeeded = false;
                        else
                            morePersonsNeeded = true;

                    } else if (reply == 2) {
                        System.out.println("\nData succesfully inserted...\n");
                        morePersonsNeeded = false;
                    } else {
                        System.out.print("Wrong input, try again...");
                        morePersonsNeeded = true;

                    }

                }

            } else {
                System.out.println("Data succesfully inserted...\n");
                morePersonsNeeded = false;
            }
        }


        return new Person.Builder(name).withSurname(surname).withAge(age).withCountry(country).withDisease(disease).withContactedPersons(contactedPersons).build();
    }









    /* ---------------------------------------HELPER METHODS FOR enterPerson METHOD-------------------------------------------------------*/

    //-------------METHOD FOR DISPLAYING PERSONS DATA--------------
    public static Person selectFromPersons(List<Person> people, List<Person> currentContactedPersonsList) throws DuplicatedContactPersons {
        int personNumberIndex = 0;
        System.out.println("Select the number from this list below please...");
        for (Person p : people) {

            System.out.println(personNumberIndex + ". " + people.get(personNumberIndex).getName());
            personNumberIndex++;


        }

        while (true) {
            System.out.print("SELECTION >>>");
            int chosenPerson = takeUserInputSafely();
            try {

                if (checkDuplication(people.get(chosenPerson), currentContactedPersonsList)) {
                    try {
                        throw new DuplicatedContactPersons();

                    } catch (DuplicatedContactPersons ex) {
                        System.out.println("\nThis person has already been selected, try another one !");
                    }

                } else {
                    System.out.println("\nAdded sucessfully !\n");
                    return people.get(chosenPerson);
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.print("Wrong input, Select the existing index number please...\n");
                continue;
            }

        }
    }

    //------------------METHOD FOR DISPLAYING DISEASES OR VIRUSES----------------------
    public static Disease selectFromDiseases(Disease[] diseases, Scanner scanner) {

        int diseaseNumber = 0;

        System.out.println("Select the disease OR virus the person is infected with >>>");
        for (Disease e : diseases) {

            System.out.println(diseaseNumber + ". " + diseases[diseaseNumber].getName());
            diseaseNumber++;


        }

        while (true) {
            System.out.print("SELECTION >>>");
            // REMEMBER THAT WHEN A STRING IS ENTERED INSTEAD OF INT, ELSE BLOCK DOES NOT EXECUTE BECAUSE IT THROWS AN EXCEPTION
            int chosenDisease = takeUserInputSafely();
            try {
                return diseases[chosenDisease];
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Wrong input, Select the existing index number please...\n");
                continue;
            }
        }


    }


    //------------------METHOD FOR SELECTING FROM SYMPTOMS----------------------
    public static Symptom selectFromSymptoms(Symptom[] CurrentDiseaseSymptoms, Scanner scanner) {
        int arrayIndexOfSymptom = 0;

        for (Symptom s : symptoms) {

            System.out.println(arrayIndexOfSymptom + ". " + symptoms[arrayIndexOfSymptom].getName() + " (" + symptoms[arrayIndexOfSymptom].getValue() + ")");
            arrayIndexOfSymptom++;


        }

        while (true) {
            System.out.print("SELECTION >>>");
            int chosenSymptom = takeUserInputSafely();
            try {

                if (checkDiseaseOfSameSymptom(diseases, symptoms[chosenSymptom], CurrentDiseaseSymptoms)) {
                    try {
                        throw new DiseaseOfTheSameSymptom();
                    } catch (DiseaseOfTheSameSymptom e) {

                        System.out.println("\nThis symptom has already been selected for a different disease, try another one !");
                        continue;
                    }
                } else if (checkIfSymptomAlreadySelected(CurrentDiseaseSymptoms, symptoms[chosenSymptom])) {
                    System.out.println("\nThis symptom has already been selected, try another one !");
                    continue;
                } else {
                    System.out.println("\nAdded sucessfully !\n");
                    return symptoms[chosenSymptom];
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.print("Wrong input, Select the existing index number please...\n");
                continue;
            }
        }

    }

    /* ---------------------------------------------------------------------------------------------------------------------------*/


    // make this method throwing an exception
    // use 2 diferent exceptions exception in the catch and if block
    public static int takeUserInputSafely() {
        Scanner userInput = new Scanner(System.in);
        int choice = 0;
        boolean continueLoop = false;

        do {
            try {
                String inStr = userInput.nextLine();
                choice = Integer.parseInt(inStr);
                continueLoop = false;
            } catch (InputMismatchException ex1) {
                System.out.println("You must enter numeric values...try again please");
                continueLoop = true;
            } catch (NumberFormatException ex2) {
                System.out.print("You must enter numeric values...try again please : ");
                continueLoop = true;
            }


        } while (continueLoop == true);


        return choice;

    }

    /****************************CHECKER FUNCTIONS****************************************************/
    public static boolean checkDuplication(Person chosenPerson, List<Person> currentContactedPersonsList) {
        boolean personFound = false;
        if (currentContactedPersonsList.contains(chosenPerson))
            return personFound = true;
        else
            return personFound;
    }


    public static boolean checkDiseaseOfSameSymptom(Disease[] previousEnteredDiseases, Symptom symptomBeingEntered, Symptom [] CurrentDiseaseSymptom) {

        for (Disease d : previousEnteredDiseases) {
            try {
                List<Symptom> previousEnteredSymptoms = Arrays.asList(d.getSymptoms());
                if (previousEnteredSymptoms.equals(CurrentDiseaseSymptom))
                    return true;
            } catch (NullPointerException e) {
                return false;
            }

        }
        return false;

    }

    public static boolean checkIfSymptomAlreadySelected(Symptom[] symptomsOfCurrentDisease, Symptom selectedSymptom) {
        List<Symptom> symptomOfCurrentDiseaseList = Arrays.asList(symptomsOfCurrentDisease);
        if (symptomOfCurrentDiseaseList.contains(selectedSymptom))
            return true;
        else
            return false;
    }


}

// things to do

// Set all contacted persons infected with virus if the person's illness is caused by a virus