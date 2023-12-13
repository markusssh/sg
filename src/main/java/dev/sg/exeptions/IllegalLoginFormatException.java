package dev.sg.exeptions;

public class IllegalLoginFormatException extends IllegalArgumentException{
    public IllegalLoginFormatException(String message) {
        super(message);
    }
}
