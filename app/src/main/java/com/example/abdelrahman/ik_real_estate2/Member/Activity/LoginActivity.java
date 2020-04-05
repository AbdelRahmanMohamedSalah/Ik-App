package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Admin.Activity.MainActivityAdmin;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button Login;
    TextView TextsignUp;
    FirebaseAuth.AuthStateListener authStateListener;
    DatabaseReference databaseReference;
    EditText email;
    FirebaseAuth firebaseAuth;
    EditText password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.email = ((EditText) findViewById(R.id.ETemailLoginActivity));
        this.password = ((EditText) findViewById(R.id.ETpasswordLoginActivity));
        this.Login = ((Button) findViewById(R.id.BtnLoginActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progressLogin));
        this.TextsignUp = ((TextView) findViewById(R.id.textSignUpLoginActivity));
        this.firebaseAuth = FirebaseAuth.getInstance();

        this.TextsignUp.setVisibility(View.VISIBLE);

        this.TextsignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                LoginActivity.this.finish();
            }
        });
        this.Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!LoginActivity.this.email.getText().toString().isEmpty()) {
                    if (!LoginActivity.this.password.getText().toString().isEmpty()) {
                        LoginActivity.this.Login.setEnabled(false);
                        LoginActivity.this.progressBar.setVisibility(View.VISIBLE);
                        LoginActivity.this.TextsignUp.setVisibility(View.INVISIBLE);

                        firebaseAuth.signInWithEmailAndPassword(LoginActivity.this.email.getText().toString(), LoginActivity.this.password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    LoginActivity.this.databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(LoginActivity.this.firebaseAuth.getCurrentUser().getUid().toString());
                                    LoginActivity.this.databaseReference.addValueEventListener(new ValueEventListener() {


                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            String type = (String) dataSnapshot.child("type").getValue(String.class);
                                            if (type.equals("member")) {
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                LoginActivity.this.startActivity(intent);
                                                LoginActivity.this.finish();

                                            }
                                            if (type.equals("admin")) {
                                                Intent intent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                                                LoginActivity.this.startActivity(intent);
                                                LoginActivity.this.finish();
                                            }
                                        }

                                        public void onCancelled(DatabaseError paramAnonymous3DatabaseError) {
                                        }
                                    });

                                } else {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    LoginActivity.this.progressBar.setVisibility(View.INVISIBLE);
                                    LoginActivity.this.Login.setEnabled(true);
                                    LoginActivity.this.TextsignUp.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    } else {
                        LoginActivity.this.password.setError("password is Empty");
                    }
                } else {
                    LoginActivity.this.email.setError("email is Empty");
                }
            }
        });
    }
}

