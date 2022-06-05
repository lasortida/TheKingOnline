package com.example.thekingonline.GameClasses;

import java.io.Serializable;

public class AllianceNote extends Note implements Serializable {
    public int idOfAlliance;

    public AllianceNote(int idOfAlliance, String text, int idOfSender){
        super(idOfSender, text);
        this.idOfAlliance = idOfAlliance;
    }
}
