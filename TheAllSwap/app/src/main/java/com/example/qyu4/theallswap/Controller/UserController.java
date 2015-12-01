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
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import java.lang.reflect.Type;

import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.View.UserInventory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Control User Class data.
 *
 * @author qyu4, egsmith, lixin1, ozero, debelang
 * Created by qyu4 on 10/24/15.
 */
public class UserController {

    /**
     * make text when user input two passwords are not equal.
     *
     * @param context: for the convenience of Toast methods.
     */
    public void makeInvalidUserToast(Context context){
        //I think this is pretty redundant since we only use it once. May as well be generic toast
        Toast.makeText(context, "Invalid user name!", Toast.LENGTH_LONG).show();
    }

    /**
     * make toast that is based on user input string.(this is for testing values in program...)
     * @param context: current activity. (eg. UserFriends.class)
     * @param inputString: String that is want to be shown at certain point.
     */
    public void makeInputStringToast(Context context, String inputString){
        Toast.makeText(context, inputString, Toast.LENGTH_LONG).show();
    }

    /**
     * save object in Gson style in the file.
     *
     * @param FileName: file name
     * @param context: convenience of openFileOutput method.
     * @param userArrayList: the class type that will be saved in the file.
     */
    public void saveInFile(String FileName, Context context, ArrayList<User> userArrayList) {
            try {
                FileOutputStream fos = context.openFileOutput(FileName, 0);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
                Gson gson = new Gson();
                gson.toJson(userArrayList, out);
                out.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e);
            }
        }



    /**
     * Intent method between activities.
     *
     * @param newClass: the target activity.
     * @param context: the convenience of Intent.
     */
    public void classIntent(Class newClass, Context context){
        Intent openNewActivity = new Intent(context, newClass);
        context.startActivity(openNewActivity);
    }

    /**
     * loading the file that is given and return a user array list.
     * @param context: current activity.
     * @param fileName: file name.
     */
    public void loadUsersFromFile(Context context, String fileName, UserList userList) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            ArrayList<User> loadedList = gson.fromJson(in, userListType);
            userList.clear();
            for(User u : loadedList){
                userList.add(u);
            }
        } catch (FileNotFoundException e) {

        }
    }

    /**
     * check loaded user list has current input user name or not.
     * @param inputUserName: input user name
     * @param userList: loaded userList
     * @return: if current user exists, return true; false otherwise.
     */
    public boolean checkingUserExist(String inputUserName, ArrayList<User> userList){
        for(int i=0; i< userList.size(); i ++) {
            String userName = userList.get(i).getUserId();

            if (inputUserName.equals(userName)) {
                return true;
            }

        }
        return false;
    }

    /**
     * given a user name and the user list find the user object.
     * If none is found return a new user object with 'No User' as ID
     * ***Should change this function to throw a 'UserNotFoundException'***
     * or something along those lines.
     * @param inputUsername: user name.
     * @param userList: user array list.
     * @return: the user object.
     */
    public User findUserById(String inputUsername, ArrayList<User> userList) {
        User currentUser = new User();
        for(int i=0; i < userList.size(); i++) {
            String username = userList.get(i).getUserId();
            if (inputUsername.equals(username)) {
                currentUser = userList.get(i);
            }
        }
        return currentUser;
    }

    /**
     * find the index of given user.
     * @param inputUserName: user name.
     * @param userList: user array list.
     * @return: the index of the user that is found.
     */
    public int findUserIndexById(String inputUserName, ArrayList<User> userList){
        for(int i=0; i< userList.size(); i ++) {
            String userName = userList.get(i).getUserId();
            if (inputUserName.equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * convert the User object list to a useful information arrayList.
     * @param userList: the UserList has all User objects.
     * @param resultList: an arrayList has all the user information. it
     *                  calls toString() method of User class is an override method.
     * @return the result list.
     */
    public ArrayList<String> convertUserToString(ArrayList<User>userList, ArrayList<String> resultList){
        resultList.clear();
        for (int i=0; i< userList.size(); i++){
            resultList.add(userList.get(i).getUserId());
        }

        return resultList;
    }

    /**
     * add a list of item name into the array list.
     * @param currentUser: current user object.
     * @param resultList: an array of item names.
     * @return: an array of item names.
     */
    public ArrayList<String> convertItemToString(User currentUser, ArrayList<String> resultList){
        resultList.clear();
        for (Item i : currentUser.getUserInventory()){
            resultList.add(i.getItemName());
        }
        return resultList;
    }

    /**
     * make toast of all item in user list.
     * @param context: current context
     * @param userList: the arrayList tester want to test.
     */
    public void checkingUserListContent(Context context, ArrayList<User>userList){
        for(int i=0; i<userList.size();i++){
            makeInputStringToast(context, userList.get(i).getUserId());
        }
    }

    /**
     * remove one user from the list.
     * @param userList: the arrayList that contains all the users.
     * @param position: the index of user to be deleted.
     */
    public ArrayList<User> removeUser(ArrayList<User> userList, int position){
        userList.remove(position);
        return userList;
    }

    /**
     * remove an item from an arrayList mostly for the use of delete an item from
     * the result list.
     * @param itemList: the arrayList that contains result items.
     * @param position: the index of item to be deleted.
     * @return a new arrayList.
     */
    public ArrayList removeItem(ArrayList itemList, int position){
        itemList.remove(position);
        return itemList;
    }

    /**
     * passing values between activities.
     * @param newClass: the intended activity.
     * @param context: current activity.
     * @param userPosition: index of current object.
     */
    public void passValueToActivity(Class newClass, Context context, int userPosition){
        Intent openNewActivity = new Intent(context, newClass);
        openNewActivity.putExtra("id", String.valueOf(userPosition));
        context.startActivity(openNewActivity);
    }

    public void passUserToActivity(Class newClass, Context context, String myID){
        Intent openNewActivity = new Intent(context, newClass);
        openNewActivity.putExtra("myID", String.valueOf(myID));
        context.startActivity(openNewActivity);
    }


    /**
     * convert a string value to int.
     * @param inputString: input string.
     * @return: a integer value of string point to.
     */
    public int stringToInt(String inputString){
        int foo = Integer.parseInt(inputString);
        return foo;
    }

    /**
     * Adds a user to another users friend list.
     * @param currentUser: User to add a friend to their friend list
     * @param otherUser: User to be added as a friend.
     */
    //Currently unidirectional, should friendship be one sided?
    public void addUserAsFriend(Context context, User currentUser, String otherUser){
        if(currentUser.getUserId().equals(otherUser)){
            makeInputStringToast(context, "Error: Cannot add yourself");
            return;
        }
        if(currentUser.getFriendsList().contains(otherUser)){
            makeInputStringToast(context, "Error: " + otherUser + " already a friend");
        }
        else {
            currentUser.addFriend(otherUser);
            makeInputStringToast(context, otherUser + " added as friend");
        }
    }

    public void removeUserAsFriend(Context context, User currentUser, String otherUser){
        currentUser.removeFriend(otherUser);
        makeInputStringToast(context, otherUser + " removed from friends");
    }

    public void incrBorrowerSuccTrades(String borrowerId, ArrayList<User> userList) {
        for(User user : userList) {
            if(user.getUserId().equals(borrowerId)) {
                user.incrementSuccessfulTrades();
            }
        }
    }
}


