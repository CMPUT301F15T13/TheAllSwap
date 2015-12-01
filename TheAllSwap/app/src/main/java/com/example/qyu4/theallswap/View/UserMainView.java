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

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.qyu4.theallswap.R;
import com.example.qyu4.theallswap.Controller.UserController;

/**
 * UserMainView class is an activity that Navigates current user's requirement.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class UserMainView extends ActionBarActivity implements View.OnClickListener{
    private UserMainView activity = this;
    private UserController uc = new UserController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_viewing);

        Button myInventoryButton = (Button)findViewById(R.id.b_my_inventory);
        Button myTradeButton = (Button)findViewById(R.id.b_my_trade);
        Button myFriendsButton = (Button)findViewById(R.id.b_my_friends);
        Button myProfileButton = (Button)findViewById(R.id.b_my_profile);
        Button myLogOutButton = (Button)findViewById(R.id.b_log_out);

        myInventoryButton.setOnClickListener(this);
        myTradeButton.setOnClickListener(this);
        myFriendsButton.setOnClickListener(this);
        myProfileButton.setOnClickListener(this);
        myLogOutButton.setOnClickListener(this);

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
        uc.classIntent(UserFriends.class, activity);
    }

    public void userPreviousBrowseSelected(MenuItem menu){
        uc.classIntent(PreviousBrowsedTrade.class, activity);
    }
    public void userLogoutSelected(MenuItem menu){
        activity.finish();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_my_inventory){
            uc.classIntent(UserInventory.class, activity);
        }
        else if(view.getId()==R.id.b_my_trade){
            uc.classIntent(UserTradesHub.class, activity);
        }
        else if(view.getId()==R.id.b_my_friends){
            uc.classIntent(UserFriends.class, activity);
        }
        else if(view.getId()==R.id.b_my_profile){
            uc.classIntent(UserProfile.class, activity);
        }
        else if(view.getId()==R.id.b_log_out){
            activity.finish();
        }
    }


}