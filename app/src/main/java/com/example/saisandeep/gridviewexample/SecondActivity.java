package com.example.saisandeep.gridviewexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saisandeep on 11/20/2014.
 */
public class SecondActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seond_activity);
        Intent intent=getIntent();

        if(intent !=null) {


           int image= intent.getIntExtra("countryImage", 0);
            String name=intent.getStringExtra("countryName");

            TextView countryName= (TextView) findViewById(R.id.textView);
            ImageView countryImage= (ImageView) findViewById(R.id.imageView);

            countryImage.setImageResource(image);
            countryName.setText("falg" +name);
        }
    }

public void closeDialog(View v){
finish();
}
}
