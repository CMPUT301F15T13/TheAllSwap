/*
 * Copyright 2015 Alexander Ozero, Qiang Yu, Eric Smith, Lixin Jin, Daniel Belanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.qyu4.theallswap.Controller;

import android.content.Context;
import android.content.Intent;

import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;

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

    public ArrayList<Item> showAllNonPrivateItems(){
        ArrayList<Item> invList = new ArrayList<>();
        UserList userList = UserList.getUserList();
        User currentUser = userList.getCurrentUser();
        for(User u : userList) {
            if(currentUser.getFriendsList().contains(u.getUserId())) {
                for (Item i : u.getInventory()) {
                    if (!i.isPrivate() && i.isAvailable()) {
                        invList.add(i);

                    }
                }
            }
        }
        return invList;
    }

    public ArrayList<Item> showNonPrivateItems(User friend){
        ArrayList<Item> invList = new ArrayList<>();
        for(Item i : friend.getInventory()){
            if(!i.isPrivate() && i.isAvailable()) {
                invList.add(i);
            }
        }
        return invList;
    }

    public ArrayList<Item> showItemsInCategory (ArrayList<Item> items, String category){
        ArrayList<Item> invList = new ArrayList<>();
        String all = "All";
        for(Item i : items){
            if(!i.isPrivate() && i.isAvailable()) {
                if (i.getItemCategory().equals(category) || category.equals(all)){
                    invList.add(i);
                }
            }
        }
        return invList;
    }

    public ArrayList<Item> searchSuggestions (ArrayList<Item> items, String search){
        ArrayList<Item> invList = new ArrayList<>();
        for(Item i : items){
            if(!i.isPrivate() && i.isAvailable()) {
                if (i.getItemName().contains(search)){
                    invList.add(i);
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

    public void uploadImagetoItem(int imgId) {

    };

}
