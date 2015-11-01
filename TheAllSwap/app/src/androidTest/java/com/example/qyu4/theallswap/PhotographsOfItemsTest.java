package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by lixin1 on 10/5/2015.
 */
public class PhotographsOfItemsTest extends ActivityInstrumentationTestCase2 {
    public PhotographsOfItemsTest(){
        super (UserTradingActivity.class);
    };

    public void testAttachPhotogragh(){
        // US06.01.01
        TradeItem tradeItem = new TradeItem();      // TradeItem has two attributes
        // ArrayList photograph
        // String itemName
        Boolean isAttached = true;
        tradeItem.itemName = "fabulous watch";

        try {
            tradeItem.attachPhotograph("/usr/lixin1/theallswap/drawable/photo.jpg");
        } catch (IllegalArgumentException e){
            isAttached = false;
        }

        try {
            tradeItem.attachPhotograph();
        } catch (IllegalArgumentException e){
            isAttached = false;
        }

        if (isAttached == false){
            assertFalse(true);                      // ends the test
        }
    }

    public void testViewPhotograph(){
        // US06.02.01
        TradeItem tradeItem = new TradeItem();      // TradeItem has two attributes
        // ArrayList photograph
        // String itemName
        tradeItem.photograph.add("/usr/lixin1/theallswap/drawable/photo.jpg");
        tradeItem.itemName = "fabulous watch";

        assertEquals(tradeItem.viewPhotograph().equal("/usr/lixin1/theallswap/drawable/photo.jpg"));
    }

    public void testDeletePhotograph(){
        // US06.03.01
        TradeItem tradeItem = new TradeItem();      // TradeItem has two attributes
        // ArrayList photograph
        // String itemName
        tradeItem.photograph.add("/usr/lixin1/theallswap/drawable/photo.jpg");
        tradeItem.itemName = "fabulous watch";

        tradeItem.deletePhotograph(0);
        assertTrue(tradeItem.hasPhotograph("/usr/lixin1/theallswap/drawable/photo.jpg"));
    }

    public void testCheckPhotographSize(){
        // US06.04.01
        TradeItem tradeItem = new TradeItem();      // TradeItem has two attributes
        // ArrayList photograph
        // String itemName
        tradeItem.itemName = "fabulous watch";
        // Attach a over size photo - photoOverSize
        tradeItem.photograph.add("/usr/lixin1/theallswap/drawable/photoOverSize.jpg");
        assertFalse(tradeItem.checkPhotographSize(0));
    }

    public void testDisablePhotographDownload(){
        // US06.05.01
        TradeItem tradeItem = new TradeItem();      // TradeItem has two attributes
        // ArrayList photograph
        // String itemName
        tradeItem.photograph.add("/usr/lixin1/theallswap/drawable/photo.jpg");
        tradeItem.itemName = "fabulous watch";

        tradeItem.disablePhotographDownload();
        isDisabled = true;
        try {
            tradeItem.downloadPhotograph(0);
        } catch (IllegalAccessException e){
            isDisabled = false;
        }


        if (isDisabled == false){
            assertFalse(true);                      // ends the test
        }
    }
}