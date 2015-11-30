package com.example.qyu4.theallswap.View;

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

import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;
import com.example.qyu4.theallswap.Controller.UserController;

import java.util.ArrayList;
/**
 * UserFriends class is an activity that showing all the current user's friends.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class UserFriends extends ActionBarActivity {
    private UserFriends activity = this;
    private UserController uc = new UserController();

    private UserList userList;
    private User currentUser;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> resultList = new ArrayList<>();
    private ListView friendList;
    private Button addFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_friends);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        resultList = currentUser.getFriendsList();

        friendList = (ListView)findViewById(R.id.lv_user_friends);

        //Set adapter
        adapter = new ArrayAdapter<String>(activity, R.layout.list_item, (ArrayList) resultList);
        friendList.setAdapter(adapter);

        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                uc.passValueToActivity(SingleFriendProfile.class, activity, position);
            }
        });

        friendList.setLongClickable(true);
        friendList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                //Long Click removes the user from friends list
                String selectedUser = friendList.getSelectedItem().toString();
                uc.removeUserAsFriend(activity, currentUser, selectedUser);

                resultList = currentUser.getFriendsList();
                adapter.notifyDataSetChanged();
                uc.saveInFile(userList.getFilename(), activity, userList);
                return true;
            }
        });

        addFriend = (Button)findViewById(R.id.btn_add_friend);
        addFriend.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                uc.classIntent(AddFriend.class, activity);
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        resultList = currentUser.getFriendsList();
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
