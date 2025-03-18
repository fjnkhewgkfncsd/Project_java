package exceptions;
import database.FetchData;

public class CheckEmptyStringException extends IllegalArgumentException {

    public CheckEmptyStringException(String message) {
        super(message);
    }
    
    public CheckEmptyStringException(String input, String formaString) throws CheckEmptyStringException {
        if(!input.matches(formaString))
        {
            throw new CheckEmptyStringException("Name cannot be without characters");
        }
    }
    public CheckEmptyStringException(String[] inpuStrings) throws CheckEmptyStringException {
        for (String string : inpuStrings) {
            if(string.isEmpty())
            {
                throw new CheckEmptyStringException("input cannot be empty");
            }
        }
    }
    public static void Checkemailexception(String email) throws CheckEmptyStringException {
        if(!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))
        {
            throw new CheckEmptyStringException("Email is not valid");
        }
    }
    public static void Checkdobexception(String dob) throws CheckEmptyStringException {
        if(!dob.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"))
        {
            throw new CheckEmptyStringException("Date of birth is not valid");
        }
    }
    public static void Checksexexception(char sex) throws CheckEmptyStringException {
        if(sex != 'M' && sex != 'F')
        {
            throw new CheckEmptyStringException("You could only choose M or F");    
        }
    }  
    public static void Isemailtaken(String Email) throws CheckEmptyStringException {
        if(FetchData.isEmailTaken(Email)){
            throw new CheckEmptyStringException("Email is already taken");
        };
    } 
}
