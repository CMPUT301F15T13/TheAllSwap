package com.example.qyu4.theallswap.Model;

import java.util.ArrayList;

/**
 * Trade class is an Model that handles all Trade structure.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class Trade {
    private String ownerId;
    private String borrowerId;
    private String ownerItem;
    private String borrowerItem;
    private boolean ownerAcceptedTrade = false;
    private boolean borrowerRetractedTrade = false;
    private boolean tradePending = true;

    public Trade() {

    }

    public Trade(String ownerId, String ownerItem, String borrowerId, String borrowerItem) {
        this.ownerId = ownerId;
        this.ownerItem = ownerItem;
        this.borrowerId = borrowerId;
        this.borrowerItem = borrowerItem;
    }

    /**
     * ownerAnswer is for determine if the request is accepted or declined.
     *  1. If 'owner' accept the trade, requestAccepted() is called for swap items.
     *  2. If 'owner' decline the trade, requestDeclined() is called for cancel the trade.
     * @param trade: the trade being accepted or declined.
     * @param acceptOrDecline: an answer from owner, showing if 'owner' accepts the trade or not.
     */
    private void ownerAnswer (Trade trade, boolean acceptOrDecline){
        if (acceptOrDecline){
            requestAccepted(trade);
        } else {
            requestDeclined();
        }
    }

    /**
     * 'owner' accepts the trade. Increment the successful-trades variable for both owner and
     * borrower, and notify the borrower that the trade was successful(?).
     * @param trade: the trade being accepted.
     */
    private void requestAccepted(Trade trade){
        //TODO: Accept the deal
    }

    /**
     * 'owner' cancels the deal. Set ownerAcceptedTrade and tradePending to false
     */
    private void requestDeclined(){
        //TODO: cancel the deal
    }

    public String getOwnerId() {
        return ownerId;
    }


    public String getBorrowerId() {
        return borrowerId;
    }

    public String getOwnerItem() {
        return ownerItem;
    }

    public String getBorrowerItem() {
        return borrowerItem;
    }

    public boolean isOwnerAcceptedTrade() {
        return ownerAcceptedTrade;
    }

    public void setOwnerAcceptedTrade(boolean ownerAcceptedTrade) {
        this.ownerAcceptedTrade = ownerAcceptedTrade;
    }

    public boolean isTradePending() {
        return tradePending;
    }

    public void setTradePending(boolean tradePending) {
        this.tradePending = tradePending;
    }

    public boolean isBorrowerRetractedTrade() {
        return borrowerRetractedTrade;
    }

    public void setBorrowerRetractedTrade(boolean borrowerRetractedTrade) {
        this.borrowerRetractedTrade = borrowerRetractedTrade;
    }
}
