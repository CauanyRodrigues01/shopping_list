package br.com.wishList.main;

public class WishInUseException extends Exception {
	public WishInUseException() {
		super();
	}
	
	public WishInUseException(String message) {
		super(message);
	}
}
