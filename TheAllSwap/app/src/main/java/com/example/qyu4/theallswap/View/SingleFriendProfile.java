package com.example.qyu4.theallswap.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class SingleFriendProfile extends ActionBarActivity {

    private SingleFriendProfile activity = this;
    private UserController uc= new UserController();
    private static final String FILENAME = "userProfile.txt";
    private ArrayList<User> userList =new ArrayList<User>();

    private TextView userName;
    private TextView userEmail;
    private TextView userCity;

    private String tvUserName;
    private String tvUserEmail;
    private String tvUserCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_friend_profile);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        userList = uc.loadUserFromFile(activity, FILENAME, userList);
        int userId =uc.stringToInt(id);
        User singleUser = userList.get(userId);
        userName = (TextView) findViewById(R.id.single_user_name);
        userEmail = (TextView) findViewById(R.id.single_user_email);
        userCity = (TextView) findViewById(R.id.single_user_city);
        userName.setText(singleUser.getUserId());
        userEmail.setText(singleUser.getUserProfile().getUserContactInformation());
        userCity.setText(singleUser.getUserProfile().getUserCity());

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
}
