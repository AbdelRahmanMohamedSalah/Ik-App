package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddRequestActivity extends AppCompatActivity {

    String Name;
    EditText comment;
    DatabaseReference databaseReference;
    EditText details;
    FirebaseAuth firebaseAuth;
    ImageView ic_back;
    ImageView ic_save;
    EditText name;
    EditText phone;
    EditText price;
    ProgressBar progressBar;
    EditText title;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        this.title = ((EditText) findViewById(R.id.EtTitleAddRequestActivity));
        this.name = ((EditText) findViewById(R.id.EtNameAddRequestActivity));
        this.details = ((EditText) findViewById(R.id.EtDetailsAddRequestActivity));
        this.phone = ((EditText) findViewById(R.id.EtPhoneAddRequestActivity));
        this.price = ((EditText) findViewById(R.id.EtPriceAddRequestActivity));
        this.comment = ((EditText) findViewById(R.id.EtCommentAddRequestActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backAddRequestActivity));
        this.ic_save = ((ImageView) findViewById(R.id.ic_saveAddRequestActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progressBarAddRequestActivity));

        this.progressBar.setVisibility(View.INVISIBLE);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Requests").push();
        this.users = FirebaseDatabase.getInstance().getReference("Users").child(this.firebaseAuth.getCurrentUser().getUid());
        this.users.child("name").addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot paramAnonymousDataSnapshot) {
                AddRequestActivity.this.Name = ((String) paramAnonymousDataSnapshot.getValue(String.class));
            }
        });
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AddRequestActivity.this.onBackPressed();
            }
        });
        this.ic_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Object localObject = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Calendar localCalendar = Calendar.getInstance();
                if (!AddRequestActivity.this.title.getText().toString().isEmpty()) {
                    if (!AddRequestActivity.this.details.getText().toString().isEmpty()) {
                        if (!AddRequestActivity.this.price.getText().toString().isEmpty()) {
                            if (!AddRequestActivity.this.name.getText().toString().isEmpty()) {
                                if (!AddRequestActivity.this.phone.getText().toString().isEmpty()) {
                                    AddRequestActivity.this.progressBar.setVisibility(View.VISIBLE);
                                    AddRequestActivity.this.ic_save.setEnabled(false);
                                    AddRequestActivity.this.ic_back.setEnabled(false);
                                    Map<String, Object> map = new HashMap();
                                    map.put("title",title.getText().toString());
                                    map.put("details", details.getText().toString());
                                    map.put("clientName",name.getText().toString());
                                    map.put("price",price.getText().toString());
                                    map.put("clientPhone", phone.getText().toString());
                                    map.put("memberID", firebaseAuth.getCurrentUser().getUid().toString());
                                    StringBuilder localStringBuilder = new StringBuilder();
                                    localStringBuilder.append(((DateFormat) localObject).format(localCalendar.getTime()));
                                    localStringBuilder.append("");
                                    map.put("time", localStringBuilder.toString());
                                    localObject = new StringBuilder();
                                    ((StringBuilder) localObject).append(AddRequestActivity.this.comment.getText().toString());
                                    ((StringBuilder) localObject).append(" ");
                                    map.put("comment", ((StringBuilder) localObject).toString());
                                    map.put("id", AddRequestActivity.this.databaseReference.getKey().toString());
                                    map.put("memberName", AddRequestActivity.this.Name);
                                    AddRequestActivity.this.databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(AddRequestActivity.this, "تم الحفظ", Toast.LENGTH_SHORT).show();
                                                AddRequestActivity.this.onBackPressed();

                                            }else {
                                            AddRequestActivity.this.ic_save.setEnabled(true);
                                            AddRequestActivity.this.ic_back.setEnabled(true);
                                            AddRequestActivity.this.progressBar.setVisibility(View.INVISIBLE);
                                        }}
                                    });

                                }else {
                                    AddRequestActivity.this.phone.setError("field Empty");
                                }
                            }else {
                                AddRequestActivity.this.name.setError("field Empty");
                            }
                        }else {
                            AddRequestActivity.this.price.setError("field Empty");
                        }
                    }else {
                        AddRequestActivity.this.details.setError("field Empty");
                    }
                }else {
                AddRequestActivity.this.title.setError("field is Empty");
            }}
        });
    }
}

