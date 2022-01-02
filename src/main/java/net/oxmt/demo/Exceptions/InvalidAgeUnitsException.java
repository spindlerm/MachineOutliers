package net.oxmt.demo.Exceptions;

public class InvalidAgeUnitsException extends Exception {
    public InvalidAgeUnitsException(String errorMessage) {
        super(errorMessage);
    }
}
