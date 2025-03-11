package org.example.pom.objects;

import org.example.pom.utils.ReadConfig;

public class BillingAddress {
    private ReadConfig config;
    // Constructor initializes ReadConfig
    public BillingAddress() {
        config = new ReadConfig();
    }

    public String getCountry() {
        return config.getProperty("country");
    }



    public String getState() {
        return config.getProperty("state");
    }

//    public void setState(String state) {
//        this.state = state;
//    }

    public String getFirstname() {
        return config.getProperty("firstname");
    }


    public String getLastname() {
        return config.getProperty("lastname");
    }



    public String getAddress() {
        return config.getProperty("address");
    }



    public String getCity() {
        return config.getProperty("city");
    }



    public String getZipcode() {
        return config.getProperty("zipcode");
    }


    public String getEmail() {
        return config.getProperty("email");
    }

}
