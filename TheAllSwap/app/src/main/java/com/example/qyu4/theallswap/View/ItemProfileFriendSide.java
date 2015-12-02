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
package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ItemProfileFriendSide extends ActionBarActivity {
    private UserController uc = new UserController();
    private InventoryController ic = new InventoryController();
    private ItemProfileFriendSide activity = this;
    private UserList userList;
    private User currentUser;
    private Item currentItem = new Item();

    private int itemId;

    private String currentItemString;

    //The TextViews that show the actual item's value
    private TextView ItemName;
    private TextView ItemQuantity;
    private TextView ItemQuality;
    private TextView ItemComments;
    private TextView ItemCategory;
    private TextView ItemPrivacy;
    private ImageView imgView;

    private Boolean imgAble = false;

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    Bitmap itemBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_profile_friendside);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ItemName = (TextView) findViewById(R.id.current_user_name);
        ItemQuantity = (TextView) findViewById(R.id.current_user_email);
        ItemQuality = (TextView) findViewById(R.id.current_user_city);
        ItemCategory = (TextView) findViewById(R.id.assigned_category);
        ItemPrivacy = (TextView) findViewById(R.id.assigned_privacy);
        ItemComments = (TextView) findViewById(R.id.assigned_comments);
        imgView = (ImageView) findViewById(R.id.imgView);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        Intent intent = getIntent();
        currentItemString = intent.getStringExtra("id");
        itemId = uc.stringToInt(currentItemString);
        currentItem = currentUser.getUserInventory().get(itemId);

        Button downloadImageButton = (Button)findViewById(R.id.download_picture_button);
        downloadImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downloadImage();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ItemName.setText(currentItem.getItemName());
        ItemQuantity.setText(String.format("%d", currentItem.getItemQuantity()));
        ItemQuality.setText(currentItem.getItemQuality());
        ItemCategory.setText(currentItem.getItemCategory());

        if (imgAble == false) {
            imgView.setVisibility(View.INVISIBLE);
        }

        if (currentItem.isPrivate()) {
            String s = "Is private";
            ItemPrivacy.setText(s);
        } else {
            String s = "Is Public";
            ItemPrivacy.setText(s);
        }
        ItemComments.setText(currentItem.getItemComments());

        if (!"".equals(imgDecodableString)) {
            //Toast.makeText(this, imgDecodableString, Toast.LENGTH_SHORT).show();
            //imgDecodableString = currentItem.getItemImgId();
            itemBitmap = currentItem.getItemImgBitMap();


            //Toast.makeText(this, itemBitmap.toString(), Toast.LENGTH_SHORT).show();
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageBitmap(itemBitmap);
            //imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_profile_friendside, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void userDisplayImage(MenuItem menu){
        imgAble = true;
        displayImage();
    }

    public void displayImage(){
        //Toast.makeText(this, imgDecodableString, Toast.LENGTH_SHORT).show();
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setVisibility(View.VISIBLE);
    }

    public void downloadImage() {
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        File file = new File(path, currentItem.getItemName()); // the File to save to
        try {
            fOut = new FileOutputStream(file);

            Bitmap pictureBitmap = currentItem.getItemImgBitMap(); // obtaining the Bitmap
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush();
            fOut.close(); // do not forget to close the stream

            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        } catch (Exception e) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
    }

}
