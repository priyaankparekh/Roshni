package com.example.roshni;

import android.Manifest;
import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Locale;


public class FullscreenActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    FrameLayout cam_prev;
    Camera mycamera=null;
    int red,blue,green,r_clo,b_clo,g_clo,closest_int,color_int=0,total;
    TextView rval,gval,bval,cname;
    String[] color_name,color_name1;
    CameraPreview preview;
    ColorN colorname1;
    private static final int cam_per=100;
    TextToSpeech textToSpeech;
    Bitmap bitmap;
    Button imgb1;
    int[] yo = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        imgb1 = (Button)findViewById(R.id.imgb1);
        cname=(TextView)findViewById(R.id.Cname);
        cam_prev=(FrameLayout) findViewById(R.id.cam_prev);

        startCamera();


        imgb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    bitmap = preview.getBitmap();
                    int Pixel = bitmap.getPixel(0,0);
                    red = Color.red(Pixel);
                    green = Color.green(Pixel);
                    blue = Color.blue(Pixel);
                    color_int = red * 65536 + green * 256 + blue - 16777216;
                } catch (Exception e) {}
                try {
                    color_name = colorname1.getColorName(red, green, blue);//String[] object return with length 3
                    r_clo = Integer.parseInt(color_name[1]);//here string return
                    g_clo = Integer.parseInt(color_name[2]);
                    b_clo = Integer.parseInt(color_name[3]);
                    closest_int = r_clo * 65536 + g_clo * 256 + b_clo - 16777216;//rgb to int //here second value for color with alpha value
                } catch (Exception e) {}

                if (color_name != null) {
                    cname.setText("COLOR :  " + color_name[0].toString().toUpperCase());
                    //color_closest.setBackgroundColor(closest_int);
                    speakColor();
                }
            }
        });
    }

    public void onStart(){
        super.onStart();
        Overlay ov2 = new Overlay(this);
        addContentView(ov2,new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int permission= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},cam_per);
        }
    }

    public void onResume(){
        super.onResume();
        //Toast.makeText(this,"In onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this,"In onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        mycamera = null;
        super.onStop();
        //Toast.makeText(this,"In onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this,"In onRestrart",Toast.LENGTH_SHORT).show();
        startCamera();
    }

    public void onDestroy(){
        if(mycamera !=null){
            mycamera.release();
            mycamera = null;
        }
        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
        //Toast.makeText(this,"In onDestroy",Toast.LENGTH_SHORT).show();
    }

    public  void startCamera(){
        try {
            mycamera = Camera.open();
            preview = new CameraPreview(this, mycamera);
            textToSpeech = new TextToSpeech(this, this);
            colorname1 = new ColorN();
            cam_prev.addView(preview);
            mycamera.setDisplayOrientation(90);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int language=textToSpeech.setLanguage(Locale.ENGLISH);
            if(language!=TextToSpeech.LANG_MISSING_DATA || language!=TextToSpeech.LANG_NOT_SUPPORTED){
                speakColor();
            }
        }
    }

    private void speakColor(){
        try {
            String text = color_name[0];
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }catch (Exception e){}
    }
}
