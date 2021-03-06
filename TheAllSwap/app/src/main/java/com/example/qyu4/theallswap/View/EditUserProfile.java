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
import android.widget.EditText;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Profile;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

public class EditUserProfile extends ActionBarActivity {
    private EditUserProfile activity = this;
    private Button submitButton;
    private EditText userNameEdit;
    private EditText userEmailEdit;
    private EditText userCityEdit;
    private String userName;
    private String userEmail;
    private String userCity;
    private UserList userList;
    private User currentUser;
    private int currentUserIndex;
    private Profile newUserProfile = new Profile();
    private UserController uc = new UserController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        /**
         * user new profile
         */
        userNameEdit = (EditText)findViewById(R.id.new_user_name);
        userEmailEdit = (EditText)findViewById(R.id.new_email);
        userCityEdit = (EditText)findViewById(R.id.new_city);

        /**
         * Submit button
         */
        submitButton=(Button)findViewById(R.id.b_edit_profile_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentUserIndex = uc.findUserIndexById(currentUser.getUserId(), userList);
                userName = userNameEdit.getText().toString();
                userEmail= userEmailEdit.getText().toString();
                userCity= userCityEdit.getText().toString();
                /**
                 * edit currentUser
                 */
                newUserProfile.setUserContactInformation(userEmail);
                newUserProfile.setUserCity(userCity);
                currentUser.setUserProfile(newUserProfile);
                currentUser.setUserId(userName);
                /**
                 * edit userList
                 */
                userList.set(currentUserIndex, currentUser);
                /**
                 * save changes
                 */
                uc.saveInFile(userList.getFilename(), activity, userList);
                activity.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        userNameEdit.setText(currentUser.getUserId());
        userEmailEdit.setText(currentUser.getUserProfile().getUserContactInformation());
        userCityEdit.setText(currentUser.getUserProfile().getUserCity());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user_profile, menu);
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
}
