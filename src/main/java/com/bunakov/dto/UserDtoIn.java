package com.bunakov.dto;

import com.bunakov.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDtoIn extends User {
    private String firstName;
    private String lastName;
    private String userName;
    @JsonProperty("password")
    private String plainTextPassword;

    public UserDtoIn() {
    }

    @Override
    public String toString() {
        return "UserDtoIn{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", plainTextPassword='" + plainTextPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDtoIn userDtoIn = (UserDtoIn) o;
        return Objects.equals(firstName, userDtoIn.firstName) &&
                Objects.equals(lastName, userDtoIn.lastName) &&
                Objects.equals(userName, userDtoIn.userName) &&
                Objects.equals(plainTextPassword, userDtoIn.plainTextPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, userName, plainTextPassword);
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public void setPlainTextPassword(String plainTextPassword) {
        this.plainTextPassword = plainTextPassword;
    }

    public UserDtoIn(String firstName, String lastName, String userName, String plainTextPassword) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.plainTextPassword = plainTextPassword;
    }
}
