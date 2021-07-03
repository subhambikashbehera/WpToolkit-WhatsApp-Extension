package com.EDUSY.whatswebnoads;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class Scan extends AppCompatActivity {

    public static TextView result;
    Button scan,reset;
    ImageButton copy,share,whatsapp;
    String J="";
    public static RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        result=findViewById(R.id.result);
        scan=findViewById(R.id.scan);
        copy=findViewById(R.id.copy);
        share=findViewById(R.id.share);
        reset=findViewById(R.id.reset);
        rl=findViewById(R.id.rl);
        whatsapp=findViewById(R.id.whatsapp);

        getSupportActionBar().hide();

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(Scan.this)
                        .withPermissions(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {

                                if (report.areAllPermissionsGranted())
                                {

                                }
                                if (report.isAnyPermissionPermanentlyDenied())
                                {
                                    Toast.makeText(Scan.this,"Pemission is needed for this action",Toast.LENGTH_LONG).show();
                                }

                                Intent intent=new Intent(Scan.this,Scancode.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(Scan.this,"error ocured",Toast.LENGTH_LONG).show();
                    }
                }).onSameThread().check();
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=result.getText().toString();
                ClipboardManager clipboardManager=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data=ClipData.newPlainText("TextView",s);
                clipboardManager.setPrimaryClip(data);
                Toast.makeText(Scan.this,"copied",Toast.LENGTH_SHORT).show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=result.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,s);
                startActivity(Intent.createChooser(intent,"choose one to share"));

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(J);
                rl.setVisibility(View.GONE);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean installed=appinstalledornot("com.whatsapp");
                boolean installed1=appinstalledornot("com.whatsapp.w4b");

                if (installed||installed1)
                {   String s=result.getText().toString();
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?text="+s));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Scan.this,"Official Whatsapp is not installed",Toast.LENGTH_SHORT).show();
                }


            }
        });





    }

    private boolean appinstalledornot(String s) {
        PackageManager packageManager=getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(s,packageManager.GET_ACTIVITIES);
            app_installed=true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed=false;
        }
        return app_installed;
    }

}