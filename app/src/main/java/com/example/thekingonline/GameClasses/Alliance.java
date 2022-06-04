package com.example.thekingonline.GameClasses;

import java.util.ArrayList;

public class Alliance {
    public String name;
    public int idOfOwner;
    public String description;
    public int idOfLogo;
    ArrayList<Integer> countryIds;

    public Alliance(String name, int idOfOwner, String description, int idOfLogo){
        this.name = name;
        this.idOfOwner = idOfOwner;
        this.description = description;
        this.idOfLogo = idOfLogo;
        countryIds = new ArrayList<>();
    }
}
