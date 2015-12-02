/*
 * Copyright 2015 Alexander Ozero, Qiang Yu, Eric Smith, Lixin Jin, Daniel Belanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.qyu4.theallswap.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;
/**
 * SingleFriendProfile class is an activity that current user can check one of his friend's profile.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class SingleFriendProfile extends ActionBarActivity {
    private SingleFriendProfile activity = this;
    private UserController uc= new UserController();
    private InventoryController ic = new InventoryController();
    private UserList userList;
    private User currentUser;
    private String singleUser;

    private TextView userName;
    private TextView userEmail;
    private TextView userCity;

    private String tvUserName;
    private String tvUserEmail;
    private String tvUserCity;

    private ArrayList<String> itemArray = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_friend_profile);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        int userId = uc.stringToInt(id);
        singleUser = currentUser.getFriendsList().get(userId);

        userName = (TextView) findViewById(R.id.single_user_name);
        userEmail = (TextView) findViewById(R.id.single_user_email);
        userCity = (TextView) findViewById(R.id.single_user_city);

        ArrayList<Item> items = ic.showNonPrivateItems(userList.getUserFromId(singleUser));
        for(Item i : items) {
            itemArray.add(i.getItemName());
        }

        itemList = (ListView)findViewById(R.id.single_inventory);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, (ArrayList) itemArray);
        itemList.setAdapter(adapter);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = currentUser.getFriendsList().indexOf(singleUser);
                uc.passValueToActivity(CreateTrade.class, activity, index);
                //activity.finish();
            }
        });

        Button searchInvButton = (Button) findViewById(R.id.btn_search_inv);
        searchInvButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int index = currentUser.getFriendsList().indexOf(singleUser);
                uc.passValueToActivity(SingleUserFullInventory.class, activity, index);
            }
        });

        Button removeFriendButton = (Button) findViewById(R.id.btn_remove_as_friend);
        removeFriendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uc.removeUserAsFriend(activity, currentUser, singleUser);
                activity.finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        User selectedUser = userList.getUserFromId(singleUser);
        userName.setText(singleUser);
        userEmail.setText(selectedUser.getUserProfile().getUserContactInformation());
        userCity.setText(selectedUser.getUserProfile().getUserCity());

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
