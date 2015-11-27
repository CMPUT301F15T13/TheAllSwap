package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Button;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

/**
 * EditSingleItem class is an activity that edit owner's single item.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class EditSingleItem extends ActionBarActivity implements View.OnClickListener{
    private UserController uc = new UserController();
    private InventoryController ic = new InventoryController();
    private EditSingleItem activity = this;
    private UserList userList;
    private User currentUser;

    private Item currentItem = new Item();

    private String itemName;
    private int itemQuantity;
    private String itemCategory;
    private String itemQuality;
    private String itemComment;
    private boolean itemPrivacy;
    private int itemId;
    private int currentUserId;
    private String currentUserString;

    private EditText ItemName;
    private EditText ItemQuantity;
    private EditText ItemQuality;
    private EditText ItemComments;
    private Spinner ItemCategory;
    private RadioGroup ItemPrivacy;

    private ArrayAdapter<String> categoryAdapter;
    private static final String[] m={"Weapons", "Gadgets", "Intel", "Identities", "Fine Clothing",
            "Vehicles", "Bug-out Supplies", "Contacts", "Secret", "Most Secret", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_item);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        ItemName = (EditText) findViewById(R.id.new_item_name);
        ItemQuantity = (EditText) findViewById(R.id.new_item_quantity);
        ItemQuality = (EditText) findViewById(R.id.new_item_quality);
        ItemComments =(EditText) findViewById(R.id.text_item_comment);
        ItemCategory = (Spinner) findViewById(R.id.new_item_category_spinner);
        ItemPrivacy = (RadioGroup) findViewById(R.id.radioGroup);

        Button editItemButton = (Button) findViewById(R.id.b_edit_item_submit);
        editItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentUserId = userList.indexOf(currentUser);

                itemName = ItemName.getText().toString();
                itemQuality = ItemQuality.getText().toString();
                itemQuantity = Integer.parseInt(ItemQuantity.getText().toString());
                itemComment = ItemComments.getText().toString();
                itemCategory = ItemCategory.getSelectedItem().toString();
                if(ItemPrivacy.getCheckedRadioButtonId() >= 0) {
                    RadioButton rb = (RadioButton) findViewById(ItemPrivacy.getCheckedRadioButtonId());
                    itemPrivacy = rb.getText().toString().equals("Yes");
                } else {
                    itemPrivacy = false;
                }

                Item newItem = ic.createNewItem(itemName, itemQuantity, itemQuality, itemCategory, itemPrivacy, itemComment);

                ic.editItem(currentUser, itemId, newItem);
                uc.saveInFile(userList.getFilename(), activity, userList);
                activity.finish();
            }
        });

        categoryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
        ItemCategory.setAdapter(categoryAdapter);
        ItemCategory.setOnItemSelectedListener(new SpinnerSelectedListener());
        ItemCategory.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        itemId = uc.stringToInt(id);
        currentItem = currentUser.getUserInventory().get(itemId);
        ItemName.setText(currentItem.getItemName());
        ItemQuality.setText(currentItem.getItemQuality());
        ItemQuantity.setText(String.valueOf(currentItem.getItemQuantity()));
        int pos = categoryAdapter.getPosition(currentItem.getItemCategory());
        ItemCategory.setSelection(pos);
        if(currentItem.isPrivate()){
            RadioButton rb = (RadioButton) ItemPrivacy.getChildAt(0);
            rb.setChecked(true);
        } else {
            RadioButton rb = (RadioButton) ItemPrivacy.getChildAt(1);
            rb.setChecked(true);
        }
        ItemComments.setText(currentItem.getItemComments());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_single_item, menu);
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

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            itemCategory = m[arg2];

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        /**
         * Check which radio button was clicked
         */
        switch(view.getId()) {
            case R.id.b_new_item_private:
                if (checked)
                    itemPrivacy = true;
                break;
            case R.id.b_new_item_not_private:
                if (checked)
                    itemPrivacy = false;
                break;

        }
    }
}
