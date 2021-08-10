package by.rozmysl.booking.exception;
/**
 *  The class is used to store MyEntityNotFoundException objects
 */
public class MyEntityNotFoundException extends RuntimeException {
    /**
     *  The constructor creates a new object MyEntityNotFoundException
     * @param name  entity name
     */
    public MyEntityNotFoundException(String name) {
        super("Entity is not found, " + name);
    }
}
