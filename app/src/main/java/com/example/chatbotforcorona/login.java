package com.example.chatbotforcorona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText emailEdittex,passwordEdittex;
    Button loginButton;
    chatbotDatabase database = new chatbotDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = findViewById(R.id.loginId);
        emailEdittex = findViewById(R.id.emailEdittexId);
        passwordEdittex = findViewById(R.id.passwordEdittexId);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailEdittex.getText().toString();
                final String password = passwordEdittex.getText().toString();

                if (email.equals("")||password.equals("")){
                    Toast.makeText(getApplicationContext(),"email or password is empty",Toast.LENGTH_SHORT).show();
                }
                else {

                    boolean boolLogin = database.checkLogin(email,password);

                    if (boolLogin==false){
                        Intent loginIntent = new Intent(login.this,Chatting.class);
                        startActivity(loginIntent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Email or password is incorrect",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}
