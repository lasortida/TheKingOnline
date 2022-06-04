package com.example.thekingonline.GameClasses;

public class Country {
    public String name;
    public int id;
    public int idOfIcon;
    public int idOfMarker;

    public float[] stats;

    public int relationshipValue;

    public int idOfAlliance;

    public Country(int id, String name, int idOfIcon, int idOfMarker){
        this.id = id;
        this.name = name;
        this.idOfIcon = idOfIcon;
        this.idOfMarker = idOfMarker;
        stats = new float[5];
        for (int i = 0; i < stats.length; ++i){
            stats[i] = 0.5f;
        }
        idOfAlliance = -1;
        relationshipValue = 50;
    }
}
