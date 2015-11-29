package com.example.qyu4.theallswap.Controller;

import android.content.Context;

import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
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

    public ArrayList<String> getPendingTrades(User currentUser, TradeList tradeList) {
        ArrayList<Trade> pendingTradeList = new ArrayList<>();

        for(Trade trade : tradeList){
            if(trade.isTradePending()){
                if(trade.getOwnerId().equals(currentUser.getUserId())
                        || trade.getBorrowerId().equals(currentUser.getUserId())){
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
}