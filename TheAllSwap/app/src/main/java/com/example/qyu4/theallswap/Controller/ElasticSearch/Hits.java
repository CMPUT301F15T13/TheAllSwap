/**
 * This code has been taken and modified from:
 * https://github.com/rayzhangcl/ESDemo
 *
 * @author Chenlei Zhang - Original Owner
 * @author Alex, Qiang, Eric, Daniel, Lixin -Minor Editor
 */
package com.example.qyu4.theallswap.Controller.ElasticSearch;

import java.util.Collection;
public class Hits<T> {
    int total;
    double max_score;
    Collection<ElasticSearchResponse<T>> hits;
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits;
    }
    public String toString() {
        return (super.toString()+","+total+","+max_score+","+hits);
    }
}
