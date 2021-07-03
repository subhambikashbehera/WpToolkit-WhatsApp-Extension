package com.EDUSY.whatswebnoads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Textrepeater extends AppCompatActivity {

    EditText input_text,input_number,output_text;
    CheckBox checkBox;
    Button reset,generate;
    String text="";
    ImageButton copy;
    ImageButton share;
    ImageButton whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textrepeater);
        input_text=findViewById(R.id.input_text);
        input_number=findViewById(R.id.input_number);
        output_text=findViewById(R.id.output_text);
        checkBox=findViewById(R.id.checkBox);
        reset=findViewById(R.id.reset);
        generate=findViewById(R.id.generate);
        copy=findViewById(R.id.copy);
        share=findViewById(R.id.share);
        whatsapp=findViewById(R.id.whatsapp);
        getSupportActionBar().hide();


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String text1=input_text.getText().toString().trim();
                String number2=input_number.getText().toString().trim();

                if (TextUtils.isEmpty( number2) && TextUtils.isEmpty(text1))
                {
                    input_text.setError("Please Enter the Text Input");
                    input_number.setError("Please Enter The Limit");
                    return;
                }
                if (TextUtils.isEmpty( number2))
                {
                    input_number.setError("Please Enter The Limit");
                    return;
                }
                if (TextUtils.isEmpty(text1))
                {
                    input_text.setError("Please Enter the Text Input");
                    return;
                }


                String text2=input_text.getText().toString();
                String tempno=input_number.getText().toString();



                if (checkBox.isChecked())
                {

                    if (!tempno.equals(""))
                    {
                        text="";
                        int number= Integer.parseInt(input_number.getText().toString());

                        for (int i=0;i<number;i++)
                        {
                            text=text+text2+"\n";

                        }

                        output_text.setText(text);

                    }

                }
                else
                {

                    if (!tempno.equals(""))
                    {
                        text="";
                        int number= Integer.parseInt(input_number.getText().toString());

                        for (int i=0;i<number;i++)
                        {
                            text=text+text2;

                        }

                        output_text.setText(text);

                    }


                }

            }
        });




        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data=ClipData.newPlainText("EditText",text);
                clipboardManager.setPrimaryClip(data);
                Toast.makeText(Textrepeater.this,"Copied",Toast.LENGTH_SHORT).show();


            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Textrepeater.this,"wait",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,text);
                startActivity(Intent.createChooser(intent,"share"));

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
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?text="+text));
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Textrepeater.this,"Official Whatsapp is not installed",Toast.LENGTH_SHORT).show();
                }





            }
        });



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                output_text.setText("");

            }
        });




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