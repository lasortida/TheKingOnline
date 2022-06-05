package com.example.thekingonline.GameClasses;

import java.io.Serializable;

public class Service implements Serializable {
    public int numberOfWeek;
    public String idOfRoom;
    public int userCode;
    public int countryId;
    public Country me;
    TradeNote[] tradeNotes;
    AllianceNote[] allianceNotes;
}
