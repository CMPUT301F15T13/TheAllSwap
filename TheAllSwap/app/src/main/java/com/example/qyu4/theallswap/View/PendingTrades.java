package com.example.qyu4.theallswap.View;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.qyu4.theallswap.Controller.TradeController;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.util.ArrayList;

public class PendingTrades extends ActionBarActivity {
    private PendingTrades activity = this;
    private TradeController tc = new TradeController();

    private TradeList tradeList;
    //private Trade currentTrade;
    private UserList userList;

    private ArrayAdapter<Trade> adapter;
    private ArrayList<String> resultList = new ArrayList<>();
    private ListView tradeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_trades);

        tradeList = TradeList.getTradeList();

        //currentTrade = tradeList.getCurrentTrade();

        //Unfiltered list of all trades:
        resultList = tc.convertTradeToString(tradeList, resultList);

        //Filtered for pending trades of the current user
        // resultList = tc.getPendingTrades(userList.getCurrentUser(), tradeList);

        tradeListView = (ListView)findViewById(R.id.lv_pending_trades);

        //Set adapter
        adapter = new ArrayAdapter<Trade>(activity, R.layout.list_item, (ArrayList) resultList);
        tradeListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pending_trades, menu);
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
