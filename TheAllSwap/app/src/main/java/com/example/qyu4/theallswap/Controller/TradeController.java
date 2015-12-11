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

import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
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
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Controller class for providing the view access to the model in areas regarding trading between
 * users.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 */
public class TradeController {

    /**
     * Method that saves completed trades to local file.
     * @param FileName name of file to be saved
     * @param context current context of app
     * @param tradeArrayList Trade list state to be saved
     */
    public void saveTradeInFile(String FileName, Context context, ArrayList<Trade> tradeArrayList) {
        try {
            FileOutputStream fos = context.openFileOutput(FileName, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(tradeArrayList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads list of trades from file. Loads trades into TradeList data structure.
     * @param context current context of app
     * @param fileName file name to load from
     * @param tradeList the instance of TradeList singleton
     */
    public void loadTradesFromFile(Context context, String fileName, TradeList tradeList) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type tradeListType = new TypeToken<ArrayList<Trade>>() {}.getType();
            ArrayList<Trade> loadedList = gson.fromJson(in, tradeListType);
            tradeList.clear();
            for(Trade u : loadedList){
                tradeList.add(u);
            }
        } catch (FileNotFoundException e) {

        }
    }

    /**
     * Constructs a list of trades associated with a specific user that are pending.
     * Method iterates through all trades in a passed list and adds all those that are both pending
     * and have the specified user involved.
     * @param userId User to find trades for
     * @param tradeList List of trades to search
     * @return An ArrayList of Strings representing all pending trades for a user.
     */
    public ArrayList<String> getPendingTrades(String userId, TradeList tradeList) {
        ArrayList<Trade> pendingTradeList = new ArrayList<>();

        for(Trade trade : tradeList){
            if(trade.isTradePending()){
                if(trade.getOwnerId().equals(userId)
                        || trade.getBorrowerId().equals(userId)){
                    pendingTradeList.add(trade);
                }
            }
        }

        ArrayList<String> resultList = new ArrayList<>();
        resultList = convertTradeToString(pendingTradeList, resultList);
        return resultList;
    }

    /**
     * Constructs a list of complete trades associated with a specific user. Iterates through a list
     * of trades and filters out those that are not completed or do not have the user involved.
     * @param userId User to search for
     * @param tradeList List to Search
     * @return ArrayList of Strings representing the trades.
     */
    public ArrayList<String> getCompletedTrades(String userId, TradeList tradeList) {
        ArrayList<Trade> pendingTradeList = new ArrayList<>();

        for(Trade trade : tradeList){
            if(!trade.isTradePending()){
                if(trade.getOwnerId().equals(userId)
                        || trade.getBorrowerId().equals(userId)){
                    pendingTradeList.add(trade);
                }
            }
        }

        ArrayList<String> resultList = new ArrayList<>();
        resultList = convertTradeToString(pendingTradeList, resultList);
        return resultList;
    }

    /**
     * convert the Trade object list to a useful information arrayList.
     * @param tradeList: the TradeList has all Trade objects.
     * @param resultList: an arrayList has all the trade information. It calls toString()
     *                  method of Trade class as an override method.
     * @return the result list.
     */
    public ArrayList<String> convertTradeToString(ArrayList<Trade> tradeList,
                                                  ArrayList<String> resultList){
        resultList.clear();
        for (int i=0; i< tradeList.size(); i++){
            Trade trade = tradeList.get(i);
            resultList.add(trade.getBorrowerId()+ "'s " + trade.getBorrowerItem() + " for "
                    +trade.getOwnerId() + "'s " + trade.getOwnerItem());
        }

        return resultList;
    }

    /**
     * Searches an ArrayList of trades for a String. Compares the string to the value of each
     * trade's toString representation. Returns the index within the list if the string is found.
     * @throws IndexOutOfBoundsException
     * @param tradeString Representation of a trade to search for
     * @param tradeList List to search
     * @return Int index
     */
    public int getIndexOfTrade(String tradeString, ArrayList<Trade> tradeList) {
        for (int i=0; i< tradeList.size(); i++){
            Trade trade = tradeList.get(i);
            if (tradeString.equals(trade.getBorrowerId()+ "'s " + trade.getBorrowerItem() + " for "
                    +trade.getOwnerId() + "'s " + trade.getOwnerItem())) {
                return tradeList.indexOf(trade);
            }
        }
        throw new IndexOutOfBoundsException();
    }
}