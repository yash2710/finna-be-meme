package com.example.tanha.finna_be_meme1;

import java.util.Date;

/**
 * Created by Tanha on 9/27/2015.
 */
public class Appoint {
    String id;
    String name;
    String time;
    String contact;
    Date date;
    String obid;
    public Appoint(String id, String name, Date date, String time, String contact) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.contact = contact;
        this.date = date;
        //this.obid=obid;
        
    }
    public Appoint(String id,Date date,String obid){
        this.id=id;
        this.date=date;
        this.obid=obid;
    }
}
