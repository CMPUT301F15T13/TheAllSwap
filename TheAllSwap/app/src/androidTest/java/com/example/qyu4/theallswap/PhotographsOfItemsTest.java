package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Model.Item;

/**
 * Created by lixin1 on 10/5/2015.
 */
public class PhotographsOfItemsTest extends ActivityInstrumentationTestCase2 {
    public PhotographsOfItemsTest(){
        super (Item.class);
    };

    public void testAttachPhotogragh(){
        // US06.01.01
        Item tradeItem = new Item();
        Boolean isAttached = true;
        tradeItem.setItemName("fabulous watch");

        try {
            tradeItem.setItemImgId("123");
        } catch (IllegalArgumentException e){
            isAttached = false;
        }

        if (isAttached == false){
            assertFalse(true);                      // ends the test
        }
    }

    public void testViewPhotograph(){
        // US06.02.01
        Item tradeItem = new Item();
        tradeItem.setItemImgId("123");
        tradeItem.setItemName("fabulous watch");

        assertTrue(tradeItem.getItemImgId()=="123");
    }

    public void testRemovePhotograph(){
        // US06.03.01
        Item tradeItem = new Item();
        tradeItem.setItemImgId("123");
        tradeItem.setItemName("fabulous watch");

        tradeItem.setItemImgId("321");
        assertFalse(tradeItem.getItemImgId() != "123");
    }

    public void testCheckPhotographSize(){
        // US06.04.01
        Item tradeItem = new Item();
        tradeItem.setItemImgId("123");
        tradeItem.setItemName("fabulous watch");
        // Attach a over size photo - photoOverSize
        tradeItem.setItemImgId("123");
        //assertFalse(tradeItem.checkImageSize()); Functionality doesn't exist
    }

    public void testDisablePhotographDownload(){
        // US06.05.01
        Item tradeItem = new Item();
        tradeItem.setItemImgId("123");
        tradeItem.setItemName("fabulous watch");

        tradeItem.setImgDownloadable(false);
        Boolean isDisabled = true;
        try {
            //tradeItem.downloadImage(0); functionality doesn't exist
        } catch (IllegalArgumentException e){
            isDisabled = false;
        }


        if (!isDisabled){
            assertFalse(true);                      // ends the test
        }
    }
}