package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by qyu4 on 10/7/15.
 * this is the test file for testing user offline activities.
 * At the date Oct. 7th, 2015, this file is just a skeleton testfile and it does not
 * have it real functionality.
 */
public class OfflineUserActivityTest extends ActivityInstrumentationTestCase2 {
    public OfflineUserActivityTest(){
        super(UserEditInventoryActivity.class);
    }
    public void testNewItemCreated(){
        assertTrue(testIsOffline());
        //TODO: creating item offline;
        //TODO: reconnect internet;
        assertTrue(testIsOnline());
        //TODO: get result while online
        //TODO: check if new result and item created when offline is equal.

    }
    public void testAddItem(){
        //TODO: initialize a item name(string Type: itemName)
        //TODO: create an item setName(itemNam)
        //assertEquals(item.getName().equals(String));
    }
    public boolean testIsOffline(){
        return true;
    }
    public boolean testIsOnline(){
        return true;
    }

}
