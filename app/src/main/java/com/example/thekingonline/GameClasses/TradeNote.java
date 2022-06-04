package com.example.thekingonline.GameClasses;

public class TradeNote extends Note{
    public int stateToMe;
    public int stateAway;

    public TradeNote(int stateToMe, int stateAway, String text, int idOfSender){
        super(idOfSender, text);
        this.stateToMe = stateToMe;
        this.stateAway = stateAway;
    }
}
