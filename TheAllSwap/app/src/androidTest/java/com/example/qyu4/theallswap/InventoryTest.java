import com.example.qyu4.theallswap.Inventory;
import com.example.qyu4.theallswap.Model.Item;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class InventoryTest {

	@Test
	public void testAddItem(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		myInventory.add(myItem);
		assertTrue(myInventory.getItems.contains(myItem));
	}
	
	@Test
	public void testDeleteItem(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		myInventory.add(myItem);
		assertTrue(myInventory.getItems.contains(myItem));
		myInventory.remove(myItem);
		assertFalse(myInventory.getItems.contains(myItem));
	}
	
	@Test
	public void testViewItemList(){
		Item myItem1 = new Item();
		Item myItem2 = new Item();
		Item myItem3 = new Item();
		Inventory myInventory = new Inventory();
		myInventory.add(myItem1);
		myInventory.add(myItem2);
		myInventory.add(myItem3);
		assertEquals(myInventory.getItems.contains(myItem1));
		assertTrue(myInventory.getItems.contains(myItem2));
		assertTrue(myInventory.getItems.contains(myItem3));
	}
	
	@Test
	public void testViewMyUnsharedItems(){
		Item myItem = new Item();
		Inventory myInventory = new Inventory();
		myInventory.setOwnership(true);
		myItem.setShared(false);
		myInventory.add(myItem);
		assertTrue(myInventory.getItems.contains(myItem));
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
		assertTrue(myOffer.getItems.contains(myItem));
	}

	@Test
	public void testViewUnsharedItemsNotMine() {
		Item item = new Item();
		Inventory notMyInventory = new Inventory();
		notMyInventory.setOwnership(false);
		item.setShared(false);
		notMyInventory.add(item);
		assertFalse(notMyInventory.getItems.contains(item));
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
		assertTrue(allowedCatagories.contains(myItem.getCatagory()));
		myItem.setCatagory("somethingNotInList"); 
		assertFalse(allowedCatagories.contains(myItem.getCatagory()));
	}
}
