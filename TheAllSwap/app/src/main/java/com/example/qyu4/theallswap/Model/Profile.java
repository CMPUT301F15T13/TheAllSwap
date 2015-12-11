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
package com.example.qyu4.theallswap.Model;

/**
 * Profile class is an Model that handles all Profile structure.
 * @author qyu4, egsmith, lixin1, ozero, debelang.
 *
 */
public class Profile {
    private String userContactInformation;
    private String userCity;

    /**
     * Default constructor. All attributes initialized to null. Set each one before
     * using
     */
    public Profile(){

    }

    /**
     * Constructor to set all starting attributes of the Profile on construction
     */
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
