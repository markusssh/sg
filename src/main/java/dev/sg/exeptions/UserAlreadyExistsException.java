package dev.sg.exeptions;

public class UserAlreadyExistsException extends IllegalArgumentException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
