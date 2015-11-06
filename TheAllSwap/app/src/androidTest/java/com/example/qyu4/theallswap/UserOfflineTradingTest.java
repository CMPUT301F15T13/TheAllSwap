package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by qyu4 on 10/7/15.
 */
public class UserOfflineTradingTest extends ActivityInstrumentationTestCase2 {
    UserOfflineTradingTest(){
        super(UserTradingActivity.class);
    }
    /**
     * testCreateTradeOffline is related to use cases #3
     **/
    public void testCreateTradeOffline(){
        ArrayList<Trade> tradeList = new ArrayList<Trade>();
        //TODO: creating item offline;
        Trade tradeOffline = new Trade("offline Trade", "offer item description");
        itemList.add(tradeOffline);
        assertEqual("offline Trade", String.vavlueOf(tradeList.getItemIndex(0)));
        //TODO: get result while online
        //TODO: check if new result and item created when offline is equal.
        assertEqual("offline Trade", trade.getName(0));
    }
    /**
     * testCreateTradeOfflinePushed is related to use cases #4
     **/
    public void testCreateTradeOfflinePushed(){
        InternetConnection ic = new InternetConnection();
        ic.setInternet(false);
        assertTrue(testIsOffline());
        ArrayList<Trade> tradeList = new ArrayList<Trade>();
        //TODO: creating item offline;
        Trade tradeOffline = new Trade("offline Trade", "offer item description");
        tradeList.add(tradeOffline);
        ic.setInternet(true);
        assertTrue(testIsOnline());
        //TODO: get result while online
        //and load the result item into itemList 
        CreateTradeOffer.loadFile(tradeList);
        
        assertEqual("offline Trade", String.vavlueOf(tradeList.getItemIndex(0)));
        //TODO: check if new result and item created when offline is equal.
        assertEqual("offline Trade", trade.getName(0));
        
        //TODO: reconnect internet;
       
        //TODO: initialize a item name(string Type: itemName)
        //TODO: create an item setName(itemNam)
        //assertEquals(item.getName().equals(String));
    }
    public boolean testIsOffline(){
        boolean cantConnect = NetworkConnector.tryConnect(); 
        return !canConnect;
    }
    public boolean testIsOnline(){
        boolean canConnect = NetworkConnector.tryConnect(); 
        return true;
    }


}
