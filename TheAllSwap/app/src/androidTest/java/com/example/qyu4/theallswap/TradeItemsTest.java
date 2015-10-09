package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by debelang on 10/9/15.
 */
public class TradeItemsTest extends ActivityInstrumentationTestCase2 {
    public TradeItemsTest(){super(UserTradingActivity.class);}

    //tests the hasItem function for itemCollection. ItemCollections are all the items owned by a user
    public void testhasItem(){
        Item item = new Item();
        ItemCollection itemCollection = new ItemCollection();
        itemCollection.addItem(item);
        assertTrue(itemCollection.hasItem(item));
    }

    //tests if an item is available for trade. Items are made unavailable when already involved in another trade
    public void testitemAvailable(){
        Item item = new Item();
        assertTrue(item.itemAvailable);
    }

    //tests that the toBeTraded function updates the item so that it can no longer be involved in other trades
    public void testtoBeTraded(){
        Item item = new Item();
        item.toBeTraded();
        assertFalse(item.itemAvailable);

    }

    //tests the isNotified function which will be set to true when notifications are sent to users
    public void testisNotified(){
        User user = new User();
        assertFalse(user.isNotified());
    }

    //tests the notify function which changes the result of isNotified to true
    public void testnotify(){
        User user = new User();
        user.notify();
        assertTrue(user.isNotified());
    }

    //tests the isAccepted function for offers, which returns true when both parties have accepted
    public void testisAccepted(){
        Offer offer = new Offer();
        assertFalse(offer.isAccepted());
    }


    //tests the acceptOffer function which updates the system when the user accepts an offer
    public void testacceptOffer(){
        Offer offer = new Offer();
        offer.acceptOffer();
        assertTrue(offer.isAccepted());
    }

    //tests the viewHistory function which displays all previous and current offers/deals the user was involved in
    public void testviewHistory(){
        boolean worked = true;
        Offer offer = new Offer();
        OfferCollection offerCollection = new OfferCollection();
        offerCollection.add(offer);
        try{
            offerCollection.viewHistory();
        }catch (NoSuchMethodException e){
            worked = false;
        }
        assertTrue(worked);
    }

    //tests the deleteOffer function which is called when a user wishes to delete an offer they were working on
    public void testdeleteOffer(){
        Offer offer = new Offer();
        offer.delete();
        assertTrue(offer==null);
    }

    //tests the addItem function for offers, which would be invoked when creating or editing an offer
    public void testaddItem(){
        Offer offer = new Offer();
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
    }

}
