package ru.salnikov.exceptions;

public class InjectorException extends Exception {
    public InjectorException(Exception exception) {
        super(exception);
    }
}
