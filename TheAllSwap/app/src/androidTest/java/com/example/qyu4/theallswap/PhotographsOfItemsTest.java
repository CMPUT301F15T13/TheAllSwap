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
            tradeItem.addImage(123);
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
        tradeItem.addImage(123);
        tradeItem.setItemName("fabulous watch");

        assertTrue(tradeItem.getItemImgId().contains(123));
    }

    public void testDeletePhotograph(){
        // US06.03.01
        Item tradeItem = new Item();
        tradeItem.addImage(123);
        tradeItem.setItemName("fabulous watch");

        tradeItem.removeImage(123);
        assertFalse(tradeItem.getItemImgId().contains(123));
    }

    public void testCheckPhotographSize(){
        // US06.04.01
        Item tradeItem = new Item();
        tradeItem.addImage(123);
        tradeItem.setItemName("fabulous watch");
        // Attach a over size photo - photoOverSize
        tradeItem.addImage(123);
        assertFalse(tradeItem.checkImageSize());
    }

    public void testDisablePhotographDownload(){
        // US06.05.01
        Item tradeItem = new Item();
        tradeItem.addImage(123);
        tradeItem.setItemName("fabulous watch");

        tradeItem.disableImageDownload();
        Boolean isDisabled = true;
        try {
            tradeItem.downloadImage(0);
        } catch (IllegalArgumentException e){
            isDisabled = false;
        }


        if (isDisabled == false){
            assertFalse(true);                      // ends the test
        }
    }
}