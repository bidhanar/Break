package com.example.abreak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <=21){
            SetWindowsFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);
        }
        if(Build.VERSION.SDK_INT >= 19){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                     View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        if(Build.VERSION.SDK_INT >= 21){
            SetWindowsFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }



        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Players = new Intent(getApplicationContext(), Players.class);
                startActivity(Players);
                System.out.println("Button Clicked");
            }
        });

    }

    public static void SetWindowsFlag(Activity activity, final int bits, Boolean on){
        Window wim = activity.getWindow();
        WindowManager.LayoutParams WinParams = wim.getAttributes();
        if(on){
            WinParams.flags |= bits;
        }else{
            WinParams.flags &= ~bits;
        }
        wim.setAttributes(WinParams);
    }
}