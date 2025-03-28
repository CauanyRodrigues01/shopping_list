package exceptions;

public class WishNotFoundException extends Exception {
	public WishNotFoundException() {
		super();
	}
	
	public WishNotFoundException(String message) {
		super(message);
	}
}
