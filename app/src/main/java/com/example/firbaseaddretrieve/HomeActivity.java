package com.example.firbaseaddretrieve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class HomeActivity extends AppCompatActivity {

    private TextInputLayout name,username,phone,email;
    private TextView prof_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        name=findViewById(R.id.name_home);
        username=findViewById(R.id.username_home);
        phone=findViewById(R.id.phone_home);
        email=findViewById(R.id.email_home);
        prof_name=findViewById(R.id.prof_text);

        Bundle extras=getIntent().getExtras();

        if(extras!=null) {
            String st_name=extras.getString("name");
            String st_user=extras.getString("username");
            String st_phone=extras.getString("phone");
            String st_email=extras.getString("email");

            username.getEditText().setText(st_user);
            name.getEditText().setText(st_name);
            email.getEditText().setText(st_email);
            phone.getEditText().setText(st_phone);

            prof_name.setText(st_user);
        }
    }
}
