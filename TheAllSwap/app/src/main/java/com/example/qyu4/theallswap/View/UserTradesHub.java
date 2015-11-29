package com.example.qyu4.theallswap.View;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.R;

public class UserTradesHub extends ActionBarActivity implements View.OnClickListener {

    private UserTradesHub activity = this;
    private UserController uc = new UserController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_trades_hub);

        Button startNewTradeButton = (Button)findViewById(R.id.b_start_new_trade);
        Button viewPendingTradesButton = (Button)findViewById(R.id.b_view_pending_trades);
        Button viewCompletedTradesButton = (Button)findViewById(R.id.b_view_completed_trades);

        startNewTradeButton.setOnClickListener(this);
        viewPendingTradesButton.setOnClickListener(this);
        viewCompletedTradesButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_trades_hub, menu);
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
        if (view.getId() == R.id.b_start_new_trade) {
            uc.classIntent(UserTrade.class, activity);
        }
    }
}
