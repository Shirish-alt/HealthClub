package com.vpace.healthyapp.Models.Profile;

import com.google.gson.annotations.SerializedName;

public class Overview {

    @SerializedName("weight_loss")
    String weight_loss;
    @SerializedName("weight_loss_remaining")
    String weight_loss_remaining;
    @SerializedName("fat_loss")
    String fat_loss;
    @SerializedName("fat_loss_remaining")
    String fat_loss_remaining;
    @SerializedName("muscle_gain")
    String muscle_gain;

    @SerializedName("muscle_gain_remaining")
    String muscle_gain_remaining;

    public Overview(String weight_loss, String weight_loss_remaining, String fat_loss, String fat_loss_remaining, String muscle_gain, String muscle_gain_remaining) {
        this.weight_loss = weight_loss;
        this.weight_loss_remaining = weight_loss_remaining;
        this.fat_loss = fat_loss;
        this.fat_loss_remaining = fat_loss_remaining;
        this.muscle_gain = muscle_gain;
        this.muscle_gain_remaining = muscle_gain_remaining;
    }

    public String getWeight_loss() {
        return weight_loss;
    }

    public void setWeight_loss(String weight_loss) {
        this.weight_loss = weight_loss;
    }

    public String getWeight_loss_remaining() {
        return weight_loss_remaining;
    }

    public void setWeight_loss_remaining(String weight_loss_remaining) {
        this.weight_loss_remaining = weight_loss_remaining;
    }

    public String getFat_loss() {
        return fat_loss;
    }

    public void setFat_loss(String fat_loss) {
        this.fat_loss = fat_loss;
    }

    public String getFat_loss_remaining() {
        return fat_loss_remaining;
    }

    public void setFat_loss_remaining(String fat_loss_remaining) {
        this.fat_loss_remaining = fat_loss_remaining;
    }

    public String getMuscle_gain() {
        return muscle_gain;
    }

    public void setMuscle_gain(String muscle_gain) {
        this.muscle_gain = muscle_gain;
    }

    public String getMuscle_gain_remaining() {
        return muscle_gain_remaining;
    }

    public void setMuscle_gain_remaining(String muscle_gain_remaining) {
        this.muscle_gain_remaining = muscle_gain_remaining;
    }
}
