
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
}
