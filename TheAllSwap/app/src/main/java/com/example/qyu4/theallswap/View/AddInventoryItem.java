package com.example.qyu4.theallswap.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
/**
 * AddInventoryItem class is an activity that add an item to owner's inventory.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class AddInventoryItem extends Activity implements View.OnClickListener {
    private UserController uc = new UserController();
    private InventoryController ic = new InventoryController();
    private AddInventoryItem activity = this;
    private ArrayList<User> userList = new ArrayList<User>();
    private static final String FILENAME = "userProfile.txt";
    private User currentUser = new User();
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
    private static final String[] m={"A", "B", "C", "D", "E", "F", "G", "H", "I", "j"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_item);

        ItemName = (EditText) findViewById(R.id.new_item_name);
        ItemQuantity = (EditText) findViewById(R.id.new_item_quantity);
        ItemQuality = (EditText) findViewById(R.id.new_item_quality);
        ItemCategory = (Spinner) findViewById(R.id.new_item_category_spinner);
        ItemPrivacy = (RadioGroup) findViewById(R.id.radioGroup);
        ItemComments =(EditText) findViewById(R.id.text_item_comment);

        //Get current user
        userList = uc.loadUserFromFile(activity, FILENAME, userList);
        Intent intent = getIntent();
        currentUserString = intent.getStringExtra("myID");
        currentUser = uc.findUserById(currentUserString, userList);

        Button editItemButton = (Button) findViewById(R.id.b_edit_item_submit);
        editItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                itemName = ItemName.getText().toString();
                itemQuality = ItemQuality.getText().toString();
                itemQuantity = Integer.parseInt(ItemQuantity.getText().toString());
                itemCategory = ItemCategory.getSelectedItem().toString();
                if(ItemPrivacy.getCheckedRadioButtonId() >0) {
                    RadioButton rb = (RadioButton) findViewById(ItemPrivacy.getCheckedRadioButtonId());
                    itemPrivacy = rb.isChecked();
                } else {
                    itemPrivacy = false;
                }
                itemComment = ItemComments.getText().toString();

                Item newItem = ic.createNewItem(itemName, itemQuantity, itemQuality, itemCategory, itemPrivacy, itemComment);
                ic.addItemToInventory(currentUser, newItem);
                uc.saveInFile(FILENAME, activity, userList);
                uc.passUserToActivity(UserInventory.class, activity, currentUserString);
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





