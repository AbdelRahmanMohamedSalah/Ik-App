package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.abdelrahman.ik_real_estate2.Admin.Activity.MainActivityAdmin;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenActivity extends AppCompatActivity {


    FirebaseAuth.AuthStateListener authStateListener;
    Button btnRefresh;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.progressBar = ((ProgressBar) findViewById(R.id.progressBarSplashAcreen));
        this.btnRefresh = ((Button) findViewById(R.id.btnRefreshSplashActivity));
        this.progressBar.setVisibility(View.VISIBLE);
        this.btnRefresh.setVisibility(View.INVISIBLE);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.btnRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                SplashScreenActivity.this.finish();
                SplashScreenActivity.this.startActivity(SplashScreenActivity.this.getIntent());
            }
        });
        this.btnRefresh.setVisibility(View.INVISIBLE);
        this.progressBar.setVisibility(View.VISIBLE);

        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            public void onAuthStateChanged(@NonNull FirebaseAuth paramAnonymousFirebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    SplashScreenActivity.this.progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth = FirebaseAuth.getInstance();


                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(paramAnonymousFirebaseAuth.getCurrentUser().getUid().toString());
                    databaseReference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String type = dataSnapshot.child("type").getValue(String.class);
                            if (type.equals("member")) {
                                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                                SplashScreenActivity.this.startActivity(intent);
                                SplashScreenActivity.this.finish();
                                return;
                            }
                            if (type.equals("admin")) {
                                Intent intent = new Intent(SplashScreenActivity.this, MainActivityAdmin.class);
                                SplashScreenActivity.this.startActivity(intent);
                                SplashScreenActivity.this.finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        SplashScreenActivity.this.finish();
                    }
                }, 'ל');
            }
        };
    }

    protected void onStart() {
        super.onStart();
        this.firebaseAuth.addAuthStateListener(this.authStateListener);
    }
}

