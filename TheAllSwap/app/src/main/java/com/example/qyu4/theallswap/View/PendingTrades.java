package com.example.qyu4.theallswap.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Trade;
import com.example.qyu4.theallswap.Model.TradeList;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PendingTrades extends ActionBarActivity {
    private PendingTrades activity = this;
    private TradeController tc = new TradeController();
    private UserController uc = new UserController();

    private TradeList tradeList;
    private UserList userList = UserList.getUserList();

    private ArrayAdapter<String> adapter;
    private ArrayList<String> resultList = new ArrayList<>();
    private ListView tradeListView;

    final String userId = userList.getCurrentUser().getUserId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_trades);

        tradeList = TradeList.getTradeList();

        //Unfiltered list of all trades
        //resultList = tc.convertTradeToString(tradeList, resultList);

        //Filtered for pending trades of the current user
        resultList = tc.getPendingTrades(userId, tradeList);

        tradeListView = (ListView)findViewById(R.id.lv_pending_trades);

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
                if (trade.getBorrowerId().equals(userId)) {
                    buildRetractDialog("Would you like to retract this trade offer?");

                } else if (trade.getOwnerId().equals(userId)) {
                    buildAccorDecDialog("Would you like to Accept or Decline this trade offer?");
                }
            }
        });
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

    public void buildAccorDecDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PendingTrades.this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_accept,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Update the trade
                        Trade currentTrade = tradeList.getCurrentTrade();
                        currentTrade.setOwnerAcceptedTrade(true);
                        currentTrade.setTradePending(false);
                        // Update the successful trade score of both owner...
                        userList.getCurrentUser().incrementSuccessfulTrades();
                        // ... and borrower
                        String borrowerId = currentTrade.getBorrowerId();
                        ArrayList<User> friendsList = userList.getCurrentUser().getFriendsList();
                        for(User user : friendsList) {
                            if(user.getUserId().equals(borrowerId)) {
                                user.incrementSuccessfulTrades();
                            }
                        }
                        // Save the trade and the successfulTrades increments
                        tc.saveTradeInFile(tradeList.getFilename(), activity, tradeList);
                        uc.saveInFile(userList.getFilename(), activity, userList);
                        Toast.makeText(getApplicationContext(),
                                "Offer Accepted! Please contact the borrower to arrange the trade",
                                Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        activity.finish();
                    }
                });
        builder.setNegativeButton(R.string.dialog_decline,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tradeList.getCurrentTrade().setOwnerAcceptedTrade(false);
                        tradeList.getCurrentTrade().setTradePending(false);
                        tc.saveTradeInFile(tradeList.getFilename(), activity, tradeList);
                        Toast.makeText(getApplicationContext(), "Offer Declined.",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        activity.finish();
                    }
                });

        builder.setNeutralButton(R.string.dialog_cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public void buildRetractDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PendingTrades.this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tradeList.getCurrentTrade().setBorrowerRetractedTrade(true);
                        tradeList.getCurrentTrade().setTradePending(false);
                        tc.saveTradeInFile(tradeList.getFilename(), activity, tradeList);
                        Toast.makeText(getApplicationContext(), "Offer Retracted.",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        activity.finish();
                    }
                });
        builder.setNegativeButton(R.string.dialog_no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
