package com.example.qyu4.theallswap.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;

public class UserRegister extends Activity implements View.OnClickListener{
    private UserRegister activity = this;
    private Button userRegister;
    private UserController uc = new UserController();
    private EditText userName = (EditText)findViewById(R.id.new_user_name);
    private EditText passwordOne= (EditText)findViewById(R.id.edit_text_firstpassword);
    private EditText passwordTwo= (EditText)findViewById(R.id.edit_text_password_two);
    private User newUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        userRegister = (Button) findViewById(R.id.b_new_user_submit);
        userRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_new_user_submit){
            String firstPassword = passwordOne.getText().toString();
            String secondPassword = passwordTwo.getText().toString();
            String userId = userName.getText().toString();
            boolean check= uc.registerNewUserPasswordCheck(firstPassword, secondPassword);
            if  (check){
                newUser.setUserId(userId);
                newUser.setUserPassword(firstPassword);

                classIntent(UserMainViewing.class);
            }else{
               uc.makeToastIn(activity);
            }
        }

    }
    public void classIntent(Class newClass){
        Intent openNewActivity = new Intent(activity, newClass);
        startActivity(openNewActivity);
    }

}
