package com.example.laurents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategory extends AppCompatActivity {

    private ImageView dresses,glasses,headset,laptops;
    private ImageView coat, purses,hat,mobilephone;
    private ImageView tshirt,pull,watch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        dresses = (ImageView) findViewById(R.id.dresses);
        glasses = (ImageView) findViewById(R.id.glasses);
        headset = (ImageView) findViewById(R.id.headphones);
        laptops = (ImageView) findViewById(R.id.laptops);
        coat = (ImageView) findViewById(R.id.sweather);
        purses = (ImageView) findViewById(R.id.bags);
        hat = (ImageView) findViewById(R.id.hats);
        mobilephone = (ImageView) findViewById(R.id.mobiles);
        tshirt = (ImageView) findViewById(R.id.tshirt);
        pull = (ImageView) findViewById(R.id.headphones_);
        watch = (ImageView) findViewById(R.id.watch);

        dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("dresses") ;
            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("glasses") ;
            }
        });
        headset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("headset") ;
            }
        });
        coat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("coat") ;
            }
        });
        purses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("purses") ;
            }
        });
        hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("hat") ;
            }
        });
        mobilephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("mobilephones") ;
            }
        });
        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("tshirt") ;
            }
        });
        pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("pull") ;
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory("watch") ;
            }
        });
    }

    private void startCategory(String category) {
        Intent intent = new Intent(AdminCategory.this,AdminAddNewProduct.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }


}