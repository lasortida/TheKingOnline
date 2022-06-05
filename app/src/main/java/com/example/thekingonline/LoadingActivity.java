package com.example.thekingonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thekingonline.GameClasses.Game;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class LoadingActivity extends AppCompatActivity {

    String idOfRoom;
    int userCode;
    int countryId;

    Retrofit retrofit;
    GameService service;
    TextView view;
    TextView timer;
    TextView advice;
    int seconds = 10000;
    int value;

    String[] advices = {
            "Совет: Будьте внимательны, когда обрабатываете обращения! От этого зависит положение нашей страны!",
            "Правило: Сразу после создания альянса, вы не можете отправлять приглашения на вступление, мировое сообщество должно утвердить ваш альянс!",
            "Правило: Устраивать обмен можно только 1 раз в игровую неделю!",
            "Совет: Сделайте хорошее описание вашему альянсу, чтобы в него хотелось вступить!",
            "Совет: Старайтесь держать уровни (Деньги, Армия, Экономика, Промышленность, Еда) в средней позиции! (Не много и не мало)",
            "Совет: Старайтесь нажимать кнопку завершения недели до исхода времени, иначе ваши жители будут вами недовольны!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Bundle args = getIntent().getExtras();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        view = findViewById(R.id.textViewTitle);
        timer = findViewById(R.id.textViewTimer);
        advice = findViewById(R.id.textViewAdvice);
        view.setText("Поиск сервера");
        value = (int) (Math.random() * advices.length);
        advice.setText(advices[value]);

        new CountDownTimer(seconds, 1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                int index = (int) (Math.random() * advices.length);
                while (index == value){
                    index = (int) (Math.random() * advices.length);
                }
                value = index;
                advice.setText(advices[index]);
                start();
            }
        }.start();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(GameService.address)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GameService.class);

    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try{
                Call<InitialData> call = service.getIDOfRoom();
                InitialData data = call.execute().body();
                idOfRoom = data.idOfRoom;
                call = service.getUserCode(idOfRoom);
                data = call.execute().body();
                if (data.error) {
                    Log.d("errror", "Error in service");
                    interrupt();
                }
                userCode = data.userCode;
                while (true){
                    call = service.getTimeReminder(idOfRoom, userCode);
                    data = call.execute().body();
                    while (!data.isTimerStarted){
                        Thread.sleep(2000);
                        call = service.getTimeReminder(idOfRoom, userCode);
                        data = call.execute().body();
                    }
                    while (data.time > 0 && data.isTimerStarted){
                        setTextToTimer(data.time);
                        Thread.sleep(900);
                    }
                    if (data.time == 0 && data.isTimerStarted){
                        call = service.getCountryId(idOfRoom, userCode);
                        data = call.execute().body();
                        if (data.start){
                            countryId = data.countryId;
                            Game game = new Game(countryId);
                            startActivity(new Intent(LoadingActivity.this, GovernMenuActivity.class).putExtra("GAME", game));
                            finish();
                        }
                    }
                }
            } catch (Exception e){
                Log.d("error", e.getLocalizedMessage());
            }
        }
    };

    public void setTextToTimer(int seconds){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setVisibility(View.VISIBLE);
                timer.setText("Осталось: " + seconds);
            }
        });
    }



    class InitialData {
        String idOfRoom;
        int userCode;
        boolean error;
        boolean start;
        int time;
        boolean isTimerStarted;
        int countryId;
    }
}