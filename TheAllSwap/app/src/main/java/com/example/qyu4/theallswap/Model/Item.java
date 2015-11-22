package com.example.qyu4.theallswap.Model;

import com.example.qyu4.theallswap.View.Search;

import java.lang.reflect.Array;
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
    private String itemCatgory;
    private boolean itemPrivacy;
    private String itemComments;
    private ArrayList<Integer> itemImgId;


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

    public ArrayList<Integer> getItemImgId(){
        return itemImgId;
    }



    public Item(){}
    public Item(String itemName, int itemQuantity,
                String itemQuality, String itemCategory, boolean itemPrivacy,
                String ItemComments, ArrayList<Integer> itemImgId) {

        this.setItemName(itemName);
        this.setItemQuality(itemQuality);
        this.setItemQuantity(itemQuantity);
        this.setItemCategory(itemCategory);
        this.setItemPrivacy(itemPrivacy);
        this.setItemComments(ItemComments);
        this.setItemImgId(itemImgId);
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
    public void setItemImgId(ArrayList<Integer> itemImgId) {
        this.itemImgId = itemImgId;
    }

    public void addImage(int addedImage){
        this.itemImgId.add(addedImage);
    }
    public void removeImage(int removedImage) {
        if (this.itemImgId.contains(removedImage)) {
            this.itemImgId.remove(removedImage);
        }
    }

    public String getItemCatgory() {
        return itemCatgory;
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
