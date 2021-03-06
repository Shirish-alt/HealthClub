package com.vpace.healthyapp.Models.Profile;

import com.google.gson.annotations.SerializedName;

public class Progress {

    @SerializedName("weight")
    String weight;

    @SerializedName("vfat")
    String vfat;

    @SerializedName("tsf")
    String tsf;

    @SerializedName("totalfatpercent")
    String totalfatpercent;

    @SerializedName("metabolicage")
    String metabolicage;

    @SerializedName("bmi")
    String bmi;

    @SerializedName("bmr")
    String bmr;

    @SerializedName("musclepercent")
    String musclepercent;
    @SerializedName("type")
    String weektype;

    @SerializedName("created_date")
    String created_date;
    @SerializedName("edate")
    String edate;

    public Progress(String weight, String vfat, String tsf, String totalfatpercent, String metabolicage, String bmi, String bmr, String musclepercent, String weektype, String created_date, String edate) {
        this.weight = weight;
        this.vfat = vfat;
        this.tsf = tsf;
        this.totalfatpercent = totalfatpercent;
        this.metabolicage = metabolicage;
        this.bmi = bmi;
        this.bmr = bmr;
        this.musclepercent = musclepercent;
        this.weektype = weektype;
        this.created_date = created_date;
        this.edate = edate;
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

    public String getMetabolicage() {
        return metabolicage;
    }

    public void setMetabolicage(String metabolicage) {
        this.metabolicage = metabolicage;
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

    public String getWeektype() {
        return weektype;
    }

    public void setWeektype(String weektype) {
        this.weektype = weektype;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getEdate() {
        return edate;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
    public void setEdate(String edate) {
        this.edate = edate;
    }
}
