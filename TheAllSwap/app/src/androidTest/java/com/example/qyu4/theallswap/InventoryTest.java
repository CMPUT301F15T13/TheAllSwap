package com.example.qyu4.theallswap;
import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserSuccessfulTradesComparator;
import com.example.qyu4.theallswap.View.UserInventory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class InventoryTest extends ActivityInstrumentationTestCase2 {

    public InventoryTest() {super(UserInventory.class);}

	public void testAddItem(){
		Item myItem = new Item();
		User myUser = new User();
		myUser.addItemToInventory(myItem);
		assertTrue(myUser.getInventory().contains(myItem));
	}

	public void testDeleteItem(){
		Item myItem = new Item();
		User myUser = new User();
		myUser.addItemToInventory(myItem);
		assertTrue(myUser.getInventory().contains(myItem));
		myUser.removeItemFromInventory(myItem);
		assertFalse(myUser.getInventory().contains(myItem));
	}

	public void testViewItemList(){
		Item myItem1 = new Item();
		Item myItem2 = new Item();
		Item myItem3 = new Item();
		User myUser = new User();
		myUser.addItemToInventory(myItem1);
		myUser.addItemToInventory(myItem2);
		myUser.addItemToInventory(myItem3);
		assertTrue(myUser.getInventory().contains(myItem1));
		assertTrue(myUser.getInventory().contains(myItem2));
		assertTrue(myUser.getInventory().contains(myItem3));
	}

	public void testViewMyUnsharedItems(){
		Item myItem = new Item();
		User myUser = new User();
		myUser.addItemToInventory(myItem);
		assertTrue(myUser.getInventory().contains(myItem));
	}

	public void testAddPrivateItemsToTrade(){
		User myUser = new User("Isaac");
		User otherUser = new User("Frank");
		Item myItem = new Item();
		myItem.setItemName("Pumpkin");
		Item otherItem = new Item();
		otherItem.setItemName("Lightbulb");
		myUser.addItemToInventory(myItem);
		
		Trade myOffer = new Trade("Isaac", "Pumpkin", "Frank", "Lightbulb");

		assertTrue(myOffer.getOwnerItem().equals("Pumpkin"));
	}

	public void testViewUnsharedItemsNotMine() {
		User notMe = new User();
		Item item = new Item();
		item.setItemPrivate(true);
		notMe.addItemToInventory(item);
        ArrayList<Item> visibleItems = new ArrayList<>();
        InventoryController ic = new InventoryController();
        visibleItems = ic.showNonPrivateItems(notMe);
        assertFalse(visibleItems.contains(item));
	}

	public void testEditItem(){
		final int quantity = 1;
		Item myItem = new Item();
		myItem.setItemQuantity(quantity);
		User user = new User();
		user.addItemToInventory(myItem);
		final int value = 6;
		myItem.setItemQuantity(value);
		assertEquals(myItem.getItemQuantity(), value);
		
	}

	public void testItemCloned() {
        InventoryController ic = new InventoryController();
        Item item = new Item();
        item.setItemName("False face");
        User me = new User("Alf");
        assertFalse(me.getInventory().contains(item));
        ic.cloneItem(item, me);
        Item clone = me.getInventory().get(0);
        assertNotNull(clone);
    }

    public void testCloneIsCopy() {
        InventoryController ic = new InventoryController();
        Item item = new Item();
        String name = "Jetpack", category="Gadgets", qual="Silver";
        item.setItemName(name);
        item.setItemCategory(category);
        item.setItemQuality(qual);
        User me = new User("Condor");
        ic.cloneItem(item, me);
        Item clone = me.getInventory().get(0);
        assertEquals(clone.getItemName(), name);
        assertEquals(clone.getItemCategory(), category);
        assertEquals(clone.getItemQuality(), qual);
    }

    public void testTopTradersSorted(){
        User top = new User("Jean");
        User second = new User("George");
        User third = new User("Alberta");
        User fourth = new User("Yolanda");
        ArrayList<User> traders = new ArrayList<>();
        traders.add(second);
        traders.add(top);
        traders.add(fourth);
        traders.add(third);
        second.incrementSuccessfulTrades();
        Collections.sort(traders, new UserSuccessfulTradesComparator());
        assertEquals(traders.get(0), second);
        top.incrementSuccessfulTrades();
        top.incrementSuccessfulTrades();
        top.incrementSuccessfulTrades();
        second.incrementSuccessfulTrades();
        third.incrementSuccessfulTrades();
        Collections.sort(traders, new UserSuccessfulTradesComparator());
        assertEquals(traders.get(0), top); //3 trades
        assertEquals(traders.get(1), second); //2 trades
        assertEquals(traders.get(2), third); //1 trade
        assertEquals(traders.get(3), fourth); //0 trades
    }

	public void testAllowedCatagory(){
		Item myItem = new Item();
        myItem.setItemCategory("Secret");
		ArrayList<String> allowedCatagories = new ArrayList<String>();
        allowedCatagories.add("Secret");
		assertTrue(allowedCatagories.contains(myItem.getItemCategory()));
		myItem.setItemCategory("somethingNotInList");
		assertFalse(allowedCatagories.contains(myItem.getItemCategory()));
	}
}
