package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText Lname;
    Button Registration;
    DatabaseReference databaseReference;
    EditText email;
    FirebaseAuth firebaseAuth;
    EditText name;
    EditText password;
    EditText phone;
    ProgressBar progressBar;
    EditText repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        this.name = ((EditText) findViewById(R.id.ETnameRegistrationActivity));
        this.Lname = ((EditText) findViewById(R.id.ETLastnameRegistrationActivity));
        this.email = ((EditText) findViewById(R.id.ETemailRegistrationActivity));
        this.phone = ((EditText) findViewById(R.id.ETphoneRegistrationActivity));
        this.password = ((EditText) findViewById(R.id.ETpasswordRegistrationActivity));
        this.repassword = ((EditText) findViewById(R.id.ETRepasswordRegistrationActivity));
        this.Registration = ((Button) findViewById(R.id.BtnRegistrationActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progressRegistration));
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        this.Registration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!RegistrationActivity.this.name.getText().toString().isEmpty()) {
                    if (!RegistrationActivity.this.Lname.getText().toString().isEmpty()) {
                        if (!RegistrationActivity.this.phone.getText().toString().isEmpty()) {
                            if (!RegistrationActivity.this.email.getText().toString().isEmpty()) {
                                if (!RegistrationActivity.this.password.getText().toString().isEmpty()) {
                                    if (!RegistrationActivity.this.repassword.getText().toString().isEmpty()) {
                                        if (RegistrationActivity.this.password.getText().toString().equals(RegistrationActivity.this.repassword.getText().toString())) {
                                            RegistrationActivity.this.Registration.setEnabled(false);
                                            RegistrationActivity.this.progressBar.setVisibility(View.VISIBLE);
                                            firebaseAuth.createUserWithEmailAndPassword(RegistrationActivity.this.email.getText().toString(), RegistrationActivity.this.password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {

                                                    if (task.isSuccessful()) {

                                                        Map<String, Object> map = new HashMap<>();
                                                        map.put("Fname", name.getText().toString());
                                                        map.put("Lname", Lname.getText().toString());
                                                        map.put("email", email.getText().toString());
                                                        map.put("mainAdmin", "0");
                                                        map.put("name", name.getText().toString().concat(" " + Lname.getText().toString()));
                                                        map.put("phone", phone.getText().toString());
                                                        map.put("type", "member");
                                                        map.put("key",firebaseAuth.getUid().toString());

                                                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                startActivity(new Intent(RegistrationActivity.this, SplashScreenActivity.class));
                                                                finish();
                                                            }
                                                        });
                                                    } else {


                                                        RegistrationActivity.this.Registration.setEnabled(true);
                                                        RegistrationActivity.this.progressBar.setVisibility(View.INVISIBLE);
                                                        Toast.makeText(RegistrationActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                            return;
                                        }
                                        RegistrationActivity.this.repassword.setError("password not matched");
                                        return;
                                    }
                                    RegistrationActivity.this.repassword.setError("password is Empty");
                                    return;
                                }
                                RegistrationActivity.this.password.setError("password is Empty");
                                return;
                            }
                            RegistrationActivity.this.email.setError("email is Empty");
                            return;
                        }
                        RegistrationActivity.this.phone.setError("phone is Empty");
                        return;
                    }
                    RegistrationActivity.this.Lname.setError("Last name is Empty");
                    return;
                }
                RegistrationActivity.this.name.setError("name is Empty");
            }
        });
    }
}

