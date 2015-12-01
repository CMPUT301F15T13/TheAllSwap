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

    //This constructor breaks for some reason
    public User(String userId) {
        this.userId = userId;
        this.userInventory = new ArrayList<Item>();
        this.userFriendList = new ArrayList<>();
        this.userProfile.setUserCity(null);
        this.userProfile.setUserContactInformation(null);
    }

    //As, I must assume does this one.
    public User(String userId, String userEmail, String userCity) {
        this.userId = userId;
        this.userInventory = new ArrayList<Item>();
        this.userFriendList = new ArrayList<>();
        this.userProfile.setUserCity(null);
        this.userProfile.setUserContactInformation(null);
    }

    public ArrayList<String> getFriendsList(){
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

    public int getSuccessfulTrades() {
        return successfulTrades;
    }

    public void incrementSuccessfulTrades() {
        successfulTrades++;
    }


}
