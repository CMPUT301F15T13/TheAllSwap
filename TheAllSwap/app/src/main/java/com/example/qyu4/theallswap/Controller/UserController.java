package com.example.qyu4.theallswap.Controller;


import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.View.UserInventory;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Objects;

/**
 * Control User Class data.
 *
 * @author qyu4, egsmith, lixin1, ozero, debelang
 * Created by qyu4 on 10/24/15.
 */
public class UserController {
    /**
     * Checking if two input passwords are equal.
     * @param firstPassword: user input first password.
     * @param secondPassword: user input second password.
     * @return: boolean indicating if two passwords are equal.
     */
    public boolean registerNewUserPasswordCheck(String firstPassword, String secondPassword){
        if (firstPassword.equals(secondPassword)){

            return true;
        }else {
            return false;
        }
    }

    /**
     * make text when user input two passwords are not equal.
     *
     * @param context: for the convenience of Toast methods.
     */
    public void makeInvalidPasswordToast(Context context){
        Toast.makeText(context, "invalid same password", Toast.LENGTH_LONG).show();
    }

    /**
     * save object in Gson style in the file.
     *
     * @param FileName: file name
     * @param context: convenience of openFileOutput method.
     * @param nameOfClass: the class type that will be saved in the file.
     */
    public void saveInFile(String FileName, Context context, User nameOfClass) {

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

    /**
     * Intent method between activities.
     * s
     * @param newClass: the target activity.
     * @param context: the convenience of Intent.
     */
    public void classIntent(Class newClass, Context context){
        Intent openNewActivity = new Intent(context, newClass);
        context.startActivity(openNewActivity);
    }

    /**
     * Fixing needed
     * @param context
     * @param fileName
     */
    private void loadFromFile(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            //BufferedReader in = new BufferedReader(new context.InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            //Type arraylistType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
            //tweets = gson.fromJson(in, arraylistType);

        } catch (FileNotFoundException e) {
            //tweets = new ArrayList<Tweet>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkingUserExist(String userName){
        return true;
    }
}
