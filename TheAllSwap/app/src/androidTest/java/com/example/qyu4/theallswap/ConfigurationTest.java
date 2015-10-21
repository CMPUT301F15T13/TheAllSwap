package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import java.io.File;

/**
 * Created by lixin1 on 10/7/2015.
 */
public class ConfigurationTest extends ActivityInstrumentationTestCase2 {
    public ConfigurationTest(){
        super (UserProfileActivity.class);
    };

    public void testDownloadImage(){
        // US10.01.01
        TradeItem tradeItem = new TradeItem();      // TradeItem has two attributes
        // ArrayList photograph
        // String itemName
        Boolean isAttached = true;
        tradeItem.itemName = "fabulous watch";
        tradeItem.attachPhotograph("/usr/lixin1/theallswap/drawable/photo.jpg");
        boolean hasDownloadImage = true;

        tradeItem.downloadImage();

        File f = new File("usr/lixin1/theallswap/download/photo.jpg");

        if (f.exists() && !f.isDirectory()){
            hasDownloadImage = true;
        } else {
            hasDownloadImage = false;
        }

        if (hasDownloadImage == false){
            assertTrue(false);                      // ends the test
        }
    }

    public void teatEditProfile(){
        // US10.02.01
        Trader trader = new Trander();
        trader.name = "Terry";

        trader.editProfile.editName("Jerry");       // editProfile is a class

        assertTrue(trader.name.equals("Jerry"));
    }

}
