package com.example.thekingonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thekingonline.GameClasses.Game;
import com.example.thekingonline.GameClasses.ImageWithParams;

public class GovernMenuActivity extends AppCompatActivity {

    Game game;

    ImageWithParams coin;
    ImageWithParams army;
    ImageWithParams business;
    ImageWithParams workers;
    ImageWithParams food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govern_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle args = getIntent().getExtras();
        game = (Game) args.get("GAME");

        ImageView coinImage = findViewById(R.id.imageViewCoin);
        ImageView armyImage = findViewById(R.id.imageViewBomb);
        ImageView businessImage = findViewById(R.id.imageViewTie);
        ImageView workersImage = findViewById(R.id.imageViewAnvil);
        ImageView foodImage = findViewById(R.id.imageViewBread);
        coin = new ImageWithParams(coinImage, 0);
        army = new ImageWithParams(armyImage, 1);
        business = new ImageWithParams(businessImage, 2);
        workers = new ImageWithParams(workersImage, 3);
        food = new ImageWithParams(foodImage, 4);

        setImages();

        TextView week = findViewById(R.id.textViewWeek);
        week.setText("Неделя: " + game.numberOfWeek);

        Button buttonJob = findViewById(R.id.buttonTasks);
        buttonJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovernMenuActivity.this, JobActivity.class));
            }
        });


    }

    void setImages(){
        coin.setNewImage(game.countries[game.service.countryId].stats[0]);
        army.setNewImage(game.countries[game.service.countryId].stats[1]);
        business.setNewImage(game.countries[game.service.countryId].stats[2]);
        workers.setNewImage(game.countries[game.service.countryId].stats[3]);
        food.setNewImage(game.countries[game.service.countryId].stats[4]);
    }
}