package com.example.firbaseaddretrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout username,password;
    private Button signnin,goto_sign_up;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.user_signin);
        password=findViewById(R.id.password_signin);
        signnin=findViewById(R.id.btn_signin);
        goto_sign_up=findViewById(R.id.signin_btn_signup);

        databaseReference= FirebaseDatabase.getInstance().getReference("User");

        goto_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                finish();
            }
        });

        signnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user=username.getEditText().getText().toString().trim();
                final String pwd=password.getEditText().getText().toString().trim();

                Query query=databaseReference.orderByChild("user_name").equalTo(user);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            String passwordDB=dataSnapshot.child(user).child("password").getValue(String.class);

                            if(passwordDB.equals(pwd))
                            {

                                String nameDB=dataSnapshot.child(user).child("name").getValue(String.class);
                                String emaileDB=dataSnapshot.child(user).child("email").getValue(String.class);
                                String phoneDB=dataSnapshot.child(user).child("phone").getValue(String.class);
                                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                intent.putExtra("name",nameDB);
                                intent.putExtra("email",emaileDB);
                                intent.putExtra("phone",phoneDB);
                                intent.putExtra("username",user);
                                startActivity(intent);
                            }
                            else
                            {
                                password.setError("Incorrect password");
                                password.requestFocus();
                            }
                        }
                        else
                        {
                            username.setError("user not exist");
                            username.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
