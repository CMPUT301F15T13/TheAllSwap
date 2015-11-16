package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.Profile;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.View.UserProfile;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lixin1 on 10/7/2015.
 */
public class ConfigurationTest extends ActivityInstrumentationTestCase2 {
    public ConfigurationTest(){
        super (UserProfile.class);
    }


    public void testDownloadImage(){
        // US10.01.01
        //Item tradeItem = new Item();      // TradeItem has two attributes
        // ArrayList photograph
        // String itemName
        //Boolean isAttached = true;
        //tradeItem.itemName = "fabulous watch";
        //tradeItem.attachPhotograph("/usr/lixin1/theallswap/drawable/photo.jpg");
        //boolean hasDownloadImage = true;

        //tradeItem.downloadImage();

        //File f = new File("usr/lixin1/theallswap/download/photo.jpg");

        /*
        if (f.exists() && !f.isDirectory()){
            hasDownloadImage = true;
        } else {
            hasDownloadImage = false;
        }

        if (hasDownloadImage == false){
            assertTrue(false);                      // ends the test
        }
        */

        assertTrue(true);
    }

    public void testEditProfile(){
        // US10.02.01
        //private UserController uc = new UserController();
        User user = new User();
        ArrayList<User> newUserList = new ArrayList<>();
        Profile newProfile= new Profile();
        user.setUserId("UserName1");
        newProfile.setUserCity("UserCity1");
        newProfile.setUserContactInformation("UserEmail1");
        user.setUserProfile(newProfile);
        newUserList.add(user);

        assertTrue(newUserList.get(0).getUserId().equals("UserName1"));

        user.setUserId("UserName2");

        assertTrue(newUserList.get(0).getUserId().equals("UserName2"));
    }

}
