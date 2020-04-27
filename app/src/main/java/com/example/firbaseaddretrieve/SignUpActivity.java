package com.example.firbaseaddretrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout name, username, email, pwd, phone;
    private Button signup, goto_signin, remove;
    private String text_name, text_username, text_email, text_pwd, text_phone;
    DatabaseReference reference;
    HashMap<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name_signup);
        username = findViewById(R.id.user_signup);
        email = findViewById(R.id.email_signup);
        phone = findViewById(R.id.phone_signup);
        pwd = findViewById(R.id.password_signup);
        reference = FirebaseDatabase.getInstance().getReference("User");


        signup = findViewById(R.id.btn_signup);
        goto_signin = findViewById(R.id.signup_btn_signin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_name = name.getEditText().getText().toString().trim();
                text_username = username.getEditText().getText().toString().trim();
                text_email = email.getEditText().getText().toString().trim();
                text_pwd = pwd.getEditText().getText().toString().trim();
                text_phone = phone.getEditText().getText().toString().trim();

                map.put("name", text_name);
                map.put("user_name", text_username);
                map.put("phone", text_phone);
                map.put("email", text_email);
                map.put("password", text_pwd);
                reference.child(text_username).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "data added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "error adding data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        goto_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        });
    }

    private void validate() {
        text_name = name.getEditText().getText().toString().trim();
        text_username = username.getEditText().getText().toString().trim();
        text_email = email.getEditText().getText().toString().trim();
        text_pwd = pwd.getEditText().getText().toString().trim();
        text_phone = phone.getEditText().getText().toString().trim();


    }
}
