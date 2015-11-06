package com.example.qyu4.theallswap.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
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
    private UserController uc= new UserController();
    private static final String FILENAME = "userProfile.txt";
    private ArrayList<User> userList = new ArrayList<User>();
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
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        /***************************************************
         TODO: add loading friends list method
         TODO: into arrayList user list
         *************************************************/
        userList = uc.loadUserFromFile(activity, FILENAME, userList);
        /***************************************************
         TODO: Done adding loading friends list method.
         *************************************************/

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_user_login){

            userName = (EditText) findViewById(R.id.user_name);
            String currentUserName = userName.getText().toString();

            //TODO: load file and save objects in arrayList.
            if(uc.checkingUserExist(currentUserName, userList)){
                Intent nextScreen = new Intent(activity, UserMainViewing.class);
                nextScreen.putExtra("myID", currentUserName);
                activity.startActivity(nextScreen);
            }else{
                uc.makeInvalidUserToast(activity);
            }

        }
        if(view.getId()==R.id.b_user_register){
            uc.classIntent(UserRegister.class, activity);
        }
    }
}
