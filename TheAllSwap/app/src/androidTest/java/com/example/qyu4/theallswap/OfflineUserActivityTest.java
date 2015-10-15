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
        ArrayList<Item> itemList = new ArrayList<Item>();
        assertTrue(testIsOffline());
        //TODO: creating item offline;
        Item itemOffline = new Item("offline item", "category", "offline item description");
        itemList.add(itemOffline);
        //TODO: reconnect internet;
        assertTrue(testIsOnline());
        //TODO: get result while online
        //TODO: check if new result and item created when offline is equal.
        assertEqual("offline item", item.getName(0));
    }
    public void testAddItem(){
        //TODO: initialize a item name(string Type: itemName)
        //TODO: create an item setName(itemNam)
        //assertEquals(item.getName().equals(String));
    }
    public boolean testIsOffline(){
        boolean cantConnect = NetworkConnector.tryConnect(); 
        return not canConnect;
    }
    public boolean testIsOnline(){
        boolean canConnect = NetworkConnector.tryConnect(); 
        return true;
    }

}
