package com.EDUSY.whatswebnoads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Directmessage extends AppCompatActivity {
    EditText phone,message;
    Button send;
    ImageView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directmessage);
        getSupportActionBar().hide();
        phone=findViewById(R.id.phone);
        send=findViewById(R.id.send);
        message=findViewById(R.id.hello);
        cancel=findViewById(R.id.cancel);
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() !=0)
                {
                    cancel.setVisibility(View.VISIBLE);
                }
                else {
                    cancel.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>11)
                {
                    phone.setError("Invalid number ,number must be 10 digits");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message.setText("");
                cancel.setVisibility(View.GONE);

            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile23=phone.getText().toString().trim();
                String message23=message.getText().toString().trim();

                if (TextUtils.isEmpty(mobile23))
                {
                    phone.setError("Enter the Number");
                    return;
                }
                if (TextUtils.isEmpty(message23))
                {
                    message.setError("Can't send Empty message");
                }

                String mobile=phone.getText().toString();
                String message2=message.getText().toString();

                boolean installed=appinstalledornot("com.whatsapp");
                boolean installed2=appinstalledornot("com.whatsapp.w4b");
                if (installed)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+mobile+"&text="+message2));
                    startActivity(intent);

                }

                else if (installed2)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+mobile+"&text="+message2));
                    startActivity(intent);

                }
                else {

                    Toast.makeText(Directmessage.this,"Official whatsapp is not installed in your phone",Toast.LENGTH_SHORT).show();
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

        }catch (PackageManager.NameNotFoundException e)
        {
            app_installed=false;
        }

        return app_installed;

    }


}