package com.example.abdelrahman.ik_real_estate2.Moudel;

import java.io.Serializable;

public class Users
        implements Serializable {
    String email;
    String Fname;
    String key;
    String Lname;
    String mainAdmin;
    String name;
    String phone;
    String type;

    public Users() {
    }

    public Users(String email, String fname, String key, String lname, String mainAdmin, String name, String phone, String type) {
        this.email = email;
        Fname = fname;
        this.key = key;
        Lname = lname;
        this.mainAdmin = mainAdmin;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLname() {
        return Lname;
    }

//    public void setLname(String lname) {
//        Lname = lname;
//    }

    public String getMainAdmin() {
        return mainAdmin;
    }

    public void setMainAdmin(String mainAdmin) {
        this.mainAdmin = mainAdmin;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}


/* Location:              C:\Users\Abdel Rahman\Desktop\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\example\abdelrahman\ik_real_state\Moudel\Users.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */