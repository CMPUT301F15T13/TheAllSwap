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
package com.example.qyu4.theallswap.Model;

import java.util.ArrayList;

/**
 * Created by qyu4 on 10/20/15.
 */
public class User {
    private String userId;

    private ArrayList<Item> userInventory= new ArrayList<>();
    private ArrayList<String> userFriendList = new ArrayList<>();

    public Profile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }

    private Profile userProfile;

    private int successfulTrades = 0;

    public User(){}

    /**
     * Constructor creates a new user with a specified name. The profile is instantiated with a
     * blank city and email address.
     * @param userId Name for the new user
     */
    public User(String userId) {
        this.userId = userId;
        this.userProfile = new Profile();
        this.userInventory = new ArrayList<>();
        this.userFriendList = new ArrayList<>();
        this.userProfile = new Profile();
        this.userProfile.setUserCity(" ");
        this.userProfile.setUserContactInformation(" ");
    }

    /**
     * Constructor that initializes User's name, email address and city.
     * @param userId New user's name
     * @param userEmail New user's email address
     * @param userCity New user's home city
     */
    public User(String userId, String userEmail, String userCity) {
        this.userId = userId;
        this.userInventory = new ArrayList<>();
        this.userFriendList = new ArrayList<>();
        this.userProfile = new Profile();
        this.userProfile.setUserCity(userCity);
        this.userProfile.setUserContactInformation(userEmail);
    }

    /**
     * Getter for User's friend list
     * @return ArrayList Strings with the names of all users in friends list
     */
    public ArrayList<String> getFriendsList(){
        return userFriendList;
    }

    /**
     * Get user's name
     * @return user name
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set user's name
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
        return userFriendList.contains(friend.getUserId());
    }

    /**
     * add new Friend to friend list.
     * @param newFriend: generate friend name from user input.
     */
    public void addFriend(String newFriend){
        userFriendList.add(newFriend);
    }

    /**
     * remove a friend from friend list.
     * @param friendToRemove: friend user id to remove from friend list.
     */
    public void removeFriend(String friendToRemove){
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

    /**
     * Overrides default equals method. A User class need only compare name as it is required to be
     * unique within the app.
     * @param o object to compare against.
     * @return Boolean specifying equality.
     */
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

    /**
     * Get user inventory
     * @return ArrayList of Items in inventory
     */
    public ArrayList<Item> getUserInventory() {
        return userInventory;
    }

    /**
     * Get the number of successful trades this user has participated in.
     * @return int number of trades
     */
    public int getSuccessfulTrades() {
        return successfulTrades;
    }

    /**
     * Increases number of successful trades by one. Should only be called after a trade has
     * successfully taken place.
     */
    public void incrementSuccessfulTrades() {
        successfulTrades++;
    }


}
