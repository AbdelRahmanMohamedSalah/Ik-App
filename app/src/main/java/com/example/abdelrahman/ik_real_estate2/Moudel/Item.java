package com.example.abdelrahman.ik_real_estate2.Moudel;

import java.io.Serializable;

public class Item implements Serializable {


    public String bathRoom;
    public String code;
    public String details;
    public String id;
    public String level;
    public String location;
    public String member_id;
    public String member_name;
    public String option;
    public String owner_name;
    public String owner_phone;
    public String price;
    public String room;
    public String space;
    public String state;
    public String time;
    public String title;
    public String type;
    public Users users;

    public Item() {
    }

    public Item(String bathRoom, String code, String details, String id, String level, String location, String member_id, String member_name, String option, String owner_name, String owner_phone, String price, String room, String space, String state, String time, String title, String type, Users users) {
        this.bathRoom = bathRoom;
        this.code = code;
        this.details = details;
        this.id = id;
        this.level = level;
        this.location = location;
        this.member_id = member_id;
        this.member_name = member_name;
        this.option = option;
        this.owner_name = owner_name;
        this.owner_phone = owner_phone;
        this.price = price;
        this.room = room;
        this.space = space;
        this.state = state;
        this.time = time;
        this.title = title;
        this.type = type;
        this.users = users;
    }

    public String getBathRoom() {
        return bathRoom;
    }

    public void setBathRoom(String bathRoom) {
        this.bathRoom = bathRoom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Item{" +
                "bathRoom='" + bathRoom + '\'' +
                ", code='" + code + '\'' +
                ", details='" + details + '\'' +
                ", id='" + id + '\'' +
                ", level='" + level + '\'' +
                ", location='" + location + '\'' +
                ", member_id='" + member_id + '\'' +
                ", member_name='" + member_name + '\'' +
                ", option='" + option + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", owner_phone='" + owner_phone + '\'' +
                ", price='" + price + '\'' +
                ", room='" + room + '\'' +
                ", space='" + space + '\'' +
                ", state='" + state + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", users=" + users +
                '}';
    }
}
