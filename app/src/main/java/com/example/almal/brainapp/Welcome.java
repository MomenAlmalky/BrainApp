package com.example.almal.brainapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context,Welcome.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void ReadyButton(View view) {
        startActivity(Calculate.getStartIntent(getApplicationContext()));
    }

    public void celebirties(View view) {
        startActivity(Celebrities.getIntent(this));
    }
}
