package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by ozero. Test file is skeletal.
 */
public class FriendsTest extends ActivityInstrumentationTestCase2 {

    public FriendsTest() {
        super(UserProfileActivity.class);
    }

    public void testAddFriend() {
        assertFalse(testFriendExists());
        // TODO Add friend
        assertTrue(testFriendExists());
    }

    public void testRemoveFriend() {
        assertTrue(testFriendExists());
        // TODO Remove friend
        assertFalse(testFriendExists());
    }

    public boolean testFriendExists() {
        return true;
    }
}
