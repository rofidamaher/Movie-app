package com.example.mymovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView txt_detail,txt_titel;
    ImageView img_poster;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_titel=findViewById(R.id.txt_titel);
        txt_detail=findViewById(R.id.txt_detail);
        img_poster=findViewById(R.id.img_poster);

        intent=getIntent();
        final String detail=intent.getStringExtra("detail");
        final String titel=intent.getStringExtra("titel");
        final String poster=intent.getStringExtra("poster");
        txt_titel.setText(titel);
        txt_detail.setText(detail);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185"+
                poster).into(img_poster);

    }
}
