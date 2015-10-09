import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestInventory {

	@Test
	public void testAddItem(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		myInventory.add(myItem);
		AssertTrue(myInventory.getItems.contains(myItem));
	}
	
	@Test
	public void testDeleteItem(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		myInventory.add(myItem);
		AssertTrue(myInventory.getItems.contains(myItem));
		myInventory.remove(myItem);
		AssertFalse(myInventory.getItems.contains(myItem));
	}
	
	@Test
	public void testViewMyUnsharedItems(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		myInventory.setOwnership(true);
		myItem.setShared(false);
		myInventory.add(myItem);
		AssertTrue(myInventory.getItems.contains(myItem));
	}
	
	@Test
	public void testAddPrivateItemsToTrade(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		myInventory.setOwnership(true);
		myItem.setShared(false);
		myInventory.add(myItem);
		
		Transaction myOffer = new Transaction();
		myOffer.add(myInventory.getItem(myItem));
		AssertTrue(myOffer.getItems.contains(myItem));
	}

	@Test
	public void testViewUnsharedItemsNotMine() {
		Item item = new Item();
		Inventory notMyInventory = new Inventory();
		notMyInventory.setOwnership(false);
		item.setShared(false);
		notMyInventory.add(item);
		AssertFalse(notMyInventory.getItems.contains(item));
	}
	
	@Test
	public void testEditItem(){
		final int quantity = 1;
		Item myItem = new Item(quantity);
		Inventory myInventory = new Inventory();
		myInventory.add(myItem);
		final int value = 6;
		myInventory.getItem(myItem).setQuantity(value);
		assertEquals(myInventory.getItem(myItem).getQuantity(), value);
		
	}
	
	@Test
	public void testAllowedCatagory(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		ArrayList<String> allowedCatagories = new ArrayList<String>();	
		AssertTrue(allowedCatagories.contains(myItem.getCatagory()));
		myItem.setCatagory("somethingNotInList"); 
		AssertFalse(allowedCatagories.contains(myItem.getCatagory()));
	}
}
