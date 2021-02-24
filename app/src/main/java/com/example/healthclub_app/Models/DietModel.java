package com.example.healthclub_app.Models;

public class DietModel {

    String title,text1;
  //  private boolean isExpanded;

//    public boolean isExpanded() {
//        return isExpanded;
//    }
///
//    public void setExpanded(boolean expanded) {
//        isExpanded = expanded;
//    }


    public DietModel(String title, String text1) {
        this.title = title;
        this.text1 = text1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }
}
