package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText et1,et2,et3;
Button btn1,btn2;
DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        DB = new DBhelper(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et1.getText().toString();
                String pass = et2.getText().toString();
                String repass = et3.getText().toString();

                if (email.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkemail(email);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(email, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, home.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exit please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password no matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,login.class));
            }
        });
    }
}