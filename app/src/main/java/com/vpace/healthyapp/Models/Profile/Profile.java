
package com.vpace.healthyapp.Models.Profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vpace.healthyapp.Models.Profile.Progress;
import com.vpace.healthyapp.Models.Profile.User;
import com.vpace.healthyapp.Models.Profile.UserData;

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
    private Integer lostWeight;
    @SerializedName("remaining_weight")
    @Expose
    private Integer remainingWeight;
    @SerializedName("lost_fat")
    @Expose
    private Integer lostFat;
    @SerializedName("remaining_fat")
    @Expose
    private Integer remainingFat;
    @SerializedName("muscle_gain")
    @Expose
    private Integer muscleGain;
    @SerializedName("muscle_remaining")
    @Expose
    private Integer muscleRemaining;

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

    public Integer getLostWeight() {
        return lostWeight;
    }

    public void setLostWeight(Integer lostWeight) {
        this.lostWeight = lostWeight;
    }

    public Integer getRemainingWeight() {
        return remainingWeight;
    }

    public void setRemainingWeight(Integer remainingWeight) {
        this.remainingWeight = remainingWeight;
    }

    public Integer getLostFat() {
        return lostFat;
    }

    public void setLostFat(Integer lostFat) {
        this.lostFat = lostFat;
    }

    public Integer getRemainingFat() {
        return remainingFat;
    }

    public void setRemainingFat(Integer remainingFat) {
        this.remainingFat = remainingFat;
    }

    public Integer getMuscleGain() {
        return muscleGain;
    }

    public void setMuscleGain(Integer muscleGain) {
        this.muscleGain = muscleGain;
    }

    public Integer getMuscleRemaining() {
        return muscleRemaining;
    }

    public void setMuscleRemaining(Integer muscleRemaining) {
        this.muscleRemaining = muscleRemaining;
    }

}
