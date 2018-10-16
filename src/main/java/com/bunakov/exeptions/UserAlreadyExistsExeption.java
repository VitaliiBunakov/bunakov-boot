package com.bunakov.exeptions;
//user is  exist, exeption is thrown
public class UserAlreadyExistsExeption extends Exception {
    public UserAlreadyExistsExeption(String username) {
        super("user already exists - " + username);
    }

}
