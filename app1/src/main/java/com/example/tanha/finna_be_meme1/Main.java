package com.example.tanha.finna_be_meme1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;

/**
 * Created by Yash on 16-Jul-15.
 */
public class Main extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "Xyl1BqgIJljeUZHslYJIOZ0oX1OH9d7GeXzfcdwt", "nANyTRxoUroiC98Oz0rBVJNXBs8XxDAUbSJ9z8ap");
        }catch(Exception e){

        }finally {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }
}
