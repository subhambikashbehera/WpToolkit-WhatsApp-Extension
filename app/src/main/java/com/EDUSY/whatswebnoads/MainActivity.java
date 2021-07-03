package com.EDUSY.whatswebnoads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.EDUSY.whatswebnoads.Adapter.Privencypolicies;

public class MainActivity extends AppCompatActivity {




    RelativeLayout rootlayout;

    CardView whatsappweb,directmessage,qrcode,textrepeater,status,whatsappdirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        assign();
        AnimationDrawable animationDrawable=(AnimationDrawable)rootlayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3500);
        animationDrawable.start();
        clicklistener();
    }


    private void clicklistener() {

        whatsappweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Whatsappweb.class);
                startActivity(intent);
                finish();
            }
        });

        directmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Directmessage.class);
                startActivity(intent);
            }
        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Scan.class);
                startActivity(intent);
            }
        });
        textrepeater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Textrepeater.class);
                startActivity(intent);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Status.class);
                startActivity(intent);
            }
        });
        whatsappdirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MainActivity.this,MultiSocialmedia.class);
                startActivity(intent);
                finish();
                

            }
        });

    }



    private void assign() {
        rootlayout=findViewById(R.id.rootlayout);
        whatsappweb=findViewById(R.id.whatsweb);
        directmessage=findViewById(R.id.directmessage);
        qrcode=findViewById(R.id.scan);
        textrepeater=findViewById(R.id.textrepeater);
        status=findViewById(R.id.status);
        whatsappdirect=findViewById(R.id.whatsappdirect);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.share:
            case R.id.share2:
                shareme();
                break;
            case R.id.privacy:
                Intent intent=new Intent(MainActivity.this, Privencypolicies.class);
                startActivity(intent);
                break;
            case R.id.terms:
                Intent intent1=new Intent(MainActivity.this, Termsandcondition.class);
                startActivity(intent1);
                break;
            case R.id.bugreport:

                Intent intent2=new Intent(MainActivity.this, bug_report.class);
                startActivity(intent2);

                break;

            default:
                break;

        }
        return true;
    }

    public  void shareme()
    {
        try {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Whats_Web[no ads]");
//            String message="https://play.google.com/store/apps/details?id=\"+BuildConfig.APPLICATION_ID+\"\\n";
            String message="it will available soon";
            intent.putExtra(Intent.EXTRA_TEXT,message);
            startActivity(Intent.createChooser(intent,"share by"));
            Toast.makeText(MainActivity.this,"Sharing",Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"error occured",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertd=new AlertDialog.Builder(this);
        alertd.setTitle("Exit?");
        alertd.setMessage("Are You Sure Want to Exit ?");
        alertd.setIcon(R.drawable.ic_baseline_error_24);

        alertd.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertd.create().show();

    }


}