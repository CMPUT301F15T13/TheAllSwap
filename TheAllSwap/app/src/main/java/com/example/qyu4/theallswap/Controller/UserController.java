package com.example.qyu4.theallswap.Controller;


import android.content.Context;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;
import java.lang.reflect.Type;
import com.example.qyu4.theallswap.Model.User;
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
     * Checking if two input passwords are equal.
     * @param firstPassword: user input first password.
     * @param secondPassword: user input second password.
     * @return: boolean indicating if two passwords are equal.
     */
    public boolean registerNewUserPasswordCheck(String firstPassword, String secondPassword){
        if (firstPassword.equals(secondPassword)){

            return true;
        }else {
            return false;
        }
    }

    /**
     * make text when user input two passwords are not equal.
     *
     * @param context: for the convenience of Toast methods.
     */
    public void makeInvalidUserToast(Context context){
        Toast.makeText(context, "invalid user name!", Toast.LENGTH_LONG).show();
    }
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
     * s
     * @param newClass: the target activity.
     * @param context: the convenience of Intent.
     */
    public void classIntent(Class newClass, Context context){
        Intent openNewActivity = new Intent(context, newClass);
        context.startActivity(openNewActivity);
    }

    /**
     * Fixing needed
     * @param context
     * @param fileName
     */
    public ArrayList<User> loadUserFromFile(Context context, String fileName, ArrayList<User> userList) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            userList = gson.fromJson(in, userListType);

        } catch (FileNotFoundException e) {
            userList = new ArrayList<User>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList;
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

    public User findCurrentUserObject(String inputUserName, ArrayList<User> userList){
        User currentUser = new User();
        for(int i=0; i< userList.size(); i ++) {
            String userName = userList.get(i).getUserId();
            if (inputUserName.equals(userName)) {
                currentUser = userList.get(i);
            }
        }
        return currentUser;
    }

    /**
     * convert the User object list to a useful information arrayList.
     * @param userList: the UserList has all User objects.
     * @param resultList: an arrayList has all the user information. it
     *                  calls toString() method of User class is an override method.
     * @return the result list.
     */
    public ArrayList convertUserToString(ArrayList<User>userList, ArrayList resultList){
        for (int i=0; i< userList.size(); i++){
            resultList.add(userList.get(i).toString());
        }

        return resultList;
    }
    public ArrayList convertItemToString(User currentUser, ArrayList resultList){
        for (int i=0; i< currentUser.getUserInventory().size(); i++){
            resultList.add(currentUser.getUserInventory().get(i).getItemName());
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
    public ArrayList<User> removeUser(ArrayList<User>userList, int position){
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
}
