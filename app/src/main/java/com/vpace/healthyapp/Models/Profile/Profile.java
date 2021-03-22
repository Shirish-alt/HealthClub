
package com.vpace.healthyapp.Models.Profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("user_data")
    @Expose
    private UserData userData;
    @SerializedName("progress")
    @Expose
    private List<Progress> progress = null;
    @SerializedName("first")
    @Expose
    private First first;
    @SerializedName("last")
    @Expose
    private Last last;
    @SerializedName("ideal_weight")
    @Expose
    private String idealWeight;
    @SerializedName("lost_weight")
    @Expose
    private String lostWeight;
    @SerializedName("remaining_weight")
    @Expose
    private String remainingWeight;
    @SerializedName("lost_fat")
    @Expose
    private String lostFat;
    @SerializedName("remaining_fat")
    @Expose
    private String remainingFat;
    @SerializedName("muscle_gain")
    @Expose
    private String muscleGain;
    @SerializedName("muscle_remaining")
    @Expose
    private String muscleRemaining;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<Progress> getProgress() {
        return progress;
    }

    public void setProgress(List<Progress> progress) {
        this.progress = progress;
    }

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Last getLast() {
        return last;
    }

    public void setLast(Last last) {
        this.last = last;
    }

    public String getIdealWeight() {
        return idealWeight;
    }

    public void setIdealWeight(String idealWeight) {
        this.idealWeight = idealWeight;
    }

    public String getLostWeight() {
        return lostWeight;
    }

    public void setLostWeight(String lostWeight) {
        this.lostWeight = lostWeight;
    }

    public String getRemainingWeight() {
        return remainingWeight;
    }

    public void setRemainingWeight(String remainingWeight) {
        this.remainingWeight = remainingWeight;
    }

    public String getLostFat() {
        return lostFat;
    }

    public void setLostFat(String lostFat) {
        this.lostFat = lostFat;
    }

    public String getRemainingFat() {
        return remainingFat;
    }

    public void setRemainingFat(String remainingFat) {
        this.remainingFat = remainingFat;
    }

    public String getMuscleGain() {
        return muscleGain;
    }

    public void setMuscleGain(String muscleGain) {
        this.muscleGain = muscleGain;
    }

    public String getMuscleRemaining() {
        return muscleRemaining;
    }

    public void setMuscleRemaining(String muscleRemaining) {
        this.muscleRemaining = muscleRemaining;
    }

}
