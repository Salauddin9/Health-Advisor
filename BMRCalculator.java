package com.example.salauddinrubel.healthadviser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMRCalculator extends AppCompatActivity {

    private EditText wht, htf, hti, ag, usa;
    private RadioGroup rg;
    private RadioButton rbtn;
    private Button calbtn,savbtn;
    UserInfo userInfo = new UserInfo();
    private DatabaseHelper databaseHelper;
    private String bmrs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmrcalculator);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();


        wht = findViewById(R.id.weighteditText);
        htf = findViewById(R.id.feeteditText);
        hti = findViewById(R.id.incheditText);
        ag = findViewById(R.id.ageeditText);
        usa = findViewById(R.id.usereditText);
        rg = findViewById(R.id.rgbutton);
        calbtn = findViewById(R.id.calbutton);
        savbtn = findViewById(R.id.savbutton);




        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rb_id = rg.getCheckedRadioButtonId();
                rbtn = findViewById(rb_id);
                final String sex = rbtn.getText().toString();


                if(sex.equals("Male")){

                    try {
                        String w = wht.getText().toString();
                        Double wt = Double.parseDouble(w);

                        String f = htf.getText().toString();
                        Double ft = Double.parseDouble(f);

                        String a = ag.getText().toString();
                        Double at = Double.parseDouble(a);

                        String i = hti.getText().toString();
                        Double it = Double.parseDouble(i);

                        Double res = 66 + (13.74 * wt) + (((ft * 12) + it)*12.7) - (at * 6.8);

                        DecimalFormat decimalFormat = new DecimalFormat("#.00");
                        bmrs = decimalFormat.format(res);

                       // String result = String.format("%0.2f", res);

                       // showMassage("BMR", res.toString());

                        Toast.makeText(BMRCalculator.this, res.toString(), Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        showMassage("Error!!", "Check Input");
                    }


                }
                if(sex.equals("Female")) {

                    try {
                        String w = wht.getText().toString();
                        Double wt = Double.parseDouble(w);

                        String f = htf.getText().toString();
                        Double ft = Double.parseDouble(f);

                        String a = ag.getText().toString();
                        Double at = Double.parseDouble(a);

                        String i = hti.getText().toString();
                        Double it = Double.parseDouble(i);

                        Double res = 655 + (9.6 * wt) + (((ft * 12) + it)*4.7) - (at * 4.7);

                        //String result = String.format("%0.2f", res);

                        //showMassage("BMR", result);

                      Toast.makeText(BMRCalculator.this, res.toString(), Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        showMassage("Error!!", "Check Input");
                    }

                }


                }
        });

        savbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    String u = usa.getText().toString();
                    String a = ag.getText().toString();
                    int rb_id = rg.getCheckedRadioButtonId();
                    rbtn = findViewById(rb_id);
                    String sex = rbtn.getText().toString();
                    String w = wht.getText().toString();
                    String f = htf.getText().toString();
                    String i = hti.getText().toString();

                    databaseHelper.insertInfo(userInfo);

                    userInfo.setUsername(u);
                    userInfo.setGender(sex);
                    userInfo.setAge(a);
                    userInfo.setWeight(w);
                    userInfo.setFeet(f);
                    userInfo.setInch(i);
                    userInfo.setBmi("Not Calculated Yet");
                    userInfo.setBmr(bmrs);


                   /* int ret = databaseHelper.insertInfo(userInfo);
                    if(ret>1){
                        Toast.makeText(BMRCalculator.this, "Error in sql", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(BMRCalculator.this, "Saved Successfully", Toast.LENGTH_LONG).show();

                    }*/

                   // databaseHelper.insertInfo(userInfo);

                   /* if (rowId2 > 0) {
                        Toast.makeText(BMRCalculator.this, "Account Confirmed", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(BMRCalculator.this, "Error Creating!!", Toast.LENGTH_LONG).show();
                    }*/
                }catch (Exception e){
                    Toast.makeText(BMRCalculator.this, "SQL Error!!", Toast.LENGTH_LONG).show();
                }





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
