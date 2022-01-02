package net.oxmt.demo.Exceptions;

public class InvalidAgeStringException extends Exception {
    public InvalidAgeStringException(String errorMessage) {
        super(errorMessage);
    }
}
