package com.example.asus.mylisttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView index, title, cont, detail;
    //private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        index = findViewById(R.id.detail_index);
        title = findViewById(R.id.detail_title);
        cont = findViewById(R.id.detail_cont);
        detail = findViewById(R.id.detail_detail);

        //img = findViewById(R.id.detail_img);


        Intent intent = getIntent();
        index.setText(intent.getStringExtra("index"));
        title.setText(intent.getStringExtra("title"));
        cont.setText(intent.getStringExtra("cont"));
        detail.setText(intent.getStringExtra("detail"));

        //img.setText(intent.getStringExtra("img"));
        //img.setImageBitmap(intent.getAction("img"));

    }//onCreate
}//DetailActivity
