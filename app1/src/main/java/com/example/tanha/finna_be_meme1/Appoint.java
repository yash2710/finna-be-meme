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
    public Appoint(String id, String name, Date date, String time, String contact) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.contact = contact;
        this.date = date;
        
    }
    public Appoint(String id,Date date){
        this.id=id;
        this.date=date;
    }
}
