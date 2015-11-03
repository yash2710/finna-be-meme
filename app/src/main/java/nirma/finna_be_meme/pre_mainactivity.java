package nirma.finna_be_meme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;


public class pre_mainactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_mainactivity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(pre_mainactivity.this,User_login.class);
                mainIntent.putExtra("hi","hi");
                pre_mainactivity.this.startActivity(mainIntent);
                pre_mainactivity.this.finish();
            }
        }, 1000);
        this.setTitle("Appoint Me!");
        try {
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "Xyl1BqgIJljeUZHslYJIOZ0oX1OH9d7GeXzfcdwt", "nANyTRxoUroiC98Oz0rBVJNXBs8XxDAUbSJ9z8ap");
        }catch(Exception e){

        }
    }
}
