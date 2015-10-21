package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.qyu4.theallswap.R;

public class UserMainViewing extends ActionBarActivity {
    private UserMainViewing activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_viewing);
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
        classIntent(UserInventory.class);
    }
    public void userMyTradeSelected(MenuItem menu){
        classIntent(UserTrade.class);
    }
    public void userMyFriendsSelected(MenuItem menu){
        classIntent(UserFriends.class);
    }
    public void userMyProfileSelected(MenuItem menu){
        classIntent(UserProfile.class);
    }
    public void userSearchSelected(MenuItem menu){
        classIntent(Search.class);
    }
    public void userPreviousBrowseSelected(MenuItem menu){
        classIntent(PreviousBrowsedTrade.class);
    }
    public void userLogoutSelected(MenuItem menu){
        classIntent(UserLogin.class);
    }

    public void classIntent(Class newClass){
        Intent openNewActivity = new Intent(activity, newClass);
        startActivity(openNewActivity);
    }
}
