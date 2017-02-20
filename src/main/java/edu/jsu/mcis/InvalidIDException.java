package edu.jsu.mcis;

public class InvalidIDException extends RuntimeException {
	public InvalidIDException() {
		super();
	}
	public InvalidIDException(String message) {
		super(message);
	}
	public InvalidIDException(String message, Throwable cause){
		super(message, cause);
	}
	public InvalidIDException(Throwable cause){
		super(cause);
	}
}