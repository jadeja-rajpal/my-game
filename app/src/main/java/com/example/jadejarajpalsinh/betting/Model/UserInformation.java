package com.example.jadejarajpalsinh.betting.Model;

public class UserInformation {

    String editTextPhone;
    String etPassword1;

    int rupees;

    public UserInformation() {
    }

    public UserInformation(String editTextPhone, String etPassword1 , int rupees) {
        this.editTextPhone = editTextPhone;
        this.etPassword1 = etPassword1;

        this.rupees = rupees;
    }


    public String getEditTextPhone() {
        return editTextPhone;
    }

    public void setEditTextPhone(String editTextPhone) {
        this.editTextPhone = editTextPhone;
    }

    public String getEtPassword1() {
        return etPassword1;
    }

    public void setEtPassword1(String etPassword1) {
        this.etPassword1 = etPassword1;
    }


    public int getRupees() {
        return rupees;
    }

    public void setRupees(int rupees) {
        this.rupees = rupees;
    }
}
