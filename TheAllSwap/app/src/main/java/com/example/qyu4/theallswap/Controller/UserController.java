package com.example.qyu4.theallswap.Controller;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by qyu4 on 10/24/15.
 */
public class UserController {
    private Context context;
    public boolean registerNewUserPasswordCheck(String firstPassword, String secondPassword){
        if (firstPassword.equals(secondPassword)){

            return true;
        }else {
            return false;
        }
    }
    public void makeToastIn(Context context){
        Toast.makeText(context, "invalid same password", Toast.LENGTH_LONG).show();
    }

}
