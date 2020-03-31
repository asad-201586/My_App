package com.example.chatbotforcorona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText emailEdittex,passwordEdittex,retypePasswordEdittex, firstNameEdittex,lastNameEdittex,mobileNumberEdittex,districtEdittex;
    Button signupButton;
    chatbotDatabase database = new chatbotDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        emailEdittex          = findViewById(R.id.email_edittex);
        passwordEdittex       = findViewById(R.id.new_password_edittex);
        retypePasswordEdittex = findViewById(R.id.retype_password_edittex);
        signupButton          = findViewById(R.id.create_account_button);
        firstNameEdittex      = findViewById(R.id.first_name_edittex);
        lastNameEdittex       = findViewById(R.id.last_name_edittex);
        mobileNumberEdittex   = findViewById(R.id.mobile_number_edittex);
        districtEdittex       = findViewById(R.id.district_edittex);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailEdittex.getText().toString();
                String password = passwordEdittex.getText().toString();
                String retype_password = retypePasswordEdittex.getText().toString();
                String firstName = firstNameEdittex.getText().toString();
                String lastName = lastNameEdittex.getText().toString();
                String mobileNumber = mobileNumberEdittex.getText().toString();
                String district = districtEdittex.getText().toString();

                if (email.equals("")||password.equals("")||retype_password.equals("")||firstName.equals("")||lastName.equals("")||district.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }

                else{

                    if (password.equals(retype_password)){
                        boolean checkEmail = database.checkEmail(email);
                        if (checkEmail==true) {
                            boolean insert = database.insertData(firstName,lastName,mobileNumber,email,district, password, retype_password);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                                emailEdittex.setText("");
                                passwordEdittex.setText("");
                                retypePasswordEdittex.setText("");
                                firstNameEdittex.setText("");
                                lastNameEdittex.setText("");
                                mobileNumberEdittex.setText("");
                                districtEdittex.setText("");
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                        }

                    }

                    else {
                        Toast.makeText(getApplicationContext(),"password doesn't matched",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
