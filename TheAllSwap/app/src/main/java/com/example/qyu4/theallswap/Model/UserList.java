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
