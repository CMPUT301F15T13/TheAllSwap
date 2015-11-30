package com.example.qyu4.theallswap.View;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.Model.UserSuccessfulTradesComparator;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;
import java.util.Collections;

public class TopTraders extends ActionBarActivity {

    private TopTraders activity = this;
    private UserController uc = new UserController();
    private ArrayList<String> topTradersList = new ArrayList<>();
    private UserList userList = UserList.getUserList();
    private ArrayAdapter<String> adapter;
    private ListView topUsersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_traders);

        // Sort users by successful trades, then add their IDs to a list
        Collections.sort(userList, new UserSuccessfulTradesComparator());

        for(User user : userList) {
            topTradersList.add(user.getUserId()+" - "+Integer.toString(user.getSuccessfulTrades()));
        }

        topUsersListView = (ListView)findViewById(R.id.lv_top_traders);

        //Set adapter
        adapter = new ArrayAdapter<>(activity, R.layout.list_item, (ArrayList) topTradersList);
        topUsersListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_traders, menu);
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
