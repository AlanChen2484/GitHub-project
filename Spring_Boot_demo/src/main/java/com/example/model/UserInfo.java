package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    private Integer userid;

    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = true, length = 11)
    private String phone;
    @Column(nullable = true, length = 50)
    private String email;
    @Column(nullable = true, length = 1)
    private Integer sex;
    @Column(nullable = true, length = 50)
    private String city;
    @Column(nullable = true, length = 50)
    private String remarks;
    @Column(nullable = true, length = 100)
    private String imagine;
    @Column(nullable = true, length = 50)
    private String name;
    @Column(nullable = true, length = 30)
    private String idnumber;
    @Column(nullable = true, length = 30)
    private String qqnumber;
    @Column(nullable = true, length = 50)
    private String collection_id;
    @Column(nullable = true, length = 50)
    private String partake_id;

    public Integer getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getSex() {
        return sex;
    }

    public String getCity() {
        return city;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getImagine() {
        return imagine;
    }

    public String getName() {
        return name;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getQqnumber() {
        return qqnumber;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public String getPartake_id() {
        return partake_id;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public void setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public void setPartake_id(String partake_id) {
        this.partake_id = partake_id;
    }
}
