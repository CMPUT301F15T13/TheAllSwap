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
 * Singleton class for storing the list of users the app interacts with during a session. It also
 * stores the current logged in user as well as the file path where changes to the user list will
 * be saved.
 * @author egsmith, lixin1, ozero, debelang, qyu4.
 */
public class UserList extends ArrayList<User> {

    private static final UserList instance = new UserList();

    private final String filename;
    private User currentUser;

    private UserList() {
        super();
        filename = "userProfile.txt";
    }

    /**
     *  @return The instance to the UserList singleton.
     */
    public static UserList getUserList() {
        return instance;
    }

    /**
     *  @return the currently logged in user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     *  @return File name where list of users is saved locally
     */
    public String getFilename(){
        return filename;
    }

    /**
     *  @param current: The user to set as the currently logged in user.
     */
    public void setCurrentUser(User current){
        currentUser = current;
    }

    /**
     *  Helper function that iterates through the UserList to find the User object corresponding to
     *  an ID passed as a String.
     *  @param friend: String to search for within UserList.
     */
    public User getUserFromId(String friend) {
        for(User user : instance) {
            if(user.getUserId().equals(friend)) {
                return user;
            }
        }
        return null;
    }
}
