package com.example.food_oasis_app;

public class SetCustomer {
    private String firstName;
    private String lastName;
    private String businessName;
    private  String uuid;

    public SetCustomer(String firstName, String lastName, String businessName, String uuid) {
        this.firstName= firstName;
        this.lastName = lastName;
        this.businessName = businessName;
        this.uuid = uuid;
    }

    public  SetCustomer ()  {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid () {
        return uuid;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setProfileName(String businessName) {
        this.businessName = businessName;

    }

}
