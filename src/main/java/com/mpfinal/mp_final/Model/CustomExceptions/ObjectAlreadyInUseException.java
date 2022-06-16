package com.mpfinal.mp_final.Model.CustomExceptions;

public class ObjectAlreadyInUseException extends Exception{
    public ObjectAlreadyInUseException(String message) {
        super(message);
    }
}
