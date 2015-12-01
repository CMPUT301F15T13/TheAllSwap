/**
 * This code has been taken and modified from:
 * https://github.com/rayzhangcl/ESDemo
 *
 * @author Chenlei Zhang - Original Owner
 * @author Alex, Qiang, Eric, Daniel, Lixin -Minor Editor
 */
package com.example.qyu4.theallswap.Controller.ElasticSearch;


public class ElasticSearchResponse<T> {
    /**
     * this function just get the source and check does it exist
     */
    String _index;
    String _type;
    String _id;
    int _version;
    boolean exists;
    T _source;
    double max_score;

    public T getSource() {
        return _source;
    }

    public boolean getExtists() {
        return this.exists;
    }
}
