package com.example.servemr;

public class model_history {
    String c_id, cost, taskid, time, ven_id, vname;


    public String getCid() {
        return c_id;
    }

    public void setCid(String cid) {
        this.c_id = cid;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVen_id() {
        return ven_id;
    }

    public void setVen_id(String ven_id) {
        this.ven_id = ven_id;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public model_history() {
        this.c_id =c_id;
        this.cost=cost;
        this.taskid=taskid;
        this.time=time;
        this.ven_id=ven_id;
        this.vname=vname;
    }

    }

