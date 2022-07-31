package com.example.loginandfindcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUserName;
    EditText etPassword;
    Button btnLogin;

    String username = "Kiss Pista";
    String password = "12345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typedUserName = etUserName.getText().toString();
                String typedPassword = etPassword.getText().toString();

                if (typedUserName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name!", Toast.LENGTH_SHORT).show();}
                if (typedPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your password!", Toast.LENGTH_SHORT).show();}
                if (!(typedUserName.equals(username) && typedPassword.equals(password)))
                    Toast.makeText(MainActivity.this, "Hibás felhasználói adatok!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this, com.example.loginandfindcity.Activity2.class);
                    startActivity(intent);
                }
            }
        });

    }

}