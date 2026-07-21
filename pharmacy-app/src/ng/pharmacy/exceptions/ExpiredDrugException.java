package ng.pharmacy.exceptions;

public class ExpiredDrugException extends RuntimeException{
    public ExpiredDrugException(String message) { super(message); }
}
