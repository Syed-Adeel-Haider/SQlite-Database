package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText et1,et2;
    Button btn2;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        btn2 = (Button) findViewById(R.id.btn2);
        DB = new DBhelper(this);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et1.getText().toString();
                String pass = et2.getText().toString();
                if (email.equals("") || pass.equals(""))
                    Toast.makeText(login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkemailpassword(email,pass);
                            if (checkuserpass==true){
                                Toast.makeText(login.this, "SignIn successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this,home.class));
                            }
                            else{
                                Toast.makeText(login.this, "Invalid credential", Toast.LENGTH_SHORT).show();
                            }
                }

            }
        });
    }
}