package com.example.almal.brainapp;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Calculate extends AppCompatActivity {
    TextView timeText,score,calc_eq,eq1,eq2,eq3,eq4,final_score;
    Button play_again_btn;
    int score_count,total_score = 0;
    int timeInSeconds = 30000;
    ArrayList<Integer> eq = new ArrayList();
    int j=0;
    ArrayList<TextView> text_eq = new ArrayList<>();

    public static Intent getStartIntent(Context context){
        return new Intent(context,Calculate.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        timeText = (TextView) findViewById(R.id.time_text);
        score = (TextView) findViewById(R.id.score_text);
        calc_eq = (TextView) findViewById(R.id.textViewCalc);
        text_eq.add((TextView) findViewById(R.id.eq1_text));
        text_eq.add((TextView) findViewById(R.id.eq2_text));
        text_eq.add((TextView) findViewById(R.id.eq3_text));
        text_eq.add((TextView) findViewById(R.id.eq4_text));
        final_score = (TextView) findViewById(R.id.final_score_text);
        play_again_btn = (Button) findViewById(R.id.play_again_btn);

        new CountDownTimer(timeInSeconds,1000){
            @Override
            public void onTick(long l) {
                String x = String.valueOf((int) (l / 1000));
                timeText.setText(x);
            }

            @Override
            public void onFinish() {
                for(int i=0;i<4;i++)
                text_eq.get(i).setClickable(false);


                final_score.setText("your score is " + score_count + "/" + total_score);
                final_score.setVisibility(View.VISIBLE);

                play_again_btn.setVisibility(View.VISIBLE);
            }
        }.start();
        start();

    }
    public void newEquation(){
        eq.clear();
        Random r = new Random();
        eq.add(r.nextInt(100 - 0) + 0);
        eq.add(r.nextInt(100 - 0) + 0);
        eq.add(r.nextInt(4 - 0) + 0);
        Log.i("randomnum",String.valueOf(eq.get(2)));

    }
    public void start(){
        newEquation();
        Random r = new Random();
        calc_eq.setText( eq.get(0)+ "+" + eq.get(1) + "=");
        for(int i= 0; i<4;i++){
            if(i==eq.get(2)) {
                text_eq.get(i).setText(String.valueOf(eq.get(0)+eq.get(1)));
                j=i;
                continue;
            }
            int w = r.nextInt(100 - 0) + 0;
            while (w == eq.get(0)+eq.get(1))
                w = r.nextInt(100 - 0) + 0;
            text_eq.get(i).setText(String.valueOf(r.nextInt(100 - 0) + 0));
        }
    }
    public void start(View view) {

        if(view.getId() == text_eq.get(j).getId()){
            score_count++;
            total_score++;
            final_score.setText("Right");
            final_score.setVisibility(View.VISIBLE);
            score.setText(score_count + "/"+ total_score);
        }
        else{
            total_score++;
            final_score.setText("Wrong");
            final_score.setVisibility(View.VISIBLE);
            score.setText(score_count + "/"+ total_score);
        }
        start();
    }

    public void playAgain(View view) {
        startActivity(this.getIntent());
        finish();
    }
}
