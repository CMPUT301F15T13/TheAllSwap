/*
 * Copyright 2015 Alexander Ozero, Qiang Yu, Eric Smith, Lixin Jin, Daniel Belanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Controller.TradeController;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Item;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class CreateTrade extends ActionBarActivity implements View.OnClickListener {
    private CreateTrade activity = this;
    private UserController uc = new UserController();
    private InventoryController ic = new InventoryController();
    private TradeController tc = new TradeController();

    private UserList userList;
    private TradeList tradeList;
    private User currentUser;

    private ArrayAdapter<User> adapterMine;
    private ArrayAdapter<User> adapterFriend;
    private ListView itemListMine;
    private ListView itemListFriend;
    private ArrayList<String> resultListMine = new ArrayList<>();
    private ArrayList<String> resultListFriend = new ArrayList<>();

    private String friendId;
    private String friendIdString;
    private String userIdString;
    private String mySelectedItem;
    private String friendsSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trade);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();
        userIdString = currentUser.getUserId();

        tradeList = TradeList.getTradeList();

        //Get friend with whom we are trading
        Intent intent = getIntent();
        friendId = intent.getStringExtra("id");
        //friendIdString = userList.get(Integer.parseInt(friendId)).getUserId();
        User friend = userList.getUserFromId(currentUser.getFriendsList().get(uc.stringToInt(friendId)));
        friendIdString = friend.getUserId();

        itemListMine = (ListView)findViewById(R.id.lv_your_items);
        itemListFriend = (ListView)findViewById(R.id.lv_friends_items);

        itemListMine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mySelectedItem = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "Selected: " + mySelectedItem,
                        Toast.LENGTH_SHORT).show();
            }
        });

        itemListFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                friendsSelectedItem = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "Selected: " + friendsSelectedItem,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Get Public Items in personal inventory
        ArrayList<Item> items = ic.showNonPrivateItems(currentUser);
        for(Item i : items) {
            resultListMine.add(i.getItemName());
        }

        //Get Public Items in friend's inventory
        items = ic.showNonPrivateItems(friend);
        for(Item i : items) {
            resultListFriend.add(i.getItemName());
        }


        adapterMine = new ArrayAdapter<>(activity, R.layout.list_item, (ArrayList) resultListMine);
        adapterFriend = new ArrayAdapter<>(activity, R.layout.list_item, (ArrayList) resultListFriend);
        itemListMine.setAdapter(adapterMine);
        itemListFriend.setAdapter(adapterFriend);

        Button offerTradeButton = (Button)findViewById(R.id.b_offer_trade);
        Button resetSelectionButton = (Button)findViewById(R.id.b_reset_selection);
        offerTradeButton.setOnClickListener(this);
        resetSelectionButton.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_offer_trade) {
            if(mySelectedItem == null || friendsSelectedItem == null) {
                Toast.makeText(getApplicationContext(), "Select the two items you wish to be traded",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Trade trade = new Trade(friendIdString, friendsSelectedItem, userIdString, mySelectedItem);
                tradeList.add(trade);
                tc.saveTradeInFile(tradeList.getFilename(), activity, tradeList);

                Toast.makeText(getApplicationContext(), "Offered to trade " + mySelectedItem +
                        " for " + friendsSelectedItem, Toast.LENGTH_LONG).show();
                activity.finish();
            }
        }
        else if(view.getId()==R.id.b_reset_selection) {
            mySelectedItem = null;
            friendsSelectedItem = null;
            Toast.makeText(getApplicationContext(), "Selection has been reset",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

