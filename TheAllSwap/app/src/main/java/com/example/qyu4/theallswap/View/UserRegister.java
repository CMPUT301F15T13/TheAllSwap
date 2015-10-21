package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.qyu4.theallswap.R;

public class UserRegister extends ActionBarActivity implements View.OnClickListener{
    private UserRegister activity = this;
    private Button userRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        userRegister = (Button) findViewById(R.id.b_new_user_submit);
        userRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_new_user_submit){
            classIntent(UserMainViewing.class);
        }

    }
    public void classIntent(Class newClass){
        Intent openNewActivity = new Intent(activity, newClass);
        startActivity(openNewActivity);
    }
}
