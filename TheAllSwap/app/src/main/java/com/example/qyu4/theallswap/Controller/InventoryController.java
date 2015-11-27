package com.example.qyu4.theallswap.Controller;

import android.content.Context;
import android.content.Intent;

import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;

import java.util.ArrayList;

/**
 * InventoryController class is an Controller that handling all method that dealing with Item model class.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class InventoryController {
    /**
     * add an item to current user's inventory list.
     * @param currentUser: current user name.(it's User object here).
     * @param item: the item current user want to add to the inventory(this method could be used in the trade success part).
     */
    public void addItemToInventory(User currentUser, Item item){
        currentUser.addItemToInventory(item);
    }

    public void removeItemFromInventory(User currentUser, Item item){
        currentUser.removeItemFromInventory(item);
    }

    /**
     * create a new item
     * @param itemName: item name
     * @param itemQuantity: item quantity
     * @param itemQuality: item quality
     * @param itemCategory: item category
     * @param itemPrivacy: item privacy
     * @param itemComment: item comments
     * @return a new Item
     */
    public Item createNewItem(String itemName, int itemQuantity, String itemQuality, String itemCategory,
                              boolean itemPrivacy, String itemComment){
        Item newItem = new Item();
        newItem.setItemName(itemName);
        newItem.setItemQuantity(itemQuantity);
        newItem.setItemQuality(itemQuality);
        newItem.setItemCategory(itemCategory);
        newItem.setItemPrivacy(itemPrivacy);
        newItem.setItemComments(itemComment);
        return newItem;
    }

    /**
     * edit an item and save it in the current local userList.
     * @param itemId: the index of current item
     * @param newItem: the item that will replace the old one
     */
    public void editItem(User currentUser, int itemId, Item newItem){
        currentUser.getUserInventory().set(itemId, newItem);
    }

    public ArrayList<String> showNonPrivateItems(User friend){
        ArrayList<String> invList = new ArrayList<>();
        for(Item i : friend.getInventory()){
            if(!i.isPrivate() && i.isAvailable()) {
                invList.add(i.getItemName());
            }
        }
        return invList;
    }

    public ArrayList<String> showItemsInCategory (User friend, String category){
        ArrayList<String> invList = new ArrayList<>();
        String all = "All";
        for(Item i : friend.getInventory()){
            if(!i.isPrivate() && i.isAvailable()) {
                if (i.getItemCategory().equals(category) || category.equals(all)){
                    invList.add(i.getItemName());
                }
            }
        }
        return invList;
    }

    public ArrayList<String> searchSuggestions (User friend, String search){
        ArrayList<String> invList = new ArrayList<>();
        for(Item i : friend.getInventory()){
            if(!i.isPrivate() && i.isAvailable()) {
                if (i.getItemName().contains(search)){
                    invList.add(i.getItemName());
                }
            }
        }
        return invList;
    }

    public Item getItemByName(String name, User owner){
        Item item = new Item();
        for(Item i : owner.getInventory()) {
            if (name.equals(i.getItemName())) {
                item = i;
            }
        }
        return item;
    }

}
