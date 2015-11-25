package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;
import com.example.qyu4.theallswap.Controller.UserController;

import java.util.ArrayList;
/**
 * UserMainView class is an activity that Navigates current user's requirement.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class UserMainView extends ActionBarActivity implements View.OnClickListener{
    private UserMainView activity = this;
    private UserController uc = new UserController();
    private String myID;
    private ArrayList<User> userList= new ArrayList<User>();
    private ArrayAdapter<User> adapter;
    private ListView friendList;
    private static final String FILENAME = "userProfile.txt";
    private ArrayList resultList = new ArrayList();
    private Button myInventoryButton;
    private Button myTradeButton;
    private Button myFriendsButton;
    private Button myProfileButton;
    private Button mySearchButton;
    private Button myLogOutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_viewing);

        Intent intent = getIntent();
        myID = intent.getStringExtra("myID");

        myInventoryButton = (Button)findViewById(R.id.b_my_inventory);
        myTradeButton = (Button)findViewById(R.id.b_my_trade);
        myFriendsButton = (Button)findViewById(R.id.b_my_friends);
        myProfileButton = (Button)findViewById(R.id.b_my_profile);
        mySearchButton = (Button)findViewById(R.id.b_search);
        myLogOutButton = (Button)findViewById(R.id.b_log_out);

        myInventoryButton.setOnClickListener(this);
        myTradeButton.setOnClickListener(this);
        myFriendsButton.setOnClickListener(this);
        myProfileButton.setOnClickListener(this);
        mySearchButton.setOnClickListener(this);
        myLogOutButton.setOnClickListener(this);

        System.out.println("Words: This is where the console out is");
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
        uc.passUserToActivity(UserInventory.class, activity, myID);
    }
    public void userMyTradeSelected(MenuItem menu){
        uc.passUserToActivity(UserTrade.class, activity, myID);
    }
    public void userMyFriendsSelected(MenuItem menu){
        uc.passUserToActivity(UserFriends.class, activity, myID);
    }
    public void userMyProfileSelected(MenuItem menu){
        uc.passUserToActivity(UserProfile.class, activity, myID);
    }
    public void userSearchSelected(MenuItem menu){uc.passUserToActivity(Search.class, activity, myID);
    }
    public void userPreviousBrowseSelected(MenuItem menu){
        uc.passUserToActivity(PreviousBrowsedTrade.class, activity, myID);
    }
    public void userLogoutSelected(MenuItem menu){
        uc.passUserToActivity(UserLogin.class, activity, myID);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_my_inventory){
            uc.passUserToActivity(UserInventory.class, activity, myID);
        }
        else if(view.getId()==R.id.b_my_trade){
            uc.passUserToActivity(UserTrade.class, activity, myID);
        }
        else if(view.getId()==R.id.b_my_friends){
            uc.passUserToActivity(UserFriends.class, activity, myID);
        }
        else if(view.getId()==R.id.b_my_profile){
            uc.passUserToActivity(UserProfile.class, activity, myID);
        }
        else if(view.getId()==R.id.b_search){
            uc.passUserToActivity(Search.class, activity, myID);
        }
        else if(view.getId()==R.id.b_log_out){
            uc.passUserToActivity(UserLogin.class, activity, myID);
        }
    }


}