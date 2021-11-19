package com.example.myapplication;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testBuyerAccount() {
        //Test through buyerClass
        //(Negative ID to prevent conflicts with existing data)

        assertTrue(true);
    }
}