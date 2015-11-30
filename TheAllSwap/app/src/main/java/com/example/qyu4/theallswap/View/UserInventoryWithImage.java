package com.example.qyu4.theallswap.View;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class UserInventoryWithImage extends ListActivity {
    private UserInventoryWithImage activity = this;
    private UserController uc = new UserController();

    private UserList userList;
    private User currentUser;

    private ArrayAdapter<String> adapter;
    private ListView itemList;
    private ArrayList<String> resultList = new ArrayList<>();


    private String[] itemname = {
            "111",
            "222",
            "333"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inventory_with_image);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        resultList = uc.convertItemToString(currentUser, resultList);

        adapter = new ArrayAdapter<String>(activity, R.layout.list_item_image, R.id.itemName, resultList);

    }

    @Override
    protected void onStart() {
        super.onStart();
        resultList = uc.convertItemToString(currentUser, resultList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_inventory_with_image, menu);
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
    public void userDisableImage(MenuItem menu){
        uc.classIntent(UserInventory.class, activity);
    }
    public void userAddItemToInventory(MenuItem menu){
        uc.classIntent(AddInventoryItem.class, activity);
    }
    public void userMyTradeSelected(MenuItem menu){

        uc.classIntent(UserTrade.class, activity);
    }
    public void userMyFriendsSelected(MenuItem menu){
        uc.classIntent(UserFriends.class, activity);
    }
    public void userMyProfileSelected(MenuItem menu){
        uc.classIntent(UserProfile.class, activity);
    }
    public void userSearchSelected(MenuItem menu){
        uc.classIntent(Search.class, activity);
    }
    public void userPreviousBrowseSelected(MenuItem menu){
        uc.classIntent(PreviousBrowsedTrade.class, activity);
    }
    public void userLogoutSelected(MenuItem menu){
        uc.classIntent(UserLogin.class, activity);
    }
}
