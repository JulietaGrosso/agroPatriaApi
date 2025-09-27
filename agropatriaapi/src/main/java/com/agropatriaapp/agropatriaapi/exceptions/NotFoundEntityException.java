package com.agropatriaapp.agropatriaapi.exceptions;

public class NotFoundEntityException extends Exception{
    public NotFoundEntityException(String mensaje){
        super(mensaje);
    }
}
