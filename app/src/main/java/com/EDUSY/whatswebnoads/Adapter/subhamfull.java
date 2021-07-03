package com.EDUSY.whatswebnoads.Adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.EDUSY.whatswebnoads.R;

import java.net.URI;

public class subhamfull extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_subhamfull);
    imageView=findViewById(R.id.xyz);
        Uri uri=Uri.parse(getIntent().getStringExtra("image"));
        imageView.setImageURI(uri);




    }
}