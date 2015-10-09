package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by ozero. Test file is skeletal.
 */
public class ProfileTest extends ActivityInstrumentationTestCase2 {

    public ProfileTest() {
        super(UserProfileActivity.class);
    }

    public void testCreateProfile() {
        assertFalse(testProfileExists());
        // TODO Add profile
        assertTrue(testProfileExists());
    }

    public void testViewProfile() {
        // TODO Load profile to view
        assertTrue(testProfileLoaded());
    }

    public boolean testProfileExists() {
        return true;
    }

    public boolean testProfileLoaded() {
        return true;
    }
}
