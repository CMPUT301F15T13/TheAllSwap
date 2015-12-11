/*
 * Copyright 2015 Alexander Ozero, Qiang Yu, Eric Smith, Lixin Jin, Daniel Belanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    private boolean itemDownload = false;
    private boolean ImgDownloadable = false;


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

    public Bitmap getItemImgBitMap() { return itemImgBitMap; }

    public boolean getItemDownload() { return itemDownload; }

    public boolean getImgDownloadable(){return ImgDownloadable;}



    public Item(){}
    public Item(String itemName, int itemQuantity,
                String itemQuality, String itemCategory, boolean itemPrivacy,
                String ItemComments, String itemImgId, Bitmap setItemImgBitMap, boolean itemDownload) {

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

    public void setItemDownload(boolean ableDownload) { this.itemDownload = ableDownload;}

    public String getItemCategory() {
        return itemCategory;
    }

    public void setImgDownloadable(boolean avail){this.ImgDownloadable = avail;}

}
