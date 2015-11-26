package com.example.qyu4.theallswap.View;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.qyu4.theallswap.R;

public class ItemProfile extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
