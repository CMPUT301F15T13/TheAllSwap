package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Model.User;

/**
 * Created by ozero. Test file is skeletal.
 */
public class FriendsTest extends ActivityInstrumentationTestCase2 {

    public FriendsTest() {
        super(UserProfileActivity.class);
    }

    public void testAddFriend() {
        User me = new User();
        User friend = new User();
        assertFalse(me.isFriend(friend));
        me.addFriend(friend);
        assertTrue(me.isFriend(friend));
    }

    public void testRemoveFriend() {
        User me = new User();
        User friend = new User();
        me.addFriend(friend);
        assertTrue(me.isFriend(friend));
        me.removeFriend(friend);
        assertFalse(me.isFriend(friend));
    }

    //public void testFriendExists() {
    //    assertTrue(friend);
    //}
}
