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

public class UserMainViewing extends ActionBarActivity {
    private UserMainViewing activity = this;
    private UserController uc = new UserController();
    private ArrayList<User> userList= new ArrayList<User>();
    private ArrayAdapter<User> adapter;
    private ListView friendList;
    private static final String FILENAME = "userProfile.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_viewing);
        friendList = (ListView)findViewById(R.id.lv_friend_list);
        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: some stuff
                uc.makeInvalidUserToast(activity);
            }


        });
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        /***************************************************
         TODO: add loading friends list method
         TODO: into arrayList user list
         *************************************************/
        loadFromFile();

        /***************************************************
         TODO: add loading friends list method.
        *************************************************/

        adapter = new ArrayAdapter<User>(this, R.layout.list_item, userList);
        friendList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
    public void userLogoutSelected(MenuItem menu){uc.classIntent(UserLogin.class, activity);
    }

    public void loadFromFile(){
        try{
        FileInputStream fis = openFileInput(FILENAME);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));


        Gson gson = new Gson();
        // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
        Type arrayListType = new TypeToken<ArrayList<User>>(){}.getType();
        userList = gson.fromJson(in, arrayListType);


    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        userList = new ArrayList<User>();
    }

    }
}