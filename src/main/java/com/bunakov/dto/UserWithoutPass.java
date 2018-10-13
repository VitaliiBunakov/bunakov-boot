package com.bunakov.dto;

import com.bunakov.model.User;

//@JsonIgnoreProperties(ignoreUnknown = true, value = {"plainTextPassword,hashedPassword"})
public class UserWithoutPass  {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public UserWithoutPass(User user) {
        this.id = user.getId();

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
    }

}
