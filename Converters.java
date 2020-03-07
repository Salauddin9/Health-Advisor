package com.example.salauddinrubel.healthadviser;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Converters extends AppCompatActivity {

    private EditText et;
    private TextView tv;
    private CheckBox c1,c2,c3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converters);

        et = findViewById(R.id.editText);
        c1 = findViewById(R.id.pkg);
        c2 = findViewById(R.id.mtof);
        c3 = findViewById(R.id.ckmtoin);
        tv = findViewById(R.id.textView);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String i = et.getText().toString();
                    Double p = Double.parseDouble(i);
                    Double kg = p*0.454;
                    String k = String.valueOf(kg);
                    tv.setText(i+" Pound = "+k+" K.G\n");

                }catch (Exception e){
                    Toast.makeText(Converters.this, "Error!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String i2 = et.getText().toString();
                    Double p2 = Double.parseDouble(i2);
                    Double ft = p2*3.28;
                    String feet = String.valueOf(ft);
                    tv.setText(i2+" Meter = "+feet+" Feet\n");

                }catch (Exception e){
                    Toast.makeText(Converters.this, "Error!!", Toast.LENGTH_LONG).show();
                }

            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String i3 = et.getText().toString();
                    Double p3 = Double.parseDouble(i3);
                    Double in = p3*39.37;
                    String inch = String.valueOf(in);
                    tv.setText(i3+" Meter = "+inch+" Inch\n");

                }catch (Exception e){
                    Toast.makeText(Converters.this, "Error!!", Toast.LENGTH_LONG).show();
                }
            }
        });




    }
}
