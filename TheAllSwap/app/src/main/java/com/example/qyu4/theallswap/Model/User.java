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

    public User(String userId, String userPassword, ArrayList<Item> userInventory,
                ArrayList<User> userFriendList, ArrayList<Trade> userRequestTradeList,
                ArrayList<Trade> userOfferTradeList) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userInventory = userInventory;
        this.userFriendList = userFriendList;
        this.userRequestTradeList = userRequestTradeList;
        this.userOfferTradeList = userOfferTradeList;
    }

    public void setUserId(String userId) {
        this.userId = userId;

    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
