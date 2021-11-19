package com.example.myapplication;

public abstract class Account {
    String username;
    String password;
    String lastName;
    String firstName;
    String emailAddress;

    Account(String username, String password, String firstName, String lastName, String emailAddress) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    protected void changeUsername(String newUsername) {
        username = newUsername;
    }

    protected void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(password)) password = newPassword;
    }

    protected void changeName(String newFirstName, String newLastName) {
        firstName = newFirstName;
        lastName = newLastName;
    }

    protected void changeEmail(String newEmail) {
        username = newEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
