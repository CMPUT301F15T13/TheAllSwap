package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.View.SingleUserFullInventory;

import java.util.ArrayList;

/**
 * Created by ozero. Test file is skeletal.
 */
public class SearchInventoryTest extends ActivityInstrumentationTestCase2 {
    public SearchInventoryTest() {
        super(SingleUserFullInventory.class);
    }

    public void testSearchInvByQuery() {
        InventoryController ic = new InventoryController();
        Item item1 = new Item();
        item1.setItemName("Spy Sword");
        Item item2 = new Item();
        item2.setItemName("Spy Hammer");
        User me = new User("Bert");
        me.addItemToInventory(item1);
        me.addItemToInventory(item2);
        ArrayList<Item> filteredList;
        filteredList = ic.searchSuggestions(me.getInventory(), "Sword");
        assertEquals(item1, filteredList.get(0));
        filteredList = ic.searchSuggestions(me.getInventory(), "Hammer");
        assertEquals(item2, filteredList.get(0));
        filteredList = ic.searchSuggestions(me.getInventory(), "Spy");
        assertTrue(filteredList.contains(item1));
        assertTrue(filteredList.contains(item2));

    }

    public void testSearchInvByCategory() {
        InventoryController ic = new InventoryController();
        Item item1 = new Item();
        item1.setItemName("Spy Sword");
        item1.setItemCategory("Sharp");
        Item item2 = new Item();
        item2.setItemName("Spy Hammer");
        item2.setItemCategory("Blunt");
        User me = new User("Bert");
        me.addItemToInventory(item1);
        me.addItemToInventory(item2);
        ArrayList<Item> filteredList;
        filteredList = ic.showItemsInCategory(me.getInventory(), "Sharp");
        assertEquals(item1, filteredList.get(0));
        filteredList = ic.showItemsInCategory(me.getInventory(), "Blunt");
        assertEquals(item2, filteredList.get(0));
        filteredList = ic.showItemsInCategory(me.getInventory(), "All");
        assertTrue(filteredList.contains(item1));
        assertTrue(filteredList.contains(item2));

    }

}
