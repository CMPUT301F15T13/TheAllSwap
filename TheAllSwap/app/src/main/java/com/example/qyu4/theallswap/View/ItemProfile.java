package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class ItemProfile extends ActionBarActivity {
    private UserController uc = new UserController();
    private InventoryController ic = new InventoryController();
    private ItemProfile activity = this;
    private ArrayList<User> userList = new ArrayList<User>();
    private static final String FILENAME = "userProfile.txt";
    private User currentUser = new User();
    private Item currentItem = new Item();

    private String itemName;
    private int itemQuantity;
    private String itemCategory;
    private String itemQuality;
    private String itemComment;
    private String itemPrivacy;

    private int itemId;
    private int currentUserId;

    private String currentItemString;
    private String currentUserString;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ItemName = (TextView) findViewById(R.id.assigned_name);
        ItemQuantity = (TextView) findViewById(R.id.assigned_quantity);
        ItemQuality = (TextView) findViewById(R.id.assigned_quality);
        ItemCategory = (TextView) findViewById(R.id.assigned_category);
        ItemPrivacy = (TextView) findViewById(R.id.assigned_privacy);
        ItemComments = (TextView) findViewById(R.id.assigned_comments);

        //Get current user and Item
        userList = uc.loadUserFromFile(activity, FILENAME, userList);
        Intent intent = getIntent();
        currentUserString = intent.getStringExtra("myID");
        currentUser = uc.findUserById(currentUserString, userList);

        currentItemString = intent.getStringExtra("id");
        itemId = uc.stringToInt(currentItemString);
        currentItem = currentUser.getUserInventory().get(itemId);

        Button editItemButton = (Button) findViewById(R.id.edit_item_button);
        editItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(activity,EditSingleItem.class);
                i.putExtra("id", currentItemString);
                i.putExtra("myID", currentUserString);
                activity.startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ItemName.clearComposingText();
        ItemName.append(currentItem.getItemName());
        ItemQuantity.clearComposingText();
        ItemQuantity.append(Integer.toString(currentItem.getItemQuantity()));
        ItemQuality.clearComposingText();
        ItemQuality.append(currentItem.getItemQuality());
        ItemCategory.clearComposingText();
        ItemCategory.append(currentItem.getItemCatgory());
        ItemPrivacy.clearComposingText();
        if(currentItem.isPrivate()) {
            ItemPrivacy.append("Is private");
        } else {
            ItemPrivacy.append("Is Public");
        }
        ItemComments.clearComposingText();
        ItemComments.append(currentItem.getItemComments());

    }
}
