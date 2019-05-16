package com.iriad11.pundrauniversity.datafromsheet;

public class DataModel {
    String name;
    String phone;
    String designation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public void add(String name, String designation, String phone){
        this.name=name;
        this.phone=phone;
        this.designation=designation;
    }
}
