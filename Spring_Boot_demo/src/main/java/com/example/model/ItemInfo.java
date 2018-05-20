package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ItemInfo {
    @Id
    @GeneratedValue
    private Integer itemid;

    @Column(nullable = false, length = 50)
    private String itemname;
    @Column(nullable = false, length = 50)
    private String itemtime;
    @Column(nullable = false)
    private Integer enrolment = 0;
    @Column(nullable = false)
    private Integer follownumber = 0;
    @Column(nullable = false, length = 50)
    private String releasetime;
    @Column(nullable = false, length = 50)
    private String hostname;
    @Column(nullable = false, length = 1)
    private Integer itemlabel = 1;
    @Column(nullable = false, length = 20)
    private String callnumber;
    @Column(nullable = false, length = 100)
    private Integer money = 0;
    @Column(nullable = false)
    private String address;
//    @Column(nullable = false, length = 100)
    private String details;


    public Integer getItemid() {
        return itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemtime() {
        return itemtime;
    }

    public Integer getEnrolment() {
        return enrolment;
    }

    public Integer getFollownumber() {
        return follownumber;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public String getHostname() {
        return hostname;
    }

    public Integer getItemlabel() {
        return itemlabel;
    }

    public String getCallnumber() {
        return callnumber;
    }

    public String getDetails() {
        return details;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setItemtime(String itemtime) {
        this.itemtime = itemtime;
    }

    public void setEnrolment(Integer enrolment) {
        this.enrolment = enrolment;
    }

    public void setFollownumber(Integer follownumber) {
        this.follownumber = follownumber;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setItemlabel(Integer itemlabel) {
        this.itemlabel = itemlabel;
    }

    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
