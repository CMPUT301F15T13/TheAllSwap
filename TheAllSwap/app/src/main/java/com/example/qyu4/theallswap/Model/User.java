package com.example.qyu4.theallswap.Model;

import com.example.qyu4.theallswap.Friends;

import java.util.ArrayList;

/**
 * Created by qyu4 on 10/20/15.
 */
public class User {
    private String userId;
    private String userPassword;
    private ArrayList<Item> userInventory = new ArrayList<Item>();
    private ArrayList<User> userFriendList = new ArrayList<User>();
    private ArrayList<Trade> userRequestTradeList = new ArrayList<Trade>();
    private ArrayList<Trade> userOfferTradeList = new ArrayList<Trade>();
    private Profile userProfile;
    public User(String userId, String userPassword, ArrayList<Item> userInventory,
                ArrayList<User> userFriendList, ArrayList<Trade> userRequestTradeList,
                ArrayList<Trade> userOfferTradeList, Profile userProfile) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userInventory = userInventory;
        this.userFriendList = userFriendList;
        this.userRequestTradeList = userRequestTradeList;
        this.userOfferTradeList = userOfferTradeList;
        this.userProfile = userProfile;
    }

    /**
     * set user id
     * @param userId: generate user id from user input
     */
    public void setUserId(String userId) {
        this.userId = userId;

    }

    /**
     * set user password
     * @param userPassword: generate user password from user input.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * add new Friend to friend list.
     * @param newFriend: generate friend name from user input.
     */
    public void addFriend(User newFriend){
        userFriendList.add(newFriend);
    }

    /**
     * remove a friend from friend list.
     * @param friendToRemove: friend user id to remove from friend list.
     */
    public void removeFriend(User friendToRemove){
        userFriendList.remove(userFriendList.indexOf(friendToRemove));
    }

    /**
     * add new item to inventory.
     * @param newItem: add given item to the inventory.
     */
    public void addItemToInventory(Item newItem){
        userInventory.add(newItem);
    }

    /**
     * remove given item from inventory.
     * @param item: the item to be deleted.
     */
    public void removeItemFromInventory(Item item){
        userInventory.remove(userInventory.indexOf(item));
    }

    /**
     * edit given item
     * @param item: the item to be edited
     * @return modified item
     */
    public Item editInventoryItem(Item item){
        //TODO: edit item functionality to be done.
        return item;
    }

}