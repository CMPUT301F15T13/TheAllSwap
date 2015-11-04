package com.example.qyu4.theallswap.Model;

/**
 * Profile class is an Model that handles all Profile structure.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
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
