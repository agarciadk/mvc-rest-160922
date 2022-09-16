package es.bootcamp.mvcrest.errorHandler;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id) { super("Could not find employee " + id); }
}
