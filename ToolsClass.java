package com.example.salauddinrubel.healthadviser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ToolsClass extends AppCompatActivity {


    private Button up,con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_class);

        up = findViewById(R.id.upinfo);
        con = findViewById(R.id.buttoncon);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1  = new Intent(ToolsClass.this, UpdateInfo.class);
                startActivity(it1);
            }
        });

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 = new Intent(ToolsClass.this, Converters.class);
                startActivity(it2);
            }
        });


    }
}
