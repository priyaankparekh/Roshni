package com.example.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EmergencyCall extends AppCompatActivity {

    Button police, ambulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);

        police = (Button) findViewById(R.id.policebtn);
        ambulance = (Button) findViewById(R.id.ambulancebtn);


        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callPolice = new Intent(Intent.ACTION_CALL);
                callPolice.setData(Uri.parse("tel:100"));
                if (ActivityCompat.checkSelfPermission(EmergencyCall.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callPolice);

            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callAmbulance = new Intent(Intent.ACTION_CALL);
                callAmbulance.setData(Uri.parse("tel:102"));
                if (ActivityCompat.checkSelfPermission(EmergencyCall.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callAmbulance);

            }
        });

        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);
                /*Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);*/
            }
        //});

    }

    /*@Override
    public void onActivityResult(int reqCode, int resultCode, Intent data)
    {
        /*String name="";
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            Uri contactData = data.getData();
            Cursor c = managedQuery(contactData, null, null, null, null);
            if (c.moveToFirst())
            {
                 name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

            }
        }*/
        //super.onActivityResult(reqCode, resultCode, data);

       /* if(reqCode == RQS_PICK_CONTACT){
            if(resultCode == RESULT_OK){
                Uri contactData = data.getData();
                Cursor cursor =  managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();

                String number =       cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
                //contactName.setText(name);
                //contactNumber.setText(number);
                //contactEmail.setText(email);*/





