package edu.eci.cvds.persistence;

public class PersistenceException extends Exception{
    public PersistenceException(String message,Exception e){super(message, e);}
}
