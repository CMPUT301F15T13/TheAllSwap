/*
 * Copyright 2015 Alexander Ozero, Qiang Yu, Eric Smith, Lixin Jin, Daniel Belanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.qyu4.theallswap.Controller.ElasticSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.util.Log;
import com.example.qyu4.theallswap.Model.User;

public class ESClient {

    // Http Connector
    private HttpClient httpclient = new DefaultHttpClient();

    // JSON Utilities
    private Gson gson = new Gson();

    public static final String WEBSERVICE_URI = "http://cmput301.softwareprocess.es:8080/cmput301f15t13/";
    public static final String USERS_FOLDER = "user/";
    public static final String SEARCH_PRETTY = "_search?pretty=1&q=";
    public static final int MAX_USERS = 30;
    /**
     * This function stores a story on the WebServer based on the user's ID. If
     * a new story with the same Id is inserted, the previous one is
     * overwritten.
     *
     * Consumes the POST/Insert operation of the service
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    public void insertStory(User user) throws IOException {

        HttpPost httpPost = new HttpPost(WEBSERVICE_URI + USERS_FOLDER
                + user.getUserId());

        StringEntity stringentity = null;

        try {
            stringentity = new StringEntity(gson.toJson(user));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpPost.setHeader("Accept", "application/json");

        httpPost.setEntity(stringentity);

        HttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String status = response.getStatusLine().toString();
        System.out.println(status);
        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                entity.getContent()));
        String output;
        System.err.println("Output from Server -> ");
        while ((output = br.readLine()) != null) {
            System.err.println(output);
        }

    }
    /**
     * This function returns all the users on the server, as an ArrayList of
     * Users.
     *
     * @return allUsers
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = null;
        allUsers = this.searchUsers("*");
        Log.d("DEBUG", "" + allUsers.size());
        return allUsers;
    }
    /**
     * search by keywords
     */
    public ArrayList<User> searchUsers(String Keyword) {
        try {
            ArrayList<User> filteredStories = new ArrayList<User>();

            HttpGet searchRequest = new HttpGet(this.getQueryHttpURI(Keyword));

            searchRequest.setHeader("Accept", "application/json");
            HttpResponse response = httpclient.execute(searchRequest);
            String status = response.getStatusLine().toString();
            System.out.println(status);
            /**
             * this line has some error in the original code i cant fix.
             * the program does not recognise getEntityContent() method
             * After I look up in the HttpResponse manual list find getEntity method.
             * String json = getEntityContent(response);
             */
            String json = response.getEntity().toString();

            Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<User>>() {
            }.getType();

            ElasticSearchSearchResponse<User> esResponse = gson.fromJson(json,
                    elasticSearchSearchResponseType);
            System.err.println(esResponse);
            for (ElasticSearchResponse<User> r : esResponse.getHits()) {
                User story = r.getSource();
                filteredStories.add(story);
            }
            // searchRequest.releaseConnection();

            return filteredStories;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * This function is a private function and should not be called by anyone.
     * This function generates a URI that is needed in order to query some story
     * information from the WebServer.
     *
     * @param str
     * @return URI
     * @throws UnsupportedEncodingException
     */
    private String getQueryHttpURI(String str)
            throws UnsupportedEncodingException {
        String queryHttpURI = null;
        queryHttpURI = WEBSERVICE_URI + USERS_FOLDER + SEARCH_PRETTY
                + java.net.URLEncoder.encode(str, "UTF-8")+"&size=" + MAX_USERS;
        return queryHttpURI;
    }

}
