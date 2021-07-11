package com.example.abreak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static android.graphics.Color.rgb;

public class ScoreBoard extends AppCompatActivity {


    ListView list1;
    ListView list2;
    ListView list3;
    ListView list4;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    ArrayList<String> arrayList3 = new ArrayList<>();
    ArrayList<String> arrayList4 = new ArrayList<>();

    ArrayList<Integer> score1 = new ArrayList<>();
    ArrayList<Integer> score2 = new ArrayList<>();
    ArrayList<Integer> score3 = new ArrayList<>();
    ArrayList<Integer> score4 = new ArrayList<>();

    Player player1;
    Player player2;
    Player player3;
    Player player4;

    TextView p1;
    TextView p2;
    TextView p3;
    TextView p4;

    TextView p11;
    TextView p21;
    TextView p31;
    TextView p41;

    Button updater;
    Button deleter;
    Button click;

    EditText s1;
    EditText s2;
    EditText s3;
    EditText s4;
    String name1;

    private static final int REQUEST_EXTERNAL_STORAGe = 1;
    private static String[] permissionstorage = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    //    ListView listView = (ListView)findViewById(R.id.listview);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_score_board);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        click = findViewById(R.id.share);
//        verifystoragepermissions(ScoreBoard.this);
//        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.beep);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScoreBoard.this, "Screenshot Captured!!" , Toast.LENGTH_SHORT).show();
                screenshot(getWindow().getDecorView().getRootView(), "result");

//                mediaPlayer.start();
            }
        });


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

        list1 = findViewById(R.id.listview1);
        list2 = findViewById(R.id.listview2);
        list3 = findViewById(R.id.listview3);
        list4 = findViewById(R.id.listview4);

        deleter = findViewById(R.id.delete);
        updater = findViewById(R.id.update);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);

        p11 = findViewById(R.id.p11);
        p21 = findViewById(R.id.p21);
        p31 = findViewById(R.id.p31);
        p41 = findViewById(R.id.p41);

        Intent intent = getIntent();
        player1 = new Player(intent.getStringExtra("text1"));
        player2 = new Player(intent.getStringExtra("text2"));
        player3 = new Player(intent.getStringExtra("text3"));
        player4 = new Player(intent.getStringExtra("text4"));

        p1.setText(player1.name.toUpperCase()+"\n( 00 )");
        p2.setText(player2.name.toUpperCase()+"\n( 00 )");
        p3.setText(player3.name.toUpperCase()+"\n( 00 )");
        p4.setText(player4.name.toUpperCase()+"\n( 00 )");

        p11.setText(player1.name.toUpperCase());
        p21.setText(player2.name.toUpperCase());
        p31.setText(player3.name.toUpperCase());
        p41.setText(player4.name.toUpperCase());

//        name1 =  "  " + player1.name.toUpperCase() +"         " + player2.name.toUpperCase() +"          " + player3.name.toUpperCase()+"         "+player4.name.toUpperCase();
//        String name2 = player1.name.toUpperCase() +" (00)"+"  " + player2.name.toUpperCase() +" (00)"+"  " + player3.name.toUpperCase()+" (00)"+" "+player4.name.toUpperCase()+" (00)";
//        textView.setText(name2);
        setList();
        updater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1  = findViewById(R.id.s1);
                s2  = findViewById(R.id.s2);
                s3  = findViewById(R.id.s3);
                s4 =  findViewById(R.id.s4);


                try {
//                    String s1 = editText.getText().toString();
                    int sc1 = Integer.parseInt(s1.getText().toString());
                    int sc2 = Integer.parseInt(s2.getText().toString());
                    int sc3 = Integer.parseInt(s3.getText().toString());
                    int sc4 = Integer.parseInt(s4.getText().toString());
                    int net = sc1 + sc2 + sc3 + sc4;

                    System.out.println(sc1 + " " + sc2 +" " + sc3 +" " + sc4 +" Sum = " + (sc1+sc2+sc3+sc4));
                    if(net <= 25 && (sc1 >= -5 && sc1 <= 25) && (sc2 >= -5 && sc2 <= 25) && (sc3 >= -5 && sc3 <= 25) && (sc4 >= -5 && sc4 <= 25)){

                        score1.add(sc1);
                        score2.add(sc2);
                        score3.add(sc3);
                        score4.add(sc4);
                        arrayList1.add("  " + form(sc1));
                        arrayList2.add("  " + form(sc2));
                        arrayList3.add("   " + form(sc3));
                        arrayList4.add("   " + form(sc4));

                        p1.setText(player1.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score1))) + " )");
                        p2.setText(player2.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score2))) + " )");
                        p3.setText(player3.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score3))) + " )");
                        p4.setText(player4.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score4))) + " )");

                        if(sum(score1) >= 75)
                            p1.setTextColor(rgb(246, 90, 143));
                        if(sum(score2) >= 75)
                            p2.setTextColor(rgb(246, 90, 143));
                        if(sum(score3) >= 75)
                            p3.setTextColor(rgb(246, 90, 143));
                        if(sum(score4) >= 75)
                            p4.setTextColor(rgb(246, 90, 143));

                        if(sum(score1) < 75)
                            p1.setTextColor(rgb(174, 241, 177));
                        if(sum(score2) < 75)
                            p2.setTextColor(rgb(174, 241, 177));
                        if(sum(score3) < 75)
                            p3.setTextColor(rgb(174, 241, 177));
                        if(sum(score4) < 75)
                            p4.setTextColor(rgb(174, 241, 177));

                        setList();

                        if(sum(score1) > 100 || sum(score2) > 100 || sum(score3) > 100 || sum(score4) > 100){
                            updater.setText("GAME OVER!!");
                            updater.setClickable(false);
                            s1.setVisibility(View.INVISIBLE);
                            s2.setVisibility(View.INVISIBLE);
                            s3.setVisibility(View.INVISIBLE);
                            s4.setVisibility(View.INVISIBLE);

                        }
                        s1.getText().clear();
                        s2.getText().clear();
                        s3.getText().clear();
                        s4.getText().clear();
                    }
                    else if(!((sc1 >= -5 && sc1 <= 25) && (sc2 >= -5 && sc2 <= 25) && (sc3 >= -5 && sc3 <= 25) && (sc4 >= -5 && sc4 <= 25))){
                        Toast.makeText(ScoreBoard.this,"Invalid Score!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ScoreBoard.this,"Total Score above 25!",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(ScoreBoard.this,"Try Again!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList1.size() == 0){
                    Toast.makeText(ScoreBoard.this,"No data to Delete!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScoreBoard.this);
                    builder.setCancelable(true);
                    builder.setTitle("Delete last Row?");
//                    builder.setMessage("");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    arrayList1.remove(arrayList1.size() - 1);
                                    arrayList2.remove(arrayList2.size() - 1);
                                    arrayList3.remove(arrayList3.size() - 1);
                                    arrayList4.remove(arrayList4.size() - 1);

                                    score1.remove(score1.size() - 1);
                                    score2.remove(score2.size() - 1);
                                    score3.remove(score3.size() - 1);
                                    score4.remove(score4.size() - 1);

                                    if(sum(score1) >= 75)
                                        p1.setTextColor(rgb(246, 90, 143));
                                    if(sum(score2) >= 75)
                                        p2.setTextColor(rgb(246, 90, 143));
                                    if(sum(score3) >= 75)
                                        p3.setTextColor(rgb(246, 90, 143));
                                    if(sum(score4) >= 75)
                                        p4.setTextColor(rgb(246, 90, 143));

                                    if(sum(score1) < 75)
                                        p1.setTextColor(rgb(174, 241, 177));
                                    if(sum(score2) < 75)
                                        p2.setTextColor(rgb(174, 241, 177));
                                    if(sum(score3) < 75)
                                        p3.setTextColor(rgb(174, 241, 177));
                                    if(sum(score4) < 75)
                                        p4.setTextColor(rgb(174, 241, 177));


                                    setList();
                                    p1.setText(player1.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score1))) + " )");
                                    p2.setText(player2.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score2))) + " )");
                                    p3.setText(player3.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score3))) + " )");
                                    p4.setText(player4.name.toUpperCase()+"\n(" + String.valueOf(form(sum(score4))) + " )");

                                    updater.setText("UPDATE");
                                    updater.setClickable(true);
                                    s1.setVisibility(View.VISIBLE);
                                    s2.setVisibility(View.VISIBLE);
                                    s3.setVisibility(View.VISIBLE);
                                    s4.setVisibility(View.VISIBLE);



                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }

    public static void verifystoragepermissions(Activity activity) {

        int permissions = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // If storage permission is not given then request for External Storage Permission
        if (permissions != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, permissionstorage, REQUEST_EXTERNAL_STORAGe);
        }
    }

    @Override
    public boolean onKeyDown(int key_code, KeyEvent key_event) {
        if (key_code== KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(key_code, key_event);
            return false;
        }
        return false;
    }

    public void setList(){
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList1){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,28);

                tv.setTextColor(Color.WHITE);

                // Return the view
                return view;
            }
        };

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList2){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,28);

                tv.setTextColor(Color.WHITE);

                // Return the view
                return view;
            }
        };
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList3){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,28);

                tv.setTextColor(Color.WHITE);

                // Return the view
                return view;
            }
        };
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList4){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,28);

                tv.setTextColor(Color.WHITE);

                // Return the view
                return view;
            }
        };
        list1.setAdapter(arrayAdapter1);
        list2.setAdapter(arrayAdapter2);
        list3.setAdapter(arrayAdapter3);
        list4.setAdapter(arrayAdapter4);
    }
    public static int sum(ArrayList<Integer> list) {
        int sum = 0;
        for (int i: list) {
            sum += i;
        }
        return sum;
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

    public static String form(int i){

        String s;
        if (i / 10 != 0){
            s = " " + String.valueOf(i);
        }
        else if(i < 0 && i / 10 == 0){
            s = "-0" + String.valueOf(-1 * i);
        }
        else if (i < 0){
            s = String.valueOf(i);
        }
        else{
            s = " 0" + String.valueOf(i);
        }
        return s;

    }


    protected static File screenshot(View view, String filename) {
        Date date = new Date();

        // Here we are initialising the format of our image name
        CharSequence format = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        try {
            // Initialising the directory of storage
            String dirpath = Environment.getExternalStorageDirectory() + "";
            File file = new File(dirpath);
            if (!file.exists()) {
                boolean mkdir = file.mkdir();
            }

            // File name
            String path = dirpath + "/" + filename + "-" + format + ".jpeg";
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            File imageurl = new File(path);
            FileOutputStream outputStream = new FileOutputStream(imageurl);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            outputStream.flush();
            outputStream.close();
            return imageurl;

        } catch (FileNotFoundException io) {
            io.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}