package com.example.qyu4.theallswap.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class SingleFriendProfile extends ActionBarActivity {

    private SingleFriendProfile activity = this;
    private UserController uc= new UserController();
    private static final String FILENAME = "userProfile.txt";
    private ArrayList<User> userList =new ArrayList<User>();
    private User singleUser = new User();

    private TextView userName;
    private TextView userEmail;
    private TextView userCity;

    private String tvUserName;
    private String tvUserEmail;
    private String tvUserCity;

    private ArrayList itemArray = new ArrayList();
    private ArrayAdapter<User> adapter;
    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_friend_profile);
        itemList = (ListView)findViewById(R.id.single_inventory);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: After I fix this part click on the item current user can start a trade...
                uc.makeInputStringToast(activity, "You click on the TREASURE!! After I fix this part click on the " +
                        "item current user can start a trade...");

            }


        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Intent intent = getIntent();
        /**
         * getting user id from UserFriends Activity
         */
        String id = intent.getStringExtra("id");

        userList = uc.loadUserFromFile(activity, FILENAME, userList);
        int userId =uc.stringToInt(id);
        singleUser = userList.get(userId);
        userName = (TextView) findViewById(R.id.single_user_name);
        userEmail = (TextView) findViewById(R.id.single_user_email);
        userCity = (TextView) findViewById(R.id.single_user_city);
        userName.setText(singleUser.getUserId());
        userEmail.setText(singleUser.getUserProfile().getUserContactInformation());
        userCity.setText(singleUser.getUserProfile().getUserCity());

        /***************************************************
         TODO: add loading item list method.
         *************************************************/
        itemArray = uc.convertItemToString(singleUser, itemArray);
        /***************************************************
         TODO: add loading item list method.
         *************************************************/

        adapter = new ArrayAdapter<User>(this, R.layout.list_item, itemArray);
        itemList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_friend_profile, menu);
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

    public void userMyInventorySelected(MenuItem menu){
        uc.classIntent(UserInventory.class, activity);
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
