package es.bootcamp.mvcrest.errorHandler;

import java.util.UUID;

public class UserListNotAcceptable extends RuntimeException{
    public UserListNotAcceptable(int page, int size) { super("Index out of bounds exception: page: " + page + " size: " + size); }
}
