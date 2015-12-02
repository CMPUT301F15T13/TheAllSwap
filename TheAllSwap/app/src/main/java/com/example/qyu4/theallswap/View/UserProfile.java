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
/**
 * UserProfile class is an activity that showing current user's profile.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;
import com.example.qyu4.theallswap.Controller.UserController;

import java.util.ArrayList;

public class UserProfile extends ActionBarActivity {
    private UserProfile activity =this;
    private UserController uc = new UserController();
    private UserList userList;
    private User currentUser;
    private String userName;
    private String userEmail;
    private String userCity;
    private Button editProfileButton;
    private TextView userNameText;
    private TextView userEmailText;
    private TextView userCityText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        /**
         * retrieve current user's profiles
         */
        userName = currentUser.getUserId();
        userEmail= currentUser.getUserProfile().getUserContactInformation();
        userCity = currentUser.getUserProfile().getUserCity();

        /**
         * Edit button
         */
        editProfileButton = (Button)findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uc.classIntent(EditUserProfile.class, activity);
            }
        });
        /**
         * Text View part
         */
        userNameText = (TextView)findViewById(R.id.current_user_name);
        userNameText.setText(userName);
        userEmailText = (TextView)findViewById(R.id.current_user_email);
        userEmailText.setText(userEmail);
        userCityText = (TextView)findViewById(R.id.current_user_city);
        userCityText.setText(userCity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        userName = currentUser.getUserId();
        userEmail= currentUser.getUserProfile().getUserContactInformation();
        userCity = currentUser.getUserProfile().getUserCity();
        userNameText.setText(userName);
        userEmailText.setText(userEmail);
        userCityText.setText(userCity);
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