package com.example.qyu4.theallswap.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qyu4.theallswap.Controller.InventoryController;
import com.example.qyu4.theallswap.Model.User;
import com.example.qyu4.theallswap.Model.UserList;
import com.example.qyu4.theallswap.R;
import com.example.qyu4.theallswap.Controller.UserController;

import java.util.ArrayList;
/**
 * Search class is an activity that current user can search for a certain Friend.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class Search extends ActionBarActivity {
    private Search activity =this;
    private UserController uc = new UserController();
    private InventoryController ic = new InventoryController();

    private SearchView SearchBox;

    private ListView itemList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemArray = new ArrayList<String>();

    private UserList userList;
    private User currentUser;
    private User singleUser = new User();

    private String itemCategory;
    private Spinner ItemCategory;
    private ArrayAdapter<String> categoryAdapter;
    private static final String[] m={"All", "Weapons", "Gadgets", "Intel", "Identities", "Fine Clothing",
            "Vehicles", "Bug-out Supplies", "Contacts", "Secret", "Most Secret", "Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        userList = UserList.getUserList();
        currentUser = userList.getCurrentUser();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        int userId = uc.stringToInt(id);
        singleUser = userList.getUserFromId(userList.get(userId).getUserId());

        itemArray = ic.showNonPrivateItems(singleUser);

        SearchBox = (SearchView) findViewById(R.id.search_inventory_friend);
        SearchBox.setOnQueryTextListener(new SearchBoxListener());

        ItemCategory = (Spinner) findViewById(R.id.sp_category_select);
        categoryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
        ItemCategory.setAdapter(categoryAdapter);
        ItemCategory.setOnItemSelectedListener(new SpinnerSelectedListener());
        ItemCategory.setVisibility(View.VISIBLE);

        itemList = (ListView)findViewById(R.id.lv_friend_inventory);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item);
        adapter.addAll(itemArray);
        itemList.setAdapter(adapter);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = currentUser.getFriendsList().indexOf(singleUser);
                uc.passValueToActivity(CreateTrade.class, activity, index);
                //activity.finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_main_viewing, menu);
        return true;
    }

    class SearchBoxListener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextChange(String newText) {
            itemArray = ic.searchSuggestions(singleUser, newText);
            adapter.clear();
            adapter.addAll(itemArray);
            adapter.notifyDataSetChanged();
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            itemArray = ic.searchSuggestions(singleUser, query);
            adapter.clear();
            adapter.addAll(itemArray);
            adapter.notifyDataSetChanged();
            return false;
        }
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            itemCategory = m[arg2];
            itemArray = ic.showItemsInCategory(singleUser, itemCategory);
            adapter.clear();
            adapter.addAll(itemArray);
            adapter.notifyDataSetChanged();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
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

    public void userMyInventorySelected(MenuItem menu){
        uc.classIntent(UserInventory.class, activity);
    }
    public void userMyTradeSelected(MenuItem menu){

        uc.classIntent(UserTrade.class, activity);
    }
    public void userMyFriendsSelected(MenuItem menu){
        uc.classIntent(UserFriends.class, activity);
    }
    public void userMyProfileSelected(MenuItem menu){
        uc.classIntent(UserProfile.class, activity);
    }
    public void userSearchSelected(MenuItem menu){
        uc.classIntent(Search.class, activity);
    }
    public void userPreviousBrowseSelected(MenuItem menu){
        uc.classIntent(PreviousBrowsedTrade.class, activity);
    }
    public void userLogoutSelected(MenuItem menu){
        uc.classIntent(UserLogin.class, activity);
    }


}