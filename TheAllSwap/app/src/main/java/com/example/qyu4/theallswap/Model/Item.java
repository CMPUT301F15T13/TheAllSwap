package com.example.qyu4.theallswap.Model;

/**
 * Created by qyu4 on 10/20/15.
 */

public class Item {
    private String itemName;
    private int itemQuantity;
    private String itemQuality;
    private String itemCatgory;
    private boolean itemPrivacy;
    private String itemComments;

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getItemQuality() {
        return itemQuality;
    }

    public boolean isItemPrivacy() {
        return itemPrivacy;
    }

    public String getItemComments() {
        return itemComments;
    }



    public Item(){}
    public Item(String itemName, int itemQuantity,
                String itemQuality, String itemCategory, boolean itemPrivacy,
                String ItemComments) {

        this.setItemName(itemName);
        this.setItemQuality(itemQuality);
        this.setItemQuantity(itemQuantity);
        this.setItemCategory(itemCategory);
        this.setItemPrivacy(itemPrivacy);
        this.setItemComments(ItemComments);
    }



    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemQuality(String itemQuality) {
        this.itemQuality = itemQuality;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCatgory = itemCategory;
    }

    public void setItemPrivacy(boolean itemPrivacy) {
        this.itemPrivacy = itemPrivacy;
    }
    public void setItemComments(String itemComments) {
        this.itemComments = itemComments;
    }
    public String getItemCatgory() {
        return itemCatgory;
    }

}
