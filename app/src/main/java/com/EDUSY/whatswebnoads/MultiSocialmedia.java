package com.EDUSY.whatswebnoads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.EDUSY.whatswebnoads.Multimedia.Instagram;
import com.EDUSY.whatswebnoads.Multimedia.Twitter;
import com.EDUSY.whatswebnoads.Multimedia.facebook;

public class MultiSocialmedia extends AppCompatActivity {

    CardView instagram,facebook,whatsapp,twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_socialmedia);
        facebook=findViewById(R.id.user1);
        instagram=findViewById(R.id.user2);
        whatsapp=findViewById(R.id.user3);
        twitter=findViewById(R.id.user4);


        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MultiSocialmedia.this, Instagram.class);
                startActivity(intent);
                finish();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MultiSocialmedia.this,facebook.class);
                startActivity(intent);
                finish();
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean installed=appintalledornot("com.whatsapp");
                boolean installed2=appintalledornot("com.whatsapp.w4b");

                if (installed || installed2)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+91));
                    startActivity(intent);
                    Toast.makeText(MultiSocialmedia.this,"Click Ok To Continue",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MultiSocialmedia.this,"Official Whatsapp is not installed",Toast.LENGTH_SHORT).show();
                }
            }
        });


        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MultiSocialmedia.this, Twitter.class);
                startActivity(intent);
                finish();
            }
        });




    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(MultiSocialmedia.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean appintalledornot(String s) {

        PackageManager packageManager=getPackageManager();
        boolean app_installed;

        try {

            packageManager.getPackageInfo(s,packageManager.GET_ACTIVITIES);
            app_installed=true;

        }catch (PackageManager.NameNotFoundException e)
        {
            app_installed=false;
        }

        return app_installed;

    }

}