package com.example.servemr;

public class model {
    String category, order_date, description, order_time, name, date, time,location,tsk,id,uid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public model() {

        this.category = category;
        this.order_date = order_date;
        this.description = description;
        this.order_time = order_time;
        this.name = name;
        this.date = date;
        this.time = time;
        this.id=id;
        this.uid=uid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getTsk() {
        return tsk;
    }

    public void setTsk(String tsk) {
        this.tsk = tsk;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

