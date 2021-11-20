package com.example.myapplication;
import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Account {
    int userid;
    String username;
    String password;
    String lastName;
    String firstName;
    String emailAddress;

    public Account(int userid, String username, String password, String firstName, String lastName, String emailAddress) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        updateToDatabase(); // temporary (write to database need to be implemented in subclasses)
    }

    private void updateToDatabase(){
        DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
        ref.child("Users").child(String.valueOf(userid)).setValue(this);
    }

    protected void changeUsername(String newUsername) {
        username = newUsername;
        updateToDatabase();
    }

    protected void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(password)) password = newPassword;
        updateToDatabase();
    }

    protected void changeName(String newFirstName, String newLastName){
        firstName = newFirstName;
        lastName = newLastName;
        updateToDatabase();
    }

    protected void changeEmail(String newEmailAddress) {
        emailAddress = newEmailAddress;
        updateToDatabase();
    }

    @NonNull
    @Override
    public String toString() {
        return this.username;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
