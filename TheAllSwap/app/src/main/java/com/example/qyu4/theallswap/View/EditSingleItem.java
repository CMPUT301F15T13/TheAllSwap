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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class EditSingleItem extends ActionBarActivity implements View.OnClickListener{
    private UserController uc = new UserController();
    private EditSingleItem activity = this;
    private ArrayList<User> userList = new ArrayList<User>();
    private static final String FILENAME = "userProfile.txt";
    private User currentUser = new User();
    private Item currentItem = new Item();
    private TextView oldItemName;
    private TextView oldItemQuantity;
    private TextView oldItemQuality;
    private TextView oldItemCategory;

    private String itemName;
    private int itemQuantity;
    private String itemCategory;
    private String itemQuality;
    private String itemComment;
    private boolean itemPrivacy;

    private EditText newItemName;
    private EditText newItemQuantity;
    private EditText newItemQuality;
    private EditText newItemComments;

    private Button editItemButton;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private static final String[] m={"A", "B", "C", "D", "E", "F", "G", "H", "I", "j"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_item);
        oldItemName = (TextView) findViewById(R.id.old_item_name);
        oldItemQuantity = (TextView) findViewById(R.id.old_item_quantity);
        oldItemQuality = (TextView) findViewById(R.id.old_item_quality);
        oldItemCategory = (TextView) findViewById(R.id.old_item_category);
        newItemName = (EditText) findViewById(R.id.new_item_name);
        newItemQuantity = (EditText) findViewById(R.id.new_item_quantity);
        newItemQuality = (EditText) findViewById(R.id.new_item_quality);
        newItemComments=(EditText) findViewById(R.id.text_item_comment);
        /************************************************
         TODO: button click listener starts.
         ************************************************/
        editItemButton = (Button) findViewById(R.id.b_edit_item_submit);
        editItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //uc.makeInputStringToast(activity, itemCategory);

                itemName = newItemName.getText().toString();
                itemQuality=newItemQuality.getText().toString();
                itemQuantity=Integer.parseInt(newItemQuantity.getText().toString());
                itemComment=newItemComments.getText().toString();


                uc.makeInputStringToast(activity, itemComment);

                /**
                curentUser = uc.findCurrentUserObject("3", userList);
                Item newItem = ic.createNewItem(itemName, itemQuantity, itemQuality, itemCategory, itemPrivacy, itemComment);
                uc.makeInputStringToast(activity, itemQuality);
                ic.addItemToInventory(curentUser, newItem);
                uc.saveInFile(FILENAME, activity, userList);
                uc.classIntent(UserInventory.class, activity);
                 **/
            }
        });
        /************************************************
         TODO: button click listener done.
         ************************************************/
        /************************************************
         TODO: spinner adapter starts.
         ************************************************/
        spinner = (Spinner) findViewById(R.id.new_item_category_spinner);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        spinner.setVisibility(View.VISIBLE);
        /************************************************
         TODO: spinner adapter done.
         ************************************************/

    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        userList = uc.loadUserFromFile(activity, FILENAME, userList);
        /**
         * current user issue....
         */
        currentUser = userList.get(0);
        Intent intent = getIntent();
        /**
         * getting user id from UserFriends Activity
         */

        String id = intent.getStringExtra("id");
        int itemId =uc.stringToInt(id);
        currentItem = currentUser.getUserInventory().get(itemId);
        oldItemName.setText("(old item name: "+currentItem.getItemName()+")");
        oldItemQuality.setText("(old item quality: "+currentItem.getItemQuality()+")");
        oldItemCategory.setText("(old item category: "+currentItem.getItemCatgory()+")");
        oldItemQuantity.setText("(old item name: " + String.valueOf(currentItem.getItemQuantity())+")");

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
            //TODO: some stuff
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
