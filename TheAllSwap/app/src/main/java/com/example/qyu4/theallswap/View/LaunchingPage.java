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

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.qyu4.theallswap.R;
/**
 * LaunchingPage activity is designed for giving some basic user manual to current user.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class LaunchingPage extends Activity implements OnClickListener {
    private LaunchingPage activity = this;
    private Button buttonToLoginPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching_page);
        buttonToLoginPage = (Button) findViewById(R.id.b_to_login_page);
        buttonToLoginPage.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_to_login_page){
            classIntent(UserLogin.class);
        }
    }
    public void classIntent(Class newClass){
        Intent openNewActivity = new Intent(activity, newClass);
        startActivity(openNewActivity);
    }
}
