package com.android.flamingo.perka;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    }
    public void send()throws Exception{
        first=((EditText)findViewById(R.id.first)).getText().toString();
        last=((EditText)findViewById(R.id.last)).getText().toString();
        email=((EditText)findViewById(R.id.email)).getText().toString();
        position=((EditText)findViewById(R.id.pos)).getText().toString();
        source=((EditText)findViewById(R.id.source)).getText().toString();

        //   String first,String last,String email, String position,String source,String explanation, List<String> proj,String resume

        request req=new request(first,last,email,position,source,explanation,projects,base64res);
        response res = NetworkHelper.makeRequestAdapter(FieldsActivity.this)
                .create(perka_back_end.class).res(req);
    }
    public void submit(View view){
        try{
            send();
        }catch(Exception e){

        }
    }

}
