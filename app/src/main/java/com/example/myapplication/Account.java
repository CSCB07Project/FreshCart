package com.example.myapplication;

public abstract class Account {
    int userid;
    String username;
    String password;
    String lastName;
    String firstName;
    String emailAddress;

    Account(int userid, String username, String password, String firstName, String lastName, String emailAddress) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        updateToDatabase(); // temporary (write to database need to be implemented in subclasses)
    }

    private void updateToDatabase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child(userid).setValue(this);
    }

    protected void changeUsername(String newUsername) {
        username = newUsername;
        updateToDatabase();
    }

    protected void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(password)) password = newPassword;
        updateToDatabase();
    }

    protected void changeName(String newFirstName, String newLastName) {
        firstName = newFirstName;
        lastName = newLastName;
        updateToDatabase();
    }

    protected void changeEmail(String newEmailAddress) {
        emailAddress = newEmailAddress;
        updateToDatabase();
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
