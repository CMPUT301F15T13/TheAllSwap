/**
 * This code has been taken and modified from:
 * https://github.com/AlanFor301/AndroidElasticSearch/
 * tree/master/app/src/main/java/ca/ualberta/ssrg/movies
 *
 * @author joshua2ua - Original Owner
 * @author Alex, Qiang, Eric, Daniel, Lixin -Minor Editor
 */
package com.example.qyu4.theallswap.Model;
import java.util.ArrayList;


public class Users  extends ArrayList<User> implements Observable{
    private volatile ArrayList<Observer> observers = new ArrayList<Observer>();
    private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f15t13/user/";
    private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f15t13/user/_search";

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.notifyUpdated(this);
        }
    }

    public String getResourceUrl() {
        return RESOURCE_URL;
    }

    public String getSearchUrl() {
        return SEARCH_URL;
    }

    /**
     * Java wants this, we don't need it for Gson/Json
     */
    private static final long serialVersionUID = 3199561696102797345L;

}
