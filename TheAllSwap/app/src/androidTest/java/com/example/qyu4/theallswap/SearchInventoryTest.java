package com.example.qyu4.theallswap;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by ozero. Test file is skeletal.
 */
public class SearchInventoryTest extends ActivityInstrumentationTestCase2 {
    public SearchInventoryTest() {
        super(UserTradingActivity.class); // Temporary
    }

    public void testSearchInvByQuery() {
        // TODO Searching by query
        assertTrue(testInventoryFiltered());
    }

    public void testSearchInvByCategory() {
        // TODO Searching by query
        assertTrue(testInventoryFiltered());
    }

    public boolean testInventoryFiltered() {
        return true;
    }
}
