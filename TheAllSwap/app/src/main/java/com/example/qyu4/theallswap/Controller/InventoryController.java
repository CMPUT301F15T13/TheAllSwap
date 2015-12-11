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

import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;

import java.util.ArrayList;

/**
 * A controller class that provides the views access to the sections of the model that are related
 * to items and inventory management.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
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

    /**
     * Removes an item to current user's inventory list.
     * @param currentUser: current user name.(it's User object here).
     * @param item: the item current user want to add to the inventory(this method could be used in the trade success part).
     */
    public void removeItemFromInventory(User currentUser, Item item){
        currentUser.removeItemFromInventory(item);
    }

    /**
     * Creates a new item with the passed values as attributes.
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
        newItem.setItemPrivate(itemPrivacy);
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

    /**
     * Method that iterates through all Users within the UserList and for each one iterates through
     * all Items in their inventories. Any Item that is public (itemPrivate attribute set to false)
     * is added to a new list of Items. This list is then returned after iterating through all
     * users.
     * @return An ArrayList of Item objects containing all user's non-private items.
     */
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

    /**
     * Method that iterates through a single user's inventory adding all non-private items to an
     * ArrayList which is returned.
     * @param friend: User who's inventory is to be searched.
     * @return ArrayList of Items containing a single user's non-private items.
     */
    public ArrayList<Item> showNonPrivateItems(User friend){
        ArrayList<Item> invList = new ArrayList<>();
        for(Item i : friend.getInventory()){
            if(!i.isPrivate() && i.isAvailable()) {
                invList.add(i);
            }
        }
        return invList;
    }

    /**
     * Method that iterates through a passed ArrayList of items and creates a new list containing
     * only those that have a category attribute matching the string passed as the second argument.
     * @param items: ArrayList of items to be filtered.
     * @param category String to filter items out based upon.
     * @return Filtered ArrayList
     */
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

    /**
     * Method that iterates through a passed ArrayList of items and creates a new list containing
     * only those that contain a specified substring within the Item's name.
     * @param items The list of items to be filtered.
     * @param search The Substring to search for in each item's name
     * @return Filtered ArrayList
     */
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

    /**
     * Method that for getting an Item object owned by a specific user based on item name.
     * Iterates through the specified user's inventory comparing each item's name to the passed
     * String.
     * @param name String being searched for in inventory.
     * @param owner User who owns Item
     * @return The item object with the name given. If no Item was found returns blank Item.
     */
    public Item getItemByName(String name, User owner){
        Item item = new Item();
        for(Item i : owner.getInventory()) {
            if (name.equals(i.getItemName())) {
                item = i;
            }
        }
        return item;
    }

    /**
     * Method that takes an existing item, creates a copy of it, and adds it to a user's inventory.
     * Method does not save UserList, that is up to the caller. Save method can be found in
     * UserController.
     * The comments and quantity are not cloned from the target item, instead they are set to
     * "cloned" and 1 respectively.
     * @param target Item to be cloned
     * @param currentUser User to have item added to inventory. Usually will be the current user.
     */
    public void cloneItem(Item target, User currentUser){
        Item clone = createNewItem(
                target.getItemName(),
                1,
                target.getItemQuality(),
                target.getItemCategory(),
                target.isPrivate(),
                "Cloned"
        );
        currentUser.addItemToInventory(clone);
    }

    /*
    public void uploadImagetoItem(int imgId) {

    };
    */
}
