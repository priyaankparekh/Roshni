package com.example.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.emergency.EmergencyNumber;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectMenu extends AppCompatActivity {

    Button odbutton, cdbutton, ltrbutton, pdfbutton, emgbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        odbutton = (Button) findViewById(R.id.odbutton);
        cdbutton = (Button) findViewById(R.id.cdbutton);
        ltrbutton = (Button) findViewById(R.id.ltrbutton);
        pdfbutton = (Button) findViewById(R.id.pdfbutton);
        emgbutton = (Button) findViewById(R.id.emgbutton);

        odbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent objectDetection = new Intent(SelectMenu.this, DetectorActivity.class);
                startActivity(objectDetection);
            }
        });

        cdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent colorDetection = new Intent(SelectMenu.this, FullscreenActivity.class);
                startActivity(colorDetection);

            }
        });

        ltrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent liveTextReader = new Intent(SelectMenu.this, OcrCaptureActivity.class);
                startActivity(liveTextReader);
            }
        });

        pdfbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pdfReader = new Intent(SelectMenu.this, PdfMainActivity.class);
                startActivity(pdfReader);
            }
        });

        emgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emgcall = new Intent(SelectMenu.this, EmergencyCall.class);
                startActivity(emgcall);
            }
        });

    }
}
