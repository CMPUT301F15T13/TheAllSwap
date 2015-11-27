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

    private ArrayAdapter<User> adapter;
    private ListView itemList;
    private ListView itemList2;
    private ArrayList resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trade);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        itemList = (ListView)findViewById(R.id.listView);
        itemList2 = (ListView)findViewById(R.id.listView2);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selectedItem,
                        Toast.LENGTH_SHORT).show();

                //TODO: First item selected
            }
        });

        itemList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selectedItem,
                        Toast.LENGTH_SHORT).show();
                //TODO: Second item selected
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        resultList = uc.convertItemToString(currentUser, resultList);

        // TODO? Use spinners instead of lists for clearer selection
        adapter = new ArrayAdapter<User>(this, R.layout.list_item, resultList);
        itemList.setAdapter(adapter);
        itemList2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
