package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
import com.example.qyu4.theallswap.View.UserInventory;
import com.example.qyu4.theallswap.Model.User;

/**
 * Created by debelang on 10/9/15.
 */
public class TradeItemsTest extends ActivityInstrumentationTestCase2 {
    public TradeItemsTest(){
        super(Trade.class);}

    //tests the hasItem function for itemCollection. ItemCollections are all the items owned by a user
    public void testHasItem(){
        User user = new User("Carl");
        Item item = new Item();
        user.addItemToInventory(item);
        assertTrue(user.getInventory().contains(item));
    }

    //tests if an item is available for trade. Items are made unavailable when already involved in another trade
    public void testItemAvailable(){
        Item item = new Item();
        User user = new User("Carl");
        user.addItemToInventory(item);
        int index = user.getInventory().indexOf(item);
        assertTrue(user.getInventory().get(index).isAvailable());
    }

    //tests that the toBeTraded function updates the item so that it can no longer be involved in other trades
    public void testToBeTraded(){
        Item item = new Item();
        Item item2 = new Item();
        item.setItemName("Towel");
        item2.setItemName("Bazooka");
        Trade trade = new Trade("Penelope", "Towel", "Lisa", "Bazooka");
        trade.setTradePending(true);
        assertTrue(trade.isTradePending());
    }

    //tests the isNotified function which will be set to true when notifications are sent to users
    public void testIsNotified(){
        Trade trade = new Trade();
        assertFalse(trade.isTradePending());
    }

    //tests the notify function which changes the result of isNotified to true
    public void testNotify(){
        Trade trade = new Trade();
        trade.setTradePending(true);
        assertTrue(trade.isTradePending());
    }

    //tests the isAccepted function for offers, which returns true when both parties have accepted
    public void testIsAccepted(){
        Trade offer = new Trade();
        assertFalse(offer.isOwnerAcceptedTrade());


    }


    //tests the acceptOffer function which updates the system when the user accepts an offer
    public void testAcceptOffer(){
        Trade offer = new Trade();
        offer.setOwnerAcceptedTrade(true);
        assertTrue(offer.isOwnerAcceptedTrade());
    }

    //tests the viewHistory function which displays all previous and current offers/deals the user was involved in
    /*public void testviewHistory(){
        boolean worked = true;
        Trade offer = new Trade();
        TradeList offerCollection = new TradeList();
        offerCollection.add(offer);
        try{
            offerCollection.viewHistory();
        }catch (NoSuchMethodException e){
            worked = false;
        }
        assertTrue(worked);
    }*/

    //tests the deleteOffer function which is called when a user wishes to delete an offer they were working on
    public void testDeleteOffer(){
        Trade offer = new Trade();
        offer.requestDeclined();
        assertTrue(offer.isOwnerAcceptedTrade());
    }

    //tests the addItem function for offers, which would be invoked when creating or editing an offer
    /*public void testaddItem(){
        Trade offer = new Trade();
        Item item = new Item();
        offer.addItem(item);
        assertTrue(offer.size()==1);
    }

    //test the deleteItem function for offers, which would be used when creating or editing offers
    public void testdeleteItem(){
        Offer offer = new Offer();
        Item item = new Item();
        offer.addItem(item);
        assertTrue(offer.size()==1);
        offer.deleteItem(item);
        asserTrue(offer.size()==0);
    }*/

}
