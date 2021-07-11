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
import android.widget.EditText;
import android.widget.Toast;

public class Players extends AppCompatActivity {

    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_players);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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


        text1 = findViewById(R.id.p1);
        text2 = findViewById(R.id.p2);
        text3 = findViewById(R.id.p3);
        text4 = findViewById(R.id.p4);



        button = findViewById(R.id.enter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getText().toString().isEmpty() || text2.getText().toString().isEmpty() || text3.getText().toString().isEmpty() || text4.getText().toString().isEmpty()){
                    Toast.makeText(Players.this,"Please enter all players!",Toast.LENGTH_SHORT).show();
                }
                else if(text1.getText().toString().length() > 5 || text2.getText().toString().length() > 5  || text3.getText().toString().length() > 5 || text4.getText().toString().length() > 5){
                    Toast.makeText(Players.this,"Please enter maximum five letter names!",Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println(text1.getText() + " " + text2.getText() + " " + text3.getText() + " " + text4.getText());
                    Intent ScoreBoard = new Intent(getApplicationContext(), ScoreBoard.class);
                    ScoreBoard.putExtra("text1", text1.getText().toString());
                    ScoreBoard.putExtra("text2", text2.getText().toString());
                    ScoreBoard.putExtra("text3", text3.getText().toString());
                    ScoreBoard.putExtra("text4", text4.getText().toString());
                    startActivity(ScoreBoard);
                }
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