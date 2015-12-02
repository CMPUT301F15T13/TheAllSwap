package com.example.qyu4.theallswap.View;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import com.example.qyu4.theallswap.Controller.ElasticSearch.SearchHit;
import com.example.qyu4.theallswap.Controller.UserController;
import com.example.qyu4.theallswap.Model.Profile;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.Users;
import com.example.qyu4.theallswap.R;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class TestingEs extends ActionBarActivity {
    private Users users;
    private Gson gson = new Gson();
    private static final String TAG = "Users controller";
    private Button testingButton;
    private Button showResultButton;
    private TextView tv;
    private UserController uc = new UserController();
    private TestingEs activity = this;
    private User oldUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_es);

        testingButton = (Button)findViewById(R.id.b_testing);
        showResultButton = (Button)findViewById(R.id.b_show_result);
        tv = (TextView)findViewById(R.id.user_email_result);
        testingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = new User();
                Profile userProfile = new Profile();
                user.setUserId("hello4");
                userProfile.setUserCity("Edmonton4");
                userProfile.setUserContactInformation("hello4@ualberta.ca");
                user.setUserProfile(userProfile);
                // Execute the thread
                Thread thread = new AddThread(user);
                thread.start();

            }
        });
        showResultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String userEmail = oldUser.getUserId();
                tv.setText(userEmail);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        Thread thread = new GetThread(4);
        thread.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_testing_es, menu);
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
    /**
     * Adds a new movie
     */
    public void addUser(User user) {


        HttpClient httpClient = new DefaultHttpClient();
        Users users1 = new Users();
        try {
            HttpPost addRequest = new HttpPost(users1.getResourceUrl() + "4");

            StringEntity stringEntity = new StringEntity(gson.toJson(user));
            addRequest.setEntity(stringEntity);
            addRequest.setHeader("Accept", "application/json");

            HttpResponse response = httpClient.execute(addRequest);
            String status = response.getStatusLine().toString();
            Log.i(TAG, status);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public User getUser(int userId) {
        Users usersA = new Users();


        SearchHit<User> sr = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(usersA.getResourceUrl()+ String.valueOf(userId));

        HttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
        } catch (ClientProtocolException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }

        Type searchHitType = new TypeToken<SearchHit<User>>() {}.getType();

        try {
            sr = gson.fromJson(
                    new InputStreamReader(response.getEntity().getContent()),
                    searchHitType);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sr.getSource();

    }
    /**
     * thread
     */
    class AddThread extends Thread {
        private User user;

        public AddThread(User user) {
            this.user = user;
        }

        @Override
        public void run() {
                addUser(user);

            // Give some time to get updated info
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //runOnUiThread(doFinishAdd);
        }
    }
    // Thread that close the activity after finishing add
    private Runnable doFinishAdd = new Runnable() {
        public void run() {
            finish();
        }
    };
    class GetThread extends Thread {
        private int id;

        public GetThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            oldUser = getUser(id);
        }
    }
}
