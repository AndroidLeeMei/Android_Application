package com.example.auser.android_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// /data/data/com.example.auser.android_application/files
        File f=getFilesDir();
        Log.d("File",f.getAbsolutePath());

        System.out.println("f.getAbsolutePath()=" + f.getAbsolutePath());

        ed=(EditText)findViewById(R.id.editText);

    }
    public void writeFile(View target) {
        System.out.println("write");

        File f=getFilesDir();


        try {
//            FileWriter fw = new FileWriter("C:\\Users\\auser\\AndroidStudioProjects\\Android_Application");
            FileWriter fw = new FileWriter(f.getAbsolutePath() +f.separator + "mydata.txt");
            Log.d("File=" , f.separator);
            fw.write(ed.getText().toString());
            fw.close();
            ed.setText("");
            Toast.makeText(this,"資料已寫入,請至ADM的data/data查看",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void readFile(View target) {
        System.out.println("read");
        File f=getFilesDir();
        File myfile=new File(f.getAbsolutePath() +f.separator + "mydata.txt");

        if (myfile.exists()) {

            try {
                FileReader fr = new FileReader(myfile);
                BufferedReader br = new BufferedReader(fr);
                String str = br.readLine();
                br.close();
                fr.close();
                ed.setText(str);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this,"檔案尚未存在",Toast.LENGTH_SHORT).show();
        }

    }
//    "com.example.auser.android_application_preferences"
    public void readPreferences(View target) {
        System.out.println("test");
        Log.d("File=" , "0");
        Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT);
//        SharedPreferences sp=getSharedPreferences(getPackageName()+"_preferences",MODE_PRIVATE);
        Log.d("File=" , "1");
        SharedPreferences sp=getSharedPreferences("com.example.auser.android_application_preferences",MODE_PRIVATE);
        Log.d("File=" , "2");
        String str=sp.getString("example_text","");//第二個參數是取不到值default
        Log.d("File=" , "3");
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
        Log.d("File=" , "4");

    }

    public void clickSettings(View target){
        Intent it=new Intent(this,SettingsActivity.class);
        startActivity(it);
    }
}
