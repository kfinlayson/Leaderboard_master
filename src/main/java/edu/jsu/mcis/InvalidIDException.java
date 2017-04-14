package edu.jsu.mcis;

public class InvalidIDException extends RuntimeException {
	public InvalidIDException(String message) {
		super(message);
	}
}