
package com.vpace.healthyapp.Models.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class First {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("edate")
    @Expose
    private String edate;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("vfat")
    @Expose
    private String vfat;
    @SerializedName("tsf")
    @Expose
    private String tsf;
    @SerializedName("totalfatpercent")
    @Expose
    private String totalfatpercent;
    @SerializedName("metabolicage")
    @Expose
    private String metabolicage;
    @SerializedName("bmi")
    @Expose
    private String bmi;
    @SerializedName("bmr")
    @Expose
    private String bmr;
    @SerializedName("musclepercent")
    @Expose
    private String musclepercent;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
