package com.example.thekingonline.GameClasses;

import java.io.Serializable;

public class Week implements Serializable {
    private Act[] acts = new Act[3];

    public Week(Storage storage){
        for(int i = 0; i < 3; ++i){
            acts[i] = storage.getRandomAct();
        }
    }

    public Act[] getActs(){
        return acts;
    }
}
