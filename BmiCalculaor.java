package com.example.salauddinrubel.healthadviser;




import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BmiCalculaor extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private EditText wt,htf,hti,usa;
    private Button btncal,btnsave;
    private String bmis;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculaor);
        databaseHelper = new DatabaseHelper(this);


           wt = findViewById(R.id.weighteditText);
           htf = findViewById(R.id.feeteditText);
           hti = findViewById(R.id.incheditText);
           usa = findViewById(R.id.usereditText);
           btncal = findViewById(R.id.calbutton);
           btnsave = findViewById(R.id.savbutton);



           btncal.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   try {

                       String w = wt.getText().toString();
                       Double weight = Double.parseDouble(w);

                       String ft = htf.getText().toString();
                       Double feet = Double.parseDouble(ft);

                       String in = hti.getText().toString();
                       Double inch = Double.parseDouble(in);

                       Double meter = (feet * 0.3048) + (inch * 0.0254);
                       Double cbmi = weight / (meter * meter);

                       String bmi = Double.toString(cbmi);
                       DecimalFormat decimalFormat = new DecimalFormat("#.00");
                       bmis = decimalFormat.format(cbmi);

                       showMassage("BMI", bmi);
                   }catch (Exception e){
                       Toast.makeText(BmiCalculaor.this,"Error!!", Toast.LENGTH_LONG).show();
                   }


               }
           });

           btnsave.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    boolean bmUpadte = databaseHelper.AddBmi(usa.getText().toString(), bmis);

                    if(bmUpadte == true){
                        Toast.makeText(BmiCalculaor.this, "Data Added", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(BmiCalculaor.this, "Error Adding", Toast.LENGTH_LONG).show();
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
