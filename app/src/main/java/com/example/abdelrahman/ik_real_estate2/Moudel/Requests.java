package com.example.abdelrahman.ik_real_estate2.Moudel;

import java.io.Serializable;

public class Requests
        implements Serializable {
    String clientName;
    String clientPhone;
    String comment;
    String details;
    String id;
    String memberID;
    String memberName;
    String price;
    String time;
    String title;

    public Requests() {
    }

    public Requests(String clientName, String clientPhone, String comment, String details, String id, String memberID, String memberName, String price, String time, String title) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.comment = comment;
        this.details = details;
        this.id = id;
        this.memberID = memberID;
        this.memberName = memberName;
        this.price = price;
        this.time = time;
        this.title = title;
    }

    public Requests(String clientName, String clientPhone, String comment, String details, String id, String memberID, String price, String time, String title) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.comment = comment;
        this.details = details;
        this.id = id;
        this.memberID = memberID;
        this.price = price;
        this.time = time;
        this.title = title;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}