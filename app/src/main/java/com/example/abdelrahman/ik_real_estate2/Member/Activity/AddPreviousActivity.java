package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddPreviousActivity extends AppCompatActivity {
    EditText EtClientName;
    EditText EtClientPhone;
    EditText EtCode;
    EditText EtComment;
    EditText EtDay;
    EditText EtMonth;
    EditText EtTime;
    DatabaseReference databaseReference;
    DatabaseReference databaseRespons;
    FirebaseAuth firebaseAuth;
    ImageView ic_back;
    ImageView ic_save;
    ProgressBar progressBar;
    Spinner spinnerMember;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_previous);
        this.EtClientName = ((EditText) findViewById(R.id.EtClientNameAddPreviousActivity));
        this.EtClientPhone = ((EditText) findViewById(R.id.EtClientPhoneAddPreviousActivity));
        this.EtCode = ((EditText) findViewById(R.id.EtCodeAddPreviousActivity));
        this.EtComment = ((EditText) findViewById(R.id.EtCommentAddPreviousActivity));
        this.EtDay = ((EditText) findViewById(R.id.ETDayAddPerivousActivity));
        this.EtMonth = ((EditText) findViewById(R.id.ETMonthAddPerivousActivity));
        this.EtTime = ((EditText) findViewById(R.id.EtClockAddPreviousActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backAddPreviousActivity));
        this.ic_save = ((ImageView) findViewById(R.id.ic_saveAddPreviousActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progressBarAddPreviousActivity));
        this.spinnerMember = ((Spinner) findViewById(R.id.SpinnerMemberAddPreviousActivity));
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.progressBar.setVisibility(View.GONE);
        arrayMember = new ArrayList<>();
        arrayMember.add("انا");
        arrayMember.add("حد اخر");

        this.databaseReference = FirebaseDatabase.getInstance().getReference("Perivous_Request").push();
        this.databaseRespons = FirebaseDatabase.getInstance().getReference("Perivous_Respons");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, arrayMember);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerMember.setAdapter(arrayAdapter);
        this.ic_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View paramAnonymousView) {
                if (!AddPreviousActivity.this.EtCode.getText().toString().isEmpty()) {
                    if (!AddPreviousActivity.this.EtCode.getText().toString().contains(" ")) {
                        if (!AddPreviousActivity.this.EtClientName.getText().toString().isEmpty()) {
                            if (!AddPreviousActivity.this.EtClientPhone.getText().toString().isEmpty()) {
                                if (!AddPreviousActivity.this.EtTime.getText().toString().isEmpty()) {
                                    if (!AddPreviousActivity.this.EtDay.getText().toString().isEmpty()) {
                                        if (!AddPreviousActivity.this.EtMonth.getText().toString().isEmpty()) {
                                            AddPreviousActivity.this.progressBar.setVisibility(View.VISIBLE);
                                            AddPreviousActivity.this.ic_save.setEnabled(false);
                                            AddPreviousActivity.this.ic_back.setEnabled(false);
                                            final Map<String, Object> map = new HashMap();
                                            map.put("code", AddPreviousActivity.this.EtCode.getText().toString().toUpperCase());
                                            map.put("clientName", AddPreviousActivity.this.EtClientName.getText().toString());
                                            map.put("clientPhone", AddPreviousActivity.this.EtClientPhone.getText().toString());
                                            map.put("time", AddPreviousActivity.this.EtTime.getText().toString());
                                            map.put("day", AddPreviousActivity.this.EtDay.getText().toString());
                                            map.put("month", AddPreviousActivity.this.EtMonth.getText().toString());
                                            map.put("comment", AddPreviousActivity.this.EtComment.getText().toString());
                                            map.put("clientMemberId", AddPreviousActivity.this.firebaseAuth.getCurrentUser().getUid());
                                            if (AddPreviousActivity.this.spinnerMember.getSelectedItem().toString().equals("انا")) {
                                                map.put("periviewMemberId", AddPreviousActivity.this.firebaseAuth.getCurrentUser().getUid().toString());
                                            } else {
                                                map.put("periviewMemberId", "other");
                                            }
                                            map.put("state", "0");
                                            map.put("ownerPhone", "0");
                                            map.put("ownerName", "0");
                                            map.put("afterPrevious", "0");
                                            map.put("memberName", "0");
                                            map.put("requestId", AddPreviousActivity.this.databaseReference.getKey().toString());

                                           AddPreviousActivity.this.databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    AddPreviousActivity.this.databaseRespons.child(AddPreviousActivity.this.databaseReference.getKey().toString()).setValue(map);
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(AddPreviousActivity.this, "saved", Toast.LENGTH_SHORT).show();
                                                        AddPreviousActivity.this.onBackPressed();

                                                    } else {
                                                        Toast.makeText(AddPreviousActivity.this, "Error Connection", Toast.LENGTH_SHORT).show();
                                                        AddPreviousActivity.this.ic_save.setEnabled(true);
                                                        AddPreviousActivity.this.ic_back.setEnabled(true);
                                                        AddPreviousActivity.this.progressBar.setVisibility(View.INVISIBLE);
                                                    }
                                                }
                                            });

                                        } else {
                                            AddPreviousActivity.this.EtMonth.setError("Month Empty");
                                        }
                                    } else {
                                        AddPreviousActivity.this.EtDay.setError("Day Empty");
                                    }
                                } else {
                                    AddPreviousActivity.this.EtTime.setError("Time Empty");
                                }
                            } else {
                                AddPreviousActivity.this.EtClientPhone.setError("Client Phone Empty");
                            }
                        } else {
                            AddPreviousActivity.this.EtClientName.setError("Client Phone Empty");
                        }
                    } else {
                        AddPreviousActivity.this.EtCode.setError("remove space");
                    }
                } else {
                    AddPreviousActivity.this.EtCode.setError("Code Empty");
                }
            }
        });
    }
}
