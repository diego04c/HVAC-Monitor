package com.example.hvac_monitor;

public class Contacts {

    private String Name;
    private String LastName;
    private String Address;
    private String Phone;

    public Contacts(){
    }

    //first name

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    //last name

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastname) {
        LastName = lastname;
    }

    //address

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    //phone number

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}