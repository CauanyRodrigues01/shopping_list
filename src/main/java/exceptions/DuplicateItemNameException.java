package exceptions;

public class DuplicateItemNameException extends Exception {
	
	public DuplicateItemNameException() {
        super();
    }
	
    public DuplicateItemNameException(String message) {
        super(message);
    }
}