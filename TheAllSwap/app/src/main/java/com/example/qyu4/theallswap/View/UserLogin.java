package com.example.qyu4.theallswap.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.qyu4.theallswap.R;

public class UserLogin extends Activity implements View.OnClickListener{
    private UserLogin activity = this;
    private Button userLogin;
    private Button userRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userLogin = (Button) findViewById(R.id.b_user_login);
        userRegister = (Button) findViewById(R.id.b_user_register);
        userRegister.setOnClickListener(this);
        userLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b_user_login){
            classIntent(UserMainViewing.class);
        }
        if(view.getId()==R.id.b_user_register){
            classIntent(UserRegister.class);
        }
    }
    public void classIntent(Class newClass){
        Intent openNewActivity = new Intent(activity, newClass);
        startActivity(openNewActivity);
    }
}
