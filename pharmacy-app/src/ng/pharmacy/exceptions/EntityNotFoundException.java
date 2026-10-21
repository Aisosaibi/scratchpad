package ng.pharmacy.exceptions;

public class EntityNotFoundException extends PharmacyException {
    public EntityNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
