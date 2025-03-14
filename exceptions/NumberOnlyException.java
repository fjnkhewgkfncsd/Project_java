package exceptions;
public class NumberOnlyException extends NumberFormatException {
    
    public NumberOnlyException(String message) {
        super(message);
    }
    public NumberOnlyException(String inpuString, String formaString) throws NumberOnlyException {
        if(!inpuString.matches(formaString))
        {
            throw new NumberOnlyException("Number only");
        }
    }
}
