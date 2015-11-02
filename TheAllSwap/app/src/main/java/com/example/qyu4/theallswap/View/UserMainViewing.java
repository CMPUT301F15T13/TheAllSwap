package com.example.qyu4.theallswap.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;
import com.example.qyu4.theallswap.Controller.UserController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserMainViewing extends ActionBarActivity implements View.OnClickListener{
    private UserMainViewing activity = this;
    private UserController uc = new UserController();
    private ArrayList<User> userList= new ArrayList<User>();
    private ArrayAdapter<User> adapter;
    private ListView friendList;
    private static final String FILENAME = "userProfile.txt";
    private ArrayList resultList = new ArrayList();
    private Button myInventoryButton;
    private Button myTradeButton;
    private Button mySearchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_viewing);

        myInventoryButton = (Button)findViewById(R.id.b_my_inventory);
        myTradeButton = (Button)findViewById(R.id.b_my_trade);
        mySearchButton = (Button)findViewById(R.id.b_search);

        myInventoryButton.setOnClickListener(this);
        myTradeButton.setOnClickListener(this);
        mySearchButton.setOnClickListener(this);

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
    public void userMyTradeSelected(MenuItem menu){uc.classIntent(UserTrade.class, activity);
    }
    public void userMyFriendsSelected(MenuItem menu){
        uc.classIntent(UserFriends.class, activity);
    }
    public void userMyProfileSelected(MenuItem menu){
        uc.classIntent(UserProfile.class, activity);
    }
    public void userSearchSelected(MenuItem menu){uc.classIntent(Search.class, activity);
    }
    public void userPreviousBrowseSelected(MenuItem menu){
        uc.classIntent(PreviousBrowsedTrade.class, activity);
    }
    public void userLogoutSelected(MenuItem menu){
        uc.classIntent(UserLogin.class, activity);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_my_inventory){
            uc.classIntent(UserInventory.class, activity);
        }
        else if(view.getId()==R.id.b_my_trade){
            uc.classIntent(UserTrade.class, activity);
        }
        else if(view.getId()==R.id.b_search){
            uc.classIntent(Search.class, activity);
        }
    }


}