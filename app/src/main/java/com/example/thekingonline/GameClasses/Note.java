package com.example.thekingonline.GameClasses;

import java.io.Serializable;

public class Note implements Serializable {
    public int idOfSender;
    public String text;
    public boolean isAccepted;

    public Note(int idOfSender, String text){
        this.idOfSender = idOfSender;
        this.text = text;
    }
}
