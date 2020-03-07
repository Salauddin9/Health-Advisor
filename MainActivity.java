package com.example.salauddinrubel.healthadviser;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usnam;
    private EditText upass;
    private Button logbtn;
    private Button btnctr;
    private Button showbtn;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usnam = findViewById(R.id.usanam);
        upass = findViewById(R.id.upasword);

        logbtn = findViewById(R.id.logbtn);
        btnctr = findViewById(R.id.btncrt);
        showbtn = findViewById(R.id.buttonshow);

        databaseHelper = new DatabaseHelper(this);
       SQLiteDatabase db = databaseHelper.getWritableDatabase();



        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String username = usnam.getText().toString();
                    String password = upass.getText().toString();

                    Boolean result = databaseHelper.findPassword(username, password);

                    if (result == true) {
                        Intent intent = new Intent(MainActivity.this, WelcomeMenu.class);
                        intent.putExtra("Username", username);
                        Intent in2 = new Intent(MainActivity.this, DatabaseHelper.class);
                        intent.putExtra("Username", username);
                        startActivity(intent);
                        usnam.setText("");
                        upass.setText("");

                    }
                    else{
                        showMassage("Access Denied!!", "Unauthorized User");
                    }

                }catch (Exception e){
                    showMassage("Error!", "Check Your Input");
                }

            }
        });

        btnctr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itt = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(itt);
            }
        });

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = databaseHelper.getAllUser();
                if (res.getCount() == 0) {
                    showMassage("Error", "Nothing Found");
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("First Name: " + res.getString(1) + "\n");
                    buffer.append("Last Name: " + res.getString(2) + "\n");
                    buffer.append("User Name: " + res.getString(3) + "\n");
                    buffer.append("Password: " + res.getString(4) + "\n");

                }
                showMassage("Data", buffer.toString());
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

























