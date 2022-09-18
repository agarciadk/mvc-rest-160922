package es.bootcamp.mvcrest.errorHandler;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) { super("Could not find user " + id); }
}
