import com.example.qyu4.theallswap.Inventory;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;

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
		myUser.setOwnership(true);
		myItem.setShared(false);
		myUser.addItemToInventory(myItem);
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
