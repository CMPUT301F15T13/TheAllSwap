/**
 * This code has been taken and modified from:
 * https://github.com/AlanFor301/AndroidElasticSearch/
 * tree/master/app/src/main/java/ca/ualberta/ssrg/movies
 *
 * @author joshua2ua - Original Owner
 * @author Alex, Qiang, Eric, Daniel, Lixin -Minor Editor
 */
package com.example.qyu4.theallswap.Model;

public interface Observable {
    public void addObserver(Observer o);
    public void deleteObserver(Observer o);
    public void notifyObservers();
}
