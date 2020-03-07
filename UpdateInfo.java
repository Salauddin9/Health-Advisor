package com.example.salauddinrubel.healthadviser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateInfo extends AppCompatActivity {

    private EditText uid,fna,unl,upas,usa;
    private Button update,delete;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        databaseHelper = new DatabaseHelper(this);

        uid = findViewById(R.id.idtext);
        fna = findViewById(R.id.editTextf);
        unl = findViewById(R.id.editTextl);
        upas = findViewById(R.id.editTextpass);
        usa = findViewById(R.id.editTextusa);
        update = findViewById(R.id.upde);
        delete = findViewById(R.id.del);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = databaseHelper.UpdateUser(uid.getText().toString(), fna.getText().toString(), unl.getText().toString(), usa.getText().toString(), upas.getText().toString());
                if(isUpdated==true){
                    Toast.makeText(UpdateInfo.this,  "Data Updated", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(UpdateInfo.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleteMe = databaseHelper.DeleteUser(uid.getText().toString());
                if(deleteMe==true){
                    Toast.makeText(UpdateInfo.this, "Data Deleted", Toast.LENGTH_LONG).show();
                   /* Intent its = new Intent(UpdateInfo.this, WelcomeMenu.class);
                    startActivity(its);*/
                }else{
                    Toast.makeText(UpdateInfo.this, "Error While Deleting", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
