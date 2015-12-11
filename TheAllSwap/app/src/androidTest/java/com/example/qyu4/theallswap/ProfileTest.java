package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.View.UserMainView;
import com.example.qyu4.theallswap.View.UserProfile;

/**
 * Created by ozero. Test file is skeletal.
 */
public class ProfileTest extends ActivityInstrumentationTestCase2 {

    public ProfileTest() {
        super(UserProfile.class);
    }

    public void testCreateProfile() {
        //Profile constructor called in User constructor
        User me = new User("Grandma Apples");
        assertTrue(testProfileExists(me));
    }

    public void testViewProfile() {
        String name = "Johnny Schotsman";
        String email = "jdawg@ualberta.ca";
        String city = "E-town";
        User me = new User(name, email, city);
        assertTrue(me.getUserProfile().getUserCity().equals(city));
        assertTrue(me.getUserProfile().getUserContactInformation().equals(email));
    }

    public boolean testProfileExists(User user) {
        return user.getUserProfile() != null;
    }
}
