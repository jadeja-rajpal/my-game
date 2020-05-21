package com.example.jadejarajpalsinh.betting.Model;

public class UserInformation {

    String number;


    String balance;

    public UserInformation() {
    }

    public UserInformation(String number , String  balance) {
        this.number = number;


        this.balance = balance;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }




    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
