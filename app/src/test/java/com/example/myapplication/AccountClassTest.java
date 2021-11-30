package com.example.myapplication;

import static org.junit.Assert.*;
import org.junit.Test;

/*
Test every function in the AccountClass
 */
public class AccountClassTest {
    @Test
    public void testChangeFunctions(){
        Buyer test = new Buyer("1", "Test", "Test", "Test", "Test", "Test");
        //Test change username
        test.changeUsername("NewUsername");
        assertEquals("NewUsername", test.getUsername());

        //test change password with incorrect old password
        test.changePassword("test", "incorrect");
        assertEquals("Test", test.getPassword());

        //test change password with correct old password
        test.changePassword("Test", "correct");
        assertEquals("correct", test.getPassword());

        //test change name
        test.changeName("firstname", "lastname");
        assertEquals("firstname", test.getFirstName());
        assertEquals("lastname", test.getLastName());

        //test change email
        test.changeEmail("test@gmail.com");
        assertEquals("test@gmail.com", test.getEmailAddress());

    }
}
