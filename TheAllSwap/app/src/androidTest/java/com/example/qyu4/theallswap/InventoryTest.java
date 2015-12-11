package com.example.qyu4.theallswap;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.User;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class InventoryTest {

	@Test
	public void testAddItem(){
		Item myItem = new Item();
		User myUser = new User();
		myUser.addItemToInventory(myItem);
		assertTrue(myUser.getInventory().contains(myItem));
	}
	
	@Test
	public void testDeleteItem(){
		Item myItem = new Item();
		User myUser = new User();
		myUser.addItemToInventory(myItem);
		assertTrue(myUser.getInventory().contains(myItem));
		myUser.removeItemFromInventory(myItem);
		assertFalse(myUser.getInventory().contains(myItem));
	}
	
	@Test
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
	
	@Test
	public void testViewMyUnsharedItems(){
		Item myItem = new Item();
		User myUser = new User();
		myUser.addItemToInventory(myItem);
		assertTrue(myUser.getInventory().contains(myItem));
	}
	
	@Test
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

	@Test
	public void testViewUnsharedItemsNotMine() {
		User notMe = new User();
		Item item = new Item();
		item.setItemPrivate(false);
		notMe.addItemToInventory(item);
		assertFalse(notMe.getInventory().contains(item));
	}
	
	@Test
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
	
	@Test
	public void testAllowedCatagory(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		ArrayList<String> allowedCatagories = new ArrayList<String>();	
		assertTrue(allowedCatagories.contains(myItem.getItemCategory()));
		myItem.setItemCategory("somethingNotInList");
		assertFalse(allowedCatagories.contains(myItem.getItemCategory()));
	}
}
