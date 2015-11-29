package com.example.qyu4.theallswap.Model;

import java.util.ArrayList;

/**
 * Created by aozero on 28/11/2015.
 */
public class TradeList extends ArrayList<Trade> {
    private static final TradeList instance = new TradeList();

    private final String filename;
    private Trade currentTrade;

    private TradeList() {
        super();
        filename = "trades.txt";
    }

    public static TradeList getTradeList() {
        return instance;
    }

    public Trade getCurrentTrade() {
        return currentTrade;
    }

    public String getFilename(){
        return filename;
    }

    public void setCurrentTrade(Trade trade){
        currentTrade = trade;
    }
}
