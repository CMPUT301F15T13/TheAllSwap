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
        Button viewTopTradersButton = (Button)findViewById(R.id.b_view_top_traders);

        startNewTradeButton.setOnClickListener(this);
        viewPendingTradesButton.setOnClickListener(this);
        viewCompletedTradesButton.setOnClickListener(this);
        viewTopTradersButton.setOnClickListener(this);
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
        if (view.getId() == R.id.b_view_pending_trades) {
            uc.classIntent(PendingTrades.class, activity);
        }
        if (view.getId() == R.id.b_view_completed_trades) {
            uc.classIntent(CompletedTrades.class, activity);
        }
        if (view.getId() == R.id.b_view_top_traders) {
            uc.classIntent(TopTraders.class, activity);
        }
    }
}
