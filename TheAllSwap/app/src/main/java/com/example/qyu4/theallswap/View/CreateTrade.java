package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class CreateTrade extends ActionBarActivity {
    private CreateTrade activity = this;
    private UserController uc = new UserController();

    private UserList userList;
    private User currentUser;

    private ArrayAdapter<User> adapterMine;
    private ArrayAdapter<User> adapterFriend;
    private ListView itemListMine;
    private ListView itemListFriend;
    private ArrayList<String> resultListMine = new ArrayList<>();
    private ArrayList<String> resultListFriend = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trade);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        //Get friend with whom we are trading
        Intent intent = getIntent();
        String friendId = intent.getStringExtra("id");
        User friend = currentUser.getFriendsList().get(uc.stringToInt(friendId));

        itemListMine = (ListView)findViewById(R.id.listView2);
        itemListFriend = (ListView)findViewById(R.id.listView);

        itemListMine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selectedItem,
                        Toast.LENGTH_SHORT).show();

                //TODO: First item selected
            }
        });

        itemListFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selectedItem,
                        Toast.LENGTH_SHORT).show();
                //TODO: Second item selected
            }
        });

        resultListMine = uc.convertItemToString(currentUser, resultListMine);
        resultListFriend = uc.convertItemToString(friend, resultListFriend);

        // TODO? Use spinners instead of lists for clearer selection
        adapterMine = new ArrayAdapter<User>(activity, R.layout.list_item, (ArrayList) resultListMine);
        adapterFriend = new ArrayAdapter<User>(activity, R.layout.list_item, (ArrayList) resultListFriend);
        itemListMine.setAdapter(adapterMine);
        itemListFriend.setAdapter(adapterFriend);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterMine.notifyDataSetChanged();
        adapterFriend.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_trade, menu);
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

