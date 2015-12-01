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

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qyu4.theallswap.Controller.TradeController;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class CompletedTrades extends ActionBarActivity {
    CompletedTrades activity = this;
    private TradeController tc = new TradeController();

    private TradeList tradeList;
    private UserList userList = UserList.getUserList();

    private ArrayAdapter<String> adapter;
    private ArrayList<String> resultList = new ArrayList<>();
    private ListView tradeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_trades);

        tradeList = TradeList.getTradeList();

        //Unfiltered list of all trades:
        //resultList = tc.convertTradeToString(tradeList, resultList);

        //Filtered for completed trades of the current user
        final String userId = userList.getCurrentUser().getUserId();
        resultList = tc.getCompletedTrades(userId, tradeList);

        tradeListView = (ListView)findViewById(R.id.lv_completed_trades);

        //Set adapter
        adapter = new ArrayAdapter<>(activity, R.layout.list_item, (ArrayList) resultList);
        tradeListView.setAdapter(adapter);

        tradeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: Ask to accept/decline if owner or cancel if borrower
                String item = tradeListView.getItemAtPosition(position).toString();
                int index = tc.getIndexOfTrade(item, tradeList);
                Trade trade = tradeList.get(index);
                tradeList.setCurrentTrade(trade);

                if(tradeList.getCurrentTrade().isOwnerAcceptedTrade()) {
                    Toast.makeText(getApplicationContext(), "Trade was accepted by owner",
                            Toast.LENGTH_SHORT).show();
                }
                else if(tradeList.getCurrentTrade().isBorrowerRetractedTrade()) {
                    Toast.makeText(getApplicationContext(), "Trade was retracted by borrower",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Trade was declined by owner",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_completed_trades, menu);
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
