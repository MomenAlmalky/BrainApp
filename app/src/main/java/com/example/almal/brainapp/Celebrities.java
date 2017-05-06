package com.example.almal.brainapp;

import android.content.Context;
import android.content.Intent;
import android.database.MergeCursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Celebrities extends AppCompatActivity {
    ArrayList<String> names = new ArrayList();
    ArrayList<String> images = new ArrayList();
    ImageView imageView ;
    int index;
    RequestQueue requestQueue;
    ArrayList<Button> b = new ArrayList<>() ;
    public static Intent getIntent(Context context){
        return new Intent(context,Celebrities.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities);
        imageView = (ImageView) findViewById(R.id.image_celebrities);
        b.add((Button) findViewById(R.id.button_celebrities1));
        b.add((Button) findViewById(R.id.button_celebrities2));
        b.add((Button) findViewById(R.id.button_celebrities3));
        b.add((Button) findViewById(R.id.button_celebrities4));


        requestQueue = Volley.newRequestQueue(this);
        String url = "http://www.posh24.se/kandisar";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i("hellooo",response);
                String [] splitresult = response.split("<div class=\"sidebarContainer\">");

                Pattern p = Pattern.compile("img src=\"(.*?)\"");
                Matcher m = p.matcher(splitresult[0]);

                while (m.find()){
                    images.add(m.group(1));
                    System.out.println(m.group(1));
                }

                p = Pattern.compile("alt=\"(.*?)\"");
                m = p.matcher(splitresult[0]);

                while (m.find()){
                    names.add(m.group(1));
                    System.out.println(m.group(1));
                }

                getCorrectImageAndName(requestQueue,names.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
        Log.i("hellooo", (String.valueOf(names.size())));

    }

    public void getCorrectImageAndName(RequestQueue requestQueue,int max){

        Random r = new Random();
        index = r.nextInt(max);
        int x = r.nextInt(3);
        ImageRequest imageRequest = new ImageRequest(images.get(index), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);

            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(imageRequest);
        b.get(x).setText(names.get(index));
        int j=0;
        for(int w=0;w<4;w++){
            if(w==x){
                continue;
            }

            Log.i("helloo",String.valueOf(w));
            j = r.nextInt(max);
            while(j==index){
                j = r.nextInt(max);
            }
            b.get(w).setText(names.get(j));
        }

    }

    public void checkCelebrities(View view) {
        Button b = (Button)view;
        if(names.get(index)== b.getText()){
            Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_SHORT).show();
            getCorrectImageAndName(requestQueue,names.size());
        }else {
            Toast.makeText(getApplicationContext(),"Wrong!!! it was: "+names.get(index),Toast.LENGTH_SHORT).show();
            getCorrectImageAndName(requestQueue,names.size());
        }
    }
}
