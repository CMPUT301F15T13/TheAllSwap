package com.example.qyu4.theallswap.Controller;

import android.content.Context;
import android.content.Intent;

import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;

import java.util.ArrayList;

/**
 * Created by qyu4 on 11/1/15.
 */
public class InventoryController {

    public void addItemToInventory(User currentUser, Item item){
        currentUser.addItemToInventory(item);
    }
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
    public ArrayList<User> editItem(ArrayList<User> userList, int currentUserId, int itemId, Item newItem){
        userList.get(currentUserId).getUserInventory().set(itemId, newItem);
        return userList;
    }

}
