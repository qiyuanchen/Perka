package com.android.flamingo.perka;

import java.util.List;

/**
 * Created by Qiyuan on 2/25/2016.
 */
public class request {

    public String first_name;
    public String last_name;
    public String email;
    public String position_ID;
    public String explanation;
    public List<String> projects;
    public String source;
    public String resume;

    public request(String first,String last,String email, String position,String source,String explanation, List<String> proj,String resume){
        this.first_name=first;
        this.last_name=last;
        this.email=email;
        this.position_ID=position;
        this.explanation=explanation;
        this.source=source;
        this.resume=resume;
        this.projects=proj;


    }
}
