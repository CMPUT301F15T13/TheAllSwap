package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.View.UserProfile;

/**
 * Created by ozero. Test file is skeletal.
 */

public class FriendsTest extends ActivityInstrumentationTestCase2 {

    public FriendsTest() {
        super(UserProfile.class);
    }

    public void testAddFriend() {
        User me = new User();
        User friend = new User("Ute");
        assertFalse(me.isFriend(friend));
        me.addFriend(friend.getUserId());
        assertTrue(me.isFriend(friend));
    }

    public void testRemoveFriend() {
        User me = new User();;
        User friend = new User("Amanda");
        me.addFriend(friend.getUserId());
        assertTrue(me.isFriend(friend));
        me.removeFriend(friend.getUserId());
        assertFalse(me.isFriend(friend));
    }

    //public void testFriendExists() {
    //    assertTrue(friend);
    //}
}
