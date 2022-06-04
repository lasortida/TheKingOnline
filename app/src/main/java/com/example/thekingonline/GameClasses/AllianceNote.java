package com.example.thekingonline.GameClasses;

public class AllianceNote extends Note{
    public int idOfAlliance;

    public AllianceNote(int idOfAlliance, String text, int idOfSender){
        super(idOfSender, text);
        this.idOfAlliance = idOfAlliance;
    }
}
