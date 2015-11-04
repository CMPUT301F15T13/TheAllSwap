package com.example.qyu4.theallswap.View;

import android.app.Activity;
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
    private Spinner spinner;
    private Button newItemSubmitButton;
    private InventoryController ic = new InventoryController();
    private UserController uc = new UserController();
    private User curentUser = new User();
    private ArrayList<User>userList = new ArrayList<User>();
    private ArrayAdapter<String> adapter;
    private AddInventoryItem activity = this;
    private RadioButton isPrivateButton;
    private RadioButton isNotPrivateButton;
    private EditText inputItemName;
    private EditText inputItemQuality;
    private EditText inputItemQuantity;
    private EditText inputItemComment;
    private String itemName;
    private String itemQuality;
    private int itemQuantity;
    private String itemComment;
    private String itemCategory;
    private boolean itemPrivacy;
    private String spinerItemCategory;
    private String checklistenerScope;

    private static final String[] m={"A", "B", "C", "D", "E", "F", "G", "H", "I", "j"};
    private static final String FILENAME = "userProfile.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_item);
        inputItemName = (EditText)findViewById(R.id.new_item_name);
        inputItemQuality=(EditText)findViewById(R.id.new_item_quality);
        inputItemComment=(EditText)findViewById(R.id.text_item_comment);
        inputItemQuantity=(EditText)findViewById(R.id.new_item_quantity);

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

        /************************************************
         TODO: submit button starts.
         ************************************************/
        newItemSubmitButton = (Button) findViewById(R.id.b_edit_item_submit);
        newItemSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                itemName = inputItemName.getText().toString();
                itemQuality=inputItemQuality.getText().toString();
                itemQuantity=Integer.parseInt(inputItemQuantity.getText().toString());
                itemComment=inputItemComment.getText().toString();
                curentUser = uc.findCurrentUserObject("3", userList);
                Item newItem = ic.createNewItem(itemName, itemQuantity, itemQuality, itemCategory, itemPrivacy, itemComment);
                uc.makeInputStringToast(activity, itemQuality);
                ic.addItemToInventory(curentUser, newItem);
                uc.saveInFile(FILENAME, activity, userList);
                uc.classIntent(UserInventory.class, activity);
            }
        });

        /************************************************
         TODO: submit button done.
         ************************************************/

        /************************************************
         TODO: radio button starts.
         ************************************************/
        isPrivateButton = (RadioButton) findViewById(R.id.b_new_item_private);
        isNotPrivateButton = (RadioButton) findViewById(R.id.b_new_item_not_private);
        isPrivateButton.setOnClickListener(this);
        isNotPrivateButton.setOnClickListener(this);
        /************************************************
         TODO: radio button done.

        inputItemName = (EditText)findViewById(R.id.new_item_name);
        inputItemQuality=(EditText)findViewById(R.id.new_item_quality);
        inputItemComment=(EditText)findViewById(R.id.text_item_comment);
        inputItemQuantity=(EditText)findViewById(R.id.new_item_quantity);

        itemQuality=inputItemQuality.getText().toString();
        itemQuantity=Integer.parseInt(inputItemQuantity.getText().toString());
        itemComment=inputItemComment.getText().toString();
        ************************************************/

    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        userList = uc.loadUserFromFile(activity, FILENAME, userList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_inventory_item, menu);
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
    class SpinnerSelectedListener implements OnItemSelectedListener {

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
