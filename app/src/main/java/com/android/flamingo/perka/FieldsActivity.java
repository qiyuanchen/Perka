package com.android.flamingo.perka;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FieldsActivity extends AppCompatActivity {
    private String first,last,email,position,source,base64res;
    private String explanation="Given that I am applying for an Android position, I thought it was only appropriate if I make an android program to do it \n " +
            "This program takes in informations from the user through edit text fields and sends it to the endpoint provided by you guys\n" +
            "this program can be found in my github page @https://github.com/qiyuanchen/Perka ";
    private byte[]resume;
    private List<String> projects;
    //going to make explanation, resume, and projects hardcoded.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_fields);
        projects=new ArrayList<String>();
        projects.add("https://github.com/qiyuanchen");

    }
    public void send()throws Exception{
        first=((EditText)findViewById(R.id.first)).getText().toString();
        last=((EditText)findViewById(R.id.last)).getText().toString();
        email=((EditText)findViewById(R.id.email)).getText().toString();
        position=((EditText)findViewById(R.id.pos)).getText().toString();
        source=((EditText)findViewById(R.id.source)).getText().toString();

        //   String first,String last,String email, String position,String source,String explanation, List<String> proj,String resume
        base64res=convertResume();
        if(!base64res.equals("Error")) {
           // writeToFile(base64res);
            request req = new request(first, last, email, position, source, explanation, projects, base64res);
            String res = NetworkHelper.makeRequestAdapter(FieldsActivity.this)
                   .create(perka_back_end.class).res(req);
            Log.d("Result",res);
        }

    }
    public void submit(View view){
        try{
            send();
        }catch(Exception e){

        }
    }
    private String convertResume(){
        AssetManager am= getAssets();
        InputStream input;
        String encoded="";
        try{
            input=am.open("Resume.pdf");
            resume=IOUtils.toByteArray(input);
            encoded=Base64.encodeToString(resume,Base64.DEFAULT);

        }catch(IOException e){
            e.printStackTrace(System.out);
        }
        if(encoded!=null&&!encoded.equals("")){
            return encoded;
        }
        return "Error";
    }
    /* FOR TESTING
    private void writeToFile(String data) {
        File logFile = new File(Environment.getExternalStorageDirectory().toString(), "myFile.txt");
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            BufferedWriter output = new BufferedWriter(new FileWriter(logFile));
            output.write(data);
            output.close();
        }catch(IOException e){

        }
    }
    */


}
