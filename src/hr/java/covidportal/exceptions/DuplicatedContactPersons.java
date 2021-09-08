package hr.java.covidportal.exceptions;

public class DuplicatedContactPersons extends Exception{
    public DuplicatedContactPersons (){
        super();
    }
    public DuplicatedContactPersons (String message){
        super(message);
    }
    public DuplicatedContactPersons (Throwable cause) {
        super (cause);
    }
    public DuplicatedContactPersons(String message, Throwable cause) {
        super (message, cause);
    }
}
