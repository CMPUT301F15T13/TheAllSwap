package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.R;
import com.example.qyu4.theallswap.Controller.UserController;

import java.util.ArrayList;
/**
 * UserFriends class is an activity that showing all the current user's friends.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class UserFriends extends ActionBarActivity {
    private User currentUser;
    private String currentUserString;

    private UserFriends activity = this;
    private UserController uc = new UserController();
    private ArrayList<User> userList= new ArrayList<User>();
    private static final String FILENAME = "userProfile.txt";
    private ArrayAdapter<User> adapter;
    private ArrayList resultList = new ArrayList<>();

    private ListView friendList;
    private Button addFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_friends);

        //Track currently logged in user
        userList = uc.loadUserFromFile(activity, FILENAME, userList);
        Intent intent = getIntent();
        currentUserString = intent.getStringExtra("myID");
        currentUser = uc.findUserById(currentUserString, userList);

        //Shows currently logged in username in a toast
        //uc.makeInputStringToast(this, currentUserString);

        resultList = uc.convertUserToString(currentUser.getFriendsList(), resultList);

        friendList = (ListView)findViewById(R.id.lv_user_friends);
        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(activity,SingleFriendProfile.class);
                i.putExtra("id", String.valueOf(position));
                i.putExtra("myID", currentUser.getUserId());
                activity.startActivity(i);
            }
        });

        friendList.setLongClickable(true);
        friendList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                //This should maybe prompt for a new menu of options but not delete outright.
                //Especially right now it deletes the entire user from existence.
                uc.makeInputStringToast(activity, "Long Click");
                /**
                 * call removeUser from controller to delete an user from the userList.
                 */
                //userList = uc.removeUser(userList, position);
                /**
                 * save the new userList to the file to sync.
                 */
                //uc.saveInFile(FILENAME, activity, userList);
                /**
                 * call remove item of the result list.
                 */
                //userList = uc.removeItem(userList, position);
                /**
                 * notify adapter changes have been done.
                 */
                //adapter.notifyDataSetChanged();
                //uc.classIntent(PreviousBrowsedTrade.class, activity);
                return true;
            }
        });

        addFriend = (Button)findViewById(R.id.btn_add_friend);
        addFriend.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(activity, AddFriend.class);
                i.putExtra("myID", currentUser.getUserId());
                activity.startActivity(i);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ArrayAdapter<User>(activity, R.layout.list_item, resultList);
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
        uc.passUserToActivity(UserInventory.class, activity, currentUserString);
    }
    public void userMyTradeSelected(MenuItem menu){
        uc.passUserToActivity(UserTrade.class, activity, currentUserString);
    }
    public void userMyFriendsSelected(MenuItem menu){
        uc.passUserToActivity(UserFriends.class, activity, currentUserString);
    }
    public void userMyProfileSelected(MenuItem menu){
        uc.passUserToActivity(UserProfile.class, activity, currentUserString);
    }
    public void userSearchSelected(MenuItem menu){
        uc.passUserToActivity(Search.class, activity, currentUserString);
    }
    public void userPreviousBrowseSelected(MenuItem menu){
        uc.passUserToActivity(PreviousBrowsedTrade.class, activity, currentUserString);
    }
    public void userLogoutSelected(MenuItem menu){
        uc.passUserToActivity(UserLogin.class, activity, currentUserString);
    }


}
