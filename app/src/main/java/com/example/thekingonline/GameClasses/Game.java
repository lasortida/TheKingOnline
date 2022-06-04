package com.example.thekingonline.GameClasses;

import java.util.ArrayList;

public class Game {
    public int numberOfWeek;
    public Service service;
    public Country[] countries;
    public ArrayList<Alliance> alliances;
    public Storage storage;
    public Week week;

    public Game(int countryId){
        service = new Service();
        service.countryId = countryId;
        numberOfWeek = 1;
        storage = new Storage();
        countries = storage.getDefaultCountries();
        alliances = new ArrayList<>();
    }
}
