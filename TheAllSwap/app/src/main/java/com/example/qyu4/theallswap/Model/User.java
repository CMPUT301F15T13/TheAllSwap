package com.example.qyu4.theallswap.Model;

import com.example.qyu4.theallswap.Friends;
import com.example.qyu4.theallswap.Inventory;

import java.util.ArrayList;

/**
 * Created by qyu4 on 10/20/15.
 */
public class User {
    private String userId;

    private ArrayList<Item> userInventory= new ArrayList<>();
    private ArrayList<User> userFriendList = new ArrayList<>();
    //private ArrayList<Trade> userRequestTradeList = new ArrayList<>();
    //private ArrayList<Trade> userOfferTradeList = new ArrayList<>();

    public Profile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }

    private Profile userProfile;

    public User(){}

    //This constructor breaks for some reason
    public User(String userId) {
        this.userId = userId;
        this.userInventory = new ArrayList<Item>();
        this.userFriendList = new ArrayList<User>();
        //this.userRequestTradeList = new ArrayList<Trade>();
        //this.userOfferTradeList = new ArrayList<Trade>();
        this.userProfile.setUserCity(null);
        this.userProfile.setUserContactInformation(null);
    }

    //As, I must assume does this one.
    public User(String userId, String userEmail, String userCity) {
        this.userId = userId;
        this.userInventory = new ArrayList<Item>();
        this.userFriendList = new ArrayList<User>();
        //this.userRequestTradeList = new ArrayList<Trade>();
        //this.userOfferTradeList = new ArrayList<Trade>();
        this.userProfile.setUserCity(null);
        this.userProfile.setUserContactInformation(null);
    }

    public ArrayList<User> getFriendsList(){
        return userFriendList;
    }

    /**
     * get user id
     * @return user id
     */
    public String getUserId() {
        return userId;
    }
    /**
     * set user id
     * @param userId: generate user id from user input
     */

    public void setUserId(String userId) {
        this.userId = userId;

    }

    /**
     * Checks if friend has been added previously to friend list
     * @param friend: friend to check if in list
     */
    public boolean isFriend(User friend) {
        if(userFriendList.contains(friend)){
            return true;
        }
        return false;
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
     * add new item to inventory.
     */
    public ArrayList<Item> getInventory(){
        return userInventory;
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

    /**
     * restructure the layout string in the friend list view.
     * @return  output a string that contains current user's name, city and email.
     */
    @Override
    public String toString(){
        return userId + " || "+ userProfile.getUserCity()+" || "+userProfile.getUserContactInformation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    public ArrayList<Item> getUserInventory() {
        return userInventory;
    }
}
