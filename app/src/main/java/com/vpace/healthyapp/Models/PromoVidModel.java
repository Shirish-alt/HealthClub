package com.vpace.healthyapp.Models;

public class PromoVidModel {
    String link,titie;


    public PromoVidModel(String link, String titie) {
        this.link = link;
        this.titie = titie;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitie() {
        return titie;
    }

    public void setTitie(String titie) {
        this.titie = titie;
    }
}
