package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_profile);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ItemName = (TextView) findViewById(R.id.assigned_name);
        ItemQuantity = (TextView) findViewById(R.id.assigned_quantity);
        ItemQuality = (TextView) findViewById(R.id.assigned_quality);
        ItemCategory = (TextView) findViewById(R.id.assigned_category);
        ItemPrivacy = (TextView) findViewById(R.id.assigned_privacy);
        ItemComments = (TextView) findViewById(R.id.assigned_comments);

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        ItemName.setText(currentItem.getItemName());
        ItemQuantity.setText(String.format("%d", currentItem.getItemQuantity()));
        ItemQuality.setText(currentItem.getItemQuality());
        ItemCategory.setText(currentItem.getItemCategory());
        if(currentItem.isPrivate()) {
            String s = "Is private";
            ItemPrivacy.setText(s);
        } else {
            String s = "Is Public";
            ItemPrivacy.setText(s);
        }
        ItemComments.setText(currentItem.getItemComments());

    }
}
