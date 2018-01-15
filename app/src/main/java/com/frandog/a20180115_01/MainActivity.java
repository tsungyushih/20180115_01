package com.frandog.a20180115_01;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;     //在模擬器的右下三個點的按鈕中/Cellular/Network type的設定可以模擬網路lag
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.progressBar);
        img = findViewById(R.id.imageView);
    }
    public void click1(View v)
    {
        pb.setVisibility(View.VISIBLE);     //點擊出現progressBar
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        ImageRequest request = new ImageRequest("https://5.imimg.com/data5/UH/ND/MY-4431270/red-rose-flower-500x500.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                        pb.setVisibility(View.INVISIBLE);        //跑完圖就隱藏progressBar
                        img.setVisibility(View.VISIBLE);         //跑完圖就秀圖
                    }
                },
                0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        queue.start();
    }
    public void click2 (View v)
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//        因中文編碼會出錯，所以要自己寫Request(UTF8StringRequest)，繼承StringRequest
//        StringRequest request = new StringRequest("https://www.mobile01.com/rss/news.xml",
        StringRequest request = new UTF8StringRequest("https://www.mobile01.com/rss/news.xml",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        queue.start();
    }
}
