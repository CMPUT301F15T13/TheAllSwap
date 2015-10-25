package com.example.qyu4.theallswap.Model;

/**
 * Created by qyu4 on 10/20/15.
 */
public class Profile {
    private String userContactInformation;
    private String userCity;
    public Profile(){

    }
    public Profile(String userContactInformation, String userCity) {
        this.setUserContactInformation(userContactInformation);
        this.setUserCity(userCity);
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserContactInformation() {
        return userContactInformation;
    }

    public void setUserContactInformation(String userContactInformation) {
        this.userContactInformation = userContactInformation;
    }


}
