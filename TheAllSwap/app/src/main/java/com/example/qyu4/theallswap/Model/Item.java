package com.example.qyu4.theallswap.Model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Item class is an Model that handles all Item structure.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */

public class Item {
    private String itemName;
    private int itemQuantity;
    private String itemQuality;
    private String itemCategory;
    private boolean itemPrivacy;
    private boolean availability = true;
    private String itemComments;
    private String itemImgId;
    private Bitmap itemImgBitMap;


    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getItemQuality() {
        return itemQuality;
    }

    public boolean isPrivate() {
        return itemPrivacy;
    }

    public boolean isAvailable() { return availability;}

    public String getItemComments() {
        return itemComments;
    }

    public String getItemImgId(){
        return itemImgId;
    }



    public Item(){}
    public Item(String itemName, int itemQuantity,
                String itemQuality, String itemCategory, boolean itemPrivacy,
                String ItemComments, String itemImgId, Bitmap setItemImgBitMap) {

        this.setItemName(itemName);
        this.setItemQuality(itemQuality);
        this.setItemQuantity(itemQuantity);
        this.setItemCategory(itemCategory);
        this.setItemPrivacy(itemPrivacy);
        this.setItemComments(ItemComments);
        this.setItemImgId(itemImgId);
        this.setItemImgBitMap(itemImgBitMap);
        this.availability = true;
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
        this.itemCategory = itemCategory;
    }

    public void setItemPrivacy(boolean itemPrivacy) {
        this.itemPrivacy = itemPrivacy;
    }
    public void setItemComments(String itemComments) {
        this.itemComments = itemComments;
    }

    public void setAvailability(boolean avail) {
        availability = avail;
    }

    public void setItemImgId(String addedImage){
        this.itemImgId = addedImage;
    }

    public void setItemImgBitMap(Bitmap addedBitMap) { this.itemImgBitMap = addedBitMap;}

    public String getItemCategory() {
        return itemCategory;
    }

    public boolean checkImageSize() {
        // Do Something
        return true;
    }

    public void disableImageDownload() {
        // Do Something
    }

    public Integer downloadImage(Integer image){
        return image;
        // Do Something
    }

}
