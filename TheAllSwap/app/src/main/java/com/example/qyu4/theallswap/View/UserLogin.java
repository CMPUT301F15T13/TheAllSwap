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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qyu4.theallswap.Controller.TradeController;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;
/**
 * userLogin class is an activity that current user login page with his/her user name.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class UserLogin extends Activity implements View.OnClickListener{
    private UserLogin activity = this;
    private Button userLogin;
    private Button userRegister;
    private EditText userName;

    private UserController uc = new UserController();
    private UserList userList;
    private TradeController tc = new TradeController();
    private TradeList tradeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userLogin = (Button) findViewById(R.id.b_user_login);
        userRegister = (Button) findViewById(R.id.b_user_register);
        userRegister.setOnClickListener(this);
        userLogin.setOnClickListener(this);
        userList = UserList.getUserList();
        tradeList = TradeList.getTradeList();
        tc.loadTradesFromFile(activity, tradeList.getFilename(), tradeList);
        //uc.loadUsersFromFile(activity, userList.getFilename(), userList);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        userList = UserList.getUserList();

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_user_login){

            userName = (EditText) findViewById(R.id.user_name);
            String currentUserName = userName.getText().toString();
            uc.loadUsersFromFile(activity, userList.getFilename(), userList);

            //TODO: load file and save objects in arrayList.
            if(uc.checkingUserExist(currentUserName, userList)){
                uc.loadUsersFromFile(activity, userList.getFilename(), userList);
                userList.setCurrentUser(uc.findUserById(currentUserName, userList));
                uc.classIntent(UserMainView.class, activity);
            }else{
                uc.makeInvalidUserToast(activity);
            }

        }
        if(view.getId()==R.id.b_user_register){
            uc.classIntent(UserRegister.class, activity);
        }
    }
}
