package com.example.salauddinrubel.healthadviser;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeMenu extends AppCompatActivity {


    private Button bmrbtn,bmibtn,toolbtn,infobtn,ecgbtn,dietbtn;
    private TextView tv;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_menu);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        bmrbtn = findViewById(R.id.buttonBMR);
        bmibtn = findViewById(R.id.buttonbmi);
        dietbtn = findViewById(R.id.buttond);
        infobtn = findViewById(R.id.buttonm);
        ecgbtn = findViewById(R.id.buttonecg);
        toolbtn = findViewById(R.id.buttont);
        dietbtn = findViewById(R.id.buttond);

        Intent intn = getIntent();
        String usa = intn.getExtras().getString("Username");

        tv = findViewById(R.id.utv);
        tv.setText(usa);

        bmibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(WelcomeMenu.this, BmiCalculaor.class);
                startActivity(it1);

            }
        });

        bmrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 = new Intent(WelcomeMenu.this, BMRCalculator.class);
                startActivity(it2);
            }
        });

        dietbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it3 = new Intent(WelcomeMenu.this, DietPlan.class);
                startActivity(it3);
            }
        });


        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = databaseHelper.showMyInfo();
                if (res.getCount() == 0) {
                    showMassage("Error", "Nothing Found");
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("User ID: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + " ");
                    buffer.append(res.getString(2) + "\n");
                    buffer.append("Gender: "+res.getString(7)+"\n");
                    buffer.append("Age: " + res.getString(8) + " Years \n");
                    buffer.append("Weight: " + res.getString(9) + " K.G\n");
                    buffer.append("Height: " + res.getString(10) + " Feet");
                    buffer.append("  " + res.getString(11) + " Inch\n");
                    buffer.append("BMR: " + res.getString(12) + " Calories\n");
                    buffer.append("BMI: " + res.getString(13) + " \n");


                }
                showMassage("Your Information", buffer.toString());

            }


        });

        toolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it4 = new Intent(WelcomeMenu.this, ToolsClass.class);
                startActivity(it4);
            }
        });

        ecgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMassage("Sorry!!", "This Page is under construction");
            }
        });


    }
    public void showMassage(String title, String Massage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Massage);
        builder.show();
    }
}
