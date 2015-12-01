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
 * Created by Eric on 2015-11-26.
 */
public class UserList extends ArrayList<User> {

    private static final UserList instance = new UserList();

    private final String filename;
    private User currentUser;

    private UserList() {
        super();
        filename = "userProfile.txt";
    }

    public static UserList getUserList() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getFilename(){
        return filename;
    }

    public void setCurrentUser(User current){
        currentUser = current;
    }

    public User getUserFromId(String friend) {
        for(User user : instance) {
            if(user.getUserId().equals(friend)) {
                return user;
            }
        }
        return null;
    }
}
