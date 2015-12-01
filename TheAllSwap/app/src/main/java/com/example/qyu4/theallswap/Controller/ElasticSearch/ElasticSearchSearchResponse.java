/**
 * This code has been taken and modified from:
 * https://github.com/rayzhangcl/ESDemo
 *
 * @author Chenlei Zhang - Original Owner
 * @author Alex, Qiang, Eric, Daniel, Lixin -Minor Editor
 */
package com.example.qyu4.theallswap.Controller.ElasticSearch;

import java.util.ArrayList;
import java.util.Collection;
public class ElasticSearchSearchResponse<T> {
    int took;
    boolean timed_out;
    transient Object _shards;
    Hits<T> hits;
    boolean exists;
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits.getHits();
    }
    public Collection<T> getSources() {
        Collection<T> out = new ArrayList<T>();
        for (ElasticSearchResponse<T> essrt : getHits()) {
            out.add( essrt.getSource() );
        }
        return out;
    }
    public String toString() {
        return (super.toString() + ":" + took + "," + _shards + "," + exists + ","  + hits);
    }
}
