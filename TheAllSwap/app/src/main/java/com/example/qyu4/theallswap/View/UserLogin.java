package com.example.qyu4.theallswap.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.R;

public class UserLogin extends Activity implements View.OnClickListener{
    private UserLogin activity = this;
    private Button userLogin;
    private Button userRegister;
    private EditText userName;
    private EditText userPassword;
    private UserController uc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userLogin = (Button) findViewById(R.id.b_user_login);
        userRegister = (Button) findViewById(R.id.b_user_register);
        userRegister.setOnClickListener(this);
        userLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_user_login){
            userName = (EditText) findViewById(R.id.user_name);
            String currentUserName = userName.getText().toString();
            /**
             * User does not need password to login.
             */
            //userPassword=(EditText)findViewById(R.id.user_password);
            //TODO: load file and save objects in arrayList.
            if(uc.checkingUserExist(currentUserName)){
                uc.classIntent(UserMainViewing.class, activity);
            }
            
        }
        if(view.getId()==R.id.b_user_register){
            uc.classIntent(UserRegister.class, activity);
        }
    }
}
