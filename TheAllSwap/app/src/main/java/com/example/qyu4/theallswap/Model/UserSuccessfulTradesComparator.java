package com.example.qyu4.theallswap.Model;

import java.util.Comparator;

/**
 * Created by aozero on 29/11/2015.
 */
public class UserSuccessfulTradesComparator implements Comparator<User> {
    public int compare(User user1, User user2) {
        return user2.getSuccessfulTrades() - user1.getSuccessfulTrades();
    }
}
