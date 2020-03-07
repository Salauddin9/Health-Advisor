package com.example.salauddinrubel.healthadviser;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.spec.ECField;

public class CreateAccount extends AppCompatActivity {

   private EditText fnam,lnam,usernam,userpass,upass2;
   private Button btncreate,btnback;
    UserDetails userDetails = new UserDetails();
   private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        databaseHelper = new DatabaseHelper(this);

        fnam = findViewById(R.id.fnam);
        lnam = findViewById(R.id.lnam);
        usernam = findViewById(R.id.usernam);
        userpass = findViewById(R.id.upas);
        btncreate = findViewById(R.id.button);
        btnback = findViewById(R.id.button2);
        upass2 = findViewById(R.id.uspass2);




        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String frstname = fnam.getText().toString().trim();
                    if(frstname.isEmpty()){
                        Toast.makeText(CreateAccount.this, "Please Enter a valid Name", Toast.LENGTH_LONG).show();
                        return;
                    }
                    String firstname = frstname;
                    String lastname = lnam.getText().toString();


                    String usrname = usernam.getText().toString().trim();
                    if(usrname.isEmpty()){
                        Toast.makeText(CreateAccount.this, "Username must be filled", Toast.LENGTH_LONG).show();
                        return;
                    }
                    String username = usrname;

                    String pasword = userpass.getText().toString().trim();
                    if(pasword.isEmpty()){
                        Toast.makeText(CreateAccount.this, "Password can't be empty", Toast.LENGTH_LONG).show();
                        return;
                    }
                       String password = pasword;






                    String passCheck = upass2.getText().toString();


                    if (password.equals(passCheck)) {
                        userDetails.setFirstname(firstname);
                        userDetails.setLastname(lastname);
                        userDetails.setUsername(username);
                        userDetails.setPassword(password);

                        long rowId = databaseHelper.insertData(userDetails);

                        if (rowId > 0) {
                            Toast.makeText(getApplicationContext(), "Account Confirmed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        showMassage("Error!!", "Password doesn't match");
                    }

                }catch (Exception e){
                    showMassage("Error Input", "Check Input");
                }






            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CreateAccount.this, MainActivity.class);

                startActivity(it);
                finish();
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


















