package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
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

public class ItemProfile extends ActionBarActivity {
    private UserController uc = new UserController();
    private InventoryController ic = new InventoryController();
    private ItemProfile activity = this;
    private UserList userList;
    private User currentUser;
    private Item currentItem = new Item();

    private String itemName;
    private int itemQuantity;
    private String itemCategory;
    private String itemQuality;
    private String itemComment;
    private String itemPrivacy;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_profile);
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

        Button editItemButton = (Button) findViewById(R.id.edit_profile_button);
        editItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uc.passValueToActivity(EditSingleItem.class, activity, itemId);
                activity.finish();
            }
        });

        Button deleteItemButton = (Button) findViewById(R.id.remove_item_button);
        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ic.removeItemFromInventory(currentUser, currentItem);
                uc.saveInFile(userList.getFilename(), activity, userList);
                activity.finish();
            }
        });

        Button uploadImageButton = (Button)findViewById(R.id.load_picture_button);
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadImageFromGallery(v);
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
            imgDecodableString = currentItem.getItemImgId();
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_profile, menu);
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
    public void userDeleteImage(MenuItem menu){
        deleteImage();
    }

    public void displayImage(){
        //Toast.makeText(this, imgDecodableString, Toast.LENGTH_SHORT).show();
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setVisibility(View.VISIBLE);
    }

    public void deleteImage(){

        currentItem.setItemImgId("");
        ic.editItem(currentUser, itemId, currentItem);
        uc.saveInFile(userList.getFilename(), activity, userList);

        imgDecodableString = currentItem.getItemImgId();
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
    }

    public void loadImageFromGallery(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,
                        null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);







                currentItem.setItemImgId(imgDecodableString);
                ic.editItem(currentUser, itemId, currentItem);
                uc.saveInFile(userList.getFilename(), activity, userList);








                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            } else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
