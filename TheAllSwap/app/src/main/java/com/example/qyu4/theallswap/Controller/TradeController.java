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
 * Created by aozero on 28/11/2015.
 */
public class TradeController {
    public void saveTradeInFile(String FileName, Context context, ArrayList<Trade> tradeArrayList) {
        try {
            FileOutputStream fos = context.openFileOutput(FileName, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(tradeArrayList, out);
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