package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class AddFriend extends ActionBarActivity {
    private User currentUser;
    private AddFriend activity = this;
    private UserController uc = new UserController();
    private ArrayList<User> userList= new ArrayList<User>();
    private static final String FILENAME = "userProfile.txt";
    private ArrayAdapter<User> adapter;
    private ArrayList resultList = new ArrayList<>();
    private String currentUserString;

    private ListView userSelectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Track currently logged in user
        userList = uc.loadUserFromFile(this, FILENAME, userList);
        Intent intent = getIntent();
        currentUserString = intent.getStringExtra("myID");
        currentUser = uc.findUserById(currentUserString, userList);

        //Shows currently logged in username in a toast. Debug
        //uc.makeInputStringToast(this, currentUserString);

        resultList = uc.convertUserToString(userList, resultList);

        userSelectionList = (ListView)findViewById(R.id.lv_add_friend);
        userSelectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = userList.get((int)id);
                uc.addUserAsFriend(activity, currentUser, selectedUser);
                uc.saveInFile(FILENAME, activity, userList);
                uc.passUserToActivity(UserFriends.class, activity, currentUserString);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ArrayAdapter<User>(activity, R.layout.list_item, resultList);
        userSelectionList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_main_viewing, menu);
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
        uc.passUserToActivity(UserInventory.class, activity, currentUserString);
    }
    public void userMyTradeSelected(MenuItem menu){

        uc.passUserToActivity(UserTrade.class, activity, currentUserString);
    }
    public void userMyFriendsSelected(MenuItem menu){
        uc.passUserToActivity(UserFriends.class, activity, currentUserString);
    }
    public void userMyProfileSelected(MenuItem menu){
        uc.passUserToActivity(UserProfile.class, activity, currentUserString);
    }
    public void userSearchSelected(MenuItem menu){
        uc.passUserToActivity(Search.class, activity, currentUserString);
    }
    public void userPreviousBrowseSelected(MenuItem menu){
        uc.passUserToActivity(PreviousBrowsedTrade.class, activity, currentUserString);
    }
    public void userLogoutSelected(MenuItem menu){
        uc.passUserToActivity(UserLogin.class, activity, currentUserString);
    }



}
