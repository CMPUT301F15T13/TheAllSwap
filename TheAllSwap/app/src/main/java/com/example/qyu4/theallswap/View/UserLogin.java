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
        uc.loadUsersFromFile(activity, userList.getFilename(), userList);
        tradeList = TradeList.getTradeList();
        tc.loadTradesFromFile(activity, tradeList.getFilename(), tradeList);
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

            //TODO: load file and save objects in arrayList.
            if(uc.checkingUserExist(currentUserName, userList)){
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
