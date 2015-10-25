package com.example.qyu4.theallswap.Controller;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Objects;

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
    private void saveInFile(String FileName, Context context, Objects nameOfClass) {

            try {
                FileOutputStream fos = context.openFileOutput(FileName, 0);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
                Gson gson = new Gson();
                gson.toJson(nameOfClass, out);
                out.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e);
            }
        }
}
