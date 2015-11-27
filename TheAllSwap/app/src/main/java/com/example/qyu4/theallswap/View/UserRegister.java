package com.example.qyu4.theallswap.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Profile;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

/**
 * User register activity take user input and check the valid id, passwords
 * email, and city by calling controller method in UserController class.
 * And create new User Class in the file for now.
 *
 * @author qyu4, egsmith, lixin1, ozero, debelang
 */
public class UserRegister extends Activity implements View.OnClickListener{
    private UserRegister activity = this;
    private UserController uc = new UserController();

    private UserList userList;

    private User newUser = new User();
    private Profile newProfile= new Profile();
    private Button userRegister;
    private EditText userName;
    private EditText inputEmail;
    private EditText inputCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        userList = UserList.getUserList();

        userRegister =(Button) findViewById(R.id.b_edit_item_submit);
        userRegister.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_edit_item_submit){

            inputEmail=(EditText)findViewById(R.id.new_item_quantity);
            inputCity =(EditText)findViewById(R.id.new_item_quality);
            userName = (EditText)findViewById(R.id.new_item_name);

            String newEmail = inputEmail.getText().toString();
            String newCity  = inputCity.getText().toString();
            String userId = userName.getText().toString();
            newUser.setUserId(userId);
            newProfile.setUserCity(newCity);
            newProfile.setUserContactInformation(newEmail);
            newUser.setUserProfile(newProfile);
            userList.add(newUser);
            uc.saveInFile(userList.getFilename(), activity, userList);
            uc.makeInputStringToast(this, "Registered username: " + userId);
            activity.finish();
        }
    }

}
