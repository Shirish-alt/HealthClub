package com.vpace.healthyapp.Models;

public class TableModel {
    String id,user_id,weight,vfat,tsf,totalfatpercent,bmi,bmr,musclepercent,type,date;

    public TableModel(String id, String user_id, String weight, String vfat, String tsf, String totalfatpercent, String bmi, String bmr, String musclepercent, String type,String date) {
        this.id = id;
        this.user_id = user_id;
        this.weight = weight;
        this.vfat = vfat;
        this.tsf = tsf;
        this.totalfatpercent = totalfatpercent;
        this.bmi = bmi;
        this.bmr = bmr;
        this.musclepercent = musclepercent;
        this.type = type;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVfat() {
        return vfat;
    }

    public void setVfat(String vfat) {
        this.vfat = vfat;
    }

    public String getTsf() {
        return tsf;
    }

    public void setTsf(String tsf) {
        this.tsf = tsf;
    }

    public String getTotalfatpercent() {
        return totalfatpercent;
    }

    public void setTotalfatpercent(String totalfatpercent) {
        this.totalfatpercent = totalfatpercent;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBmr() {
        return bmr;
    }

    public void setBmr(String bmr) {
        this.bmr = bmr;
    }

    public String getMusclepercent() {
        return musclepercent;
    }

    public void setMusclepercent(String musclepercent) {
        this.musclepercent = musclepercent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
