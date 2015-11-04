package com.example.qyu4.theallswap.Model;

import java.util.ArrayList;

/**
 * Trade class is an Model that handles all Trade structure.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class Trade {
    private ArrayList<Item> ownerOfferItemList = new ArrayList<Item>();
    private ArrayList<Item> borrowRequestList = new ArrayList<Item>();
    private String ownwerId;
    private String borrowerId;
    private boolean ownerAcceptTrade = false;

    public Trade() {

    }

    public Trade(boolean ownerAcceptTrade, ArrayList<Item> ownerOfferItemList, ArrayList<Item> borrowRequestList, String ownwerId, String borrowerId) {
        this.ownerAcceptTrade = ownerAcceptTrade;
        this.ownerOfferItemList = ownerOfferItemList;
        this.borrowRequestList = borrowRequestList;
        this.ownwerId = ownwerId;
        this.borrowerId = borrowerId;
    }

    /**
     * ownerAnswer is for determine if the request is accepted or declined.
     *  1. If 'owner' accept the trade, requestAccepted() is called for swap items.
     *  2. If 'owner' decline the trade, requestDeclined() is called for cancel the trade.
     * @param ownerId: without owner id everyone can modify any trade
     * @param acceptOrDecline: an answer from owner, showing if 'owner' accept the trade or not.
     */
    private void ownerAnswer (String ownerId, boolean acceptOrDecline){
        if (acceptOrDecline){
            requestAccepted(ownerOfferItemList, ownwerId,
                            borrowRequestList,  borrowerId);
        }else {
            requestDeclined();
        }
    }

    /**
     * 'owner' accept the trade and swap the Items.
     * @param ownerOffer: owner's offer items.
     * @param ownerId: owner id.
     * @param borrowRequest: borrower's request items.
     * @param borrowerId: borrower id.
     */
    private void requestAccepted(ArrayList<Item> ownerOffer,
                            String ownerId, ArrayList<Item> borrowRequest, String borrowerId){
        //TODO: modify to swap the trading items.
    }

    /**
     * 'owner' cancel the deal. remove the request from request list.
     */
    private void requestDeclined(){
        //TODO: cancel the deal
    }
}
