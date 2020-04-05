package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.Moudel.Users;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PeriviousAdminDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Map<String, String> MapUser;
    Spinner MemberPerview;
    TextView PeriviewMember;
    DatabaseReference UsersReferance;
    ImageView accept;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    TextView clientName;
    TextView clientPhone;
    TextView code;
    TextView comment;
    TextView date;
    ImageView ic_back;
    String member;
    TextView memberName;
    String ownerName;
    String ownerPhone;
    DatabaseReference perivousData;
    ProgressBar progressBar;
    ImageView refused;
    TextView time;
    Perivous intentPerview;
    int foundItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perivious_admin_details);
        this.code = ((TextView) findViewById(R.id.textCodePeriviousAdminDetailsActivity));
        this.PeriviewMember = ((TextView) findViewById(R.id.textMemberPeriviewPeriviousAdminDetailsActivity));
        this.clientName = ((TextView) findViewById(R.id.textclientNamePeriviousAdminDetailsActivity));
        this.clientPhone = ((TextView) findViewById(R.id.textClientPhonePeriviousAdminDetailsActivity));
        this.time = ((TextView) findViewById(R.id.textTimePeriviousAdminDetailsActivity));
        this.date = ((TextView) findViewById(R.id.textDatePeriviousAdminDetailsActivity));
        this.memberName = ((TextView) findViewById(R.id.textMemberClientPeriviousAdminDetailsActivity));
        this.comment = ((TextView) findViewById(R.id.textCommentPeriviousAdminDetailsActivity));
        this.MemberPerview = ((Spinner) findViewById(R.id.spinnerMemberPerviewPeriviousAdminDetailsActivity));
        this.accept = ((ImageView) findViewById(R.id.ic_acceptPeriviousAdminDetailsActivity));
        this.refused = ((ImageView) findViewById(R.id.ic_refeuusedPeriviousAdminDetailsActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backPerivousAdminDetailsActivity));
        this.progressBar = ((ProgressBar) findViewById(R.id.progressbarPerivousAdminDetailsActivity));

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.arrayList = new ArrayList();
        this.MapUser = new HashMap();
        this.progressBar.setVisibility(View.GONE);
        this.perivousData = FirebaseDatabase.getInstance().getReference();

        intentPerview = (Perivous) getIntent().getSerializableExtra("previewDetails");

        this.UsersReferance = FirebaseDatabase.getInstance().getReference();
        this.UsersReferance.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError paramAnonymousDatabaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                PeriviousAdminDetailsActivity.this.arrayList.clear();
                PeriviousAdminDetailsActivity.this.arrayList.add("other");
//                Iterator localIterator = dataSnapshot.child("Users").getChildren().iterator();
//                while (localIterator.hasNext()) {
                // Users localUsers = (Users) ((DataSnapshot) localIterator.next()).getValue(Users.class);
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("Users").getChildren()) {
                    Users localUsers = new Users(
                            dataSnapshot1.child("email").getValue(String.class),
                            dataSnapshot1.child("Fname").getValue(String.class),
                            dataSnapshot1.child("key").getValue(String.class),
                            dataSnapshot1.child("Lname").getValue(String.class),
                            dataSnapshot1.child("mainAdmin").getValue(String.class),
                            dataSnapshot1.child("name").getValue(String.class),
                            dataSnapshot1.child("phone").getValue(String.class),
                            dataSnapshot1.child("type").getValue(String.class)
//                    Users localUsers=dataSnapshot1.getValue(Users.class);
                    );
                    PeriviousAdminDetailsActivity.this.arrayList.add(localUsers.getName());
                    PeriviousAdminDetailsActivity.this.MapUser.put(localUsers.getName(), localUsers.getKey());
//                }
                    System.out.println("map Name =" + MapUser.get(intentPerview.getPeriviewMemberId()));
                }
                PeriviousAdminDetailsActivity.this.adapter = new ArrayAdapter(PeriviousAdminDetailsActivity.this, android.R.layout.simple_list_item_checked, PeriviousAdminDetailsActivity.this.arrayList);
                PeriviousAdminDetailsActivity.this.adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
                PeriviousAdminDetailsActivity.this.MemberPerview.setAdapter(PeriviousAdminDetailsActivity.this.adapter);
                PeriviousAdminDetailsActivity.this.member = ((String) dataSnapshot.child("Users").child(intentPerview.getClientMemberId()).child("name").getValue(String.class));
                PeriviousAdminDetailsActivity.this.memberName.setText(PeriviousAdminDetailsActivity.this.member);
                PeriviousAdminDetailsActivity.this.PeriviewMember.setText(PeriviousAdminDetailsActivity.this.member);
            }
        });
        if (intentPerview.getPeriviewMemberId().toString().equals("other")) {
            this.MemberPerview.setVisibility(View.VISIBLE);
            this.PeriviewMember.setVisibility(View.GONE);
            System.out.println("other Visible");
        } else {
            System.out.println("other un visible");
            this.MemberPerview.setVisibility(View.GONE);
            this.PeriviewMember.setVisibility(View.VISIBLE);
        }
        this.code.setText(intentPerview.getCode().toString());
        this.clientName.setText(intentPerview.getClientName().toString());
        this.clientPhone.setText(intentPerview.getClientPhone().toString());
        this.time.setText(intentPerview.getTime().toString());
        TextView localTextView = this.date;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(intentPerview.getDay().concat("/"));
        localStringBuilder.append(intentPerview.getMonth().toString());
        localTextView.setText(localStringBuilder.toString());
        this.comment.setText(intentPerview.getComment().toString());

        this.accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                PeriviousAdminDetailsActivity.this.progressBar.setVisibility(View.VISIBLE);

                if ((PeriviousAdminDetailsActivity.this.MemberPerview.getVisibility() == View.VISIBLE) && (PeriviousAdminDetailsActivity.this.MemberPerview.getSelectedItem().toString().equals("other"))) {
                    Toast.makeText(PeriviousAdminDetailsActivity.this, "Choose member", Toast.LENGTH_SHORT).show();
                    PeriviousAdminDetailsActivity.this.progressBar.setVisibility(View.GONE);
                    PeriviousAdminDetailsActivity.this.accept.setEnabled(true);

                } else {
                    PeriviousAdminDetailsActivity.this.perivousData.child("Data").addValueEventListener(new ValueEventListener() {
                        public void onCancelled(DatabaseError paramAnonymous2DatabaseError) {
                        }

                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Perivous localObject = dataSnapshot1.getValue(Perivous.class);

                                if (dataSnapshot1.child("code").getValue().equals(intentPerview.getCode().toString().toUpperCase())) {
                                    foundItem = 1;
                                    System.out.println("code equal ");
                                    Toast.makeText(PeriviousAdminDetailsActivity.this, "saved", Toast.LENGTH_SHORT).show();
                                    PeriviousAdminDetailsActivity.this.ownerName = ((String) dataSnapshot1.child("owner_name").getValue(String.class));
                                    PeriviousAdminDetailsActivity.this.ownerPhone = ((String) dataSnapshot1.child("owner_phone").getValue(String.class));
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("afterPrevious", "0");
                                    map.put("clientMemberId", intentPerview.getClientMemberId());
                                    map.put("clientName", intentPerview.getClientName());
                                    map.put("clientPhone", intentPerview.getClientPhone());
                                    map.put("code", intentPerview.getCode());
                                    map.put("comment", intentPerview.getComment());
                                    map.put("day", intentPerview.getDay());
                                    map.put("memberName", "0");
                                    map.put("month", intentPerview.getMonth());
                                    StringBuilder localStringBuilder = new StringBuilder();
                                    localStringBuilder.append(PeriviousAdminDetailsActivity.this.ownerName);
                                    localStringBuilder.append("");
                                    map.put("ownerName", localStringBuilder.toString());
                                    localStringBuilder = new StringBuilder();
                                    localStringBuilder.append(PeriviousAdminDetailsActivity.this.ownerPhone);
                                    localStringBuilder.append("");
                                    map.put("ownerPhone", localStringBuilder.toString());

                                    if (PeriviousAdminDetailsActivity.this.MemberPerview.getVisibility() == View.GONE) {
                                        map.put("periviewMemberId", intentPerview.getPeriviewMemberId());
                                    } else {
                                        map.put("periviewMemberId", PeriviousAdminDetailsActivity.this.MapUser.get(PeriviousAdminDetailsActivity.this.MemberPerview.getSelectedItem().toString()));
                                        System.out.println("MEMBER = " + MapUser.get(PeriviousAdminDetailsActivity.this.MemberPerview.getSelectedItem().toString()));
                                    }

                                    map.put("requestId", intentPerview.getRequestId());
                                    map.put("state", "1");
                                    map.put("time", intentPerview.getTime());
                                    if (PeriviousAdminDetailsActivity.this.MemberPerview.getSelectedItem().toString().equals("other")) {
                                        PeriviousAdminDetailsActivity.this.perivousData.child("Perivous_recycle").child(intentPerview.getRequestId()).setValue(map);
                                    }
                                    PeriviousAdminDetailsActivity.this.perivousData.child("Perivous_Request").child(intentPerview.getRequestId()).removeValue();
                                    PeriviousAdminDetailsActivity.this.perivousData.child("Perivous_Respons").child(intentPerview.getRequestId()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                PeriviousAdminDetailsActivity.this.progressBar.setVisibility(View.GONE);
//                                                PeriviousAdminDetailsActivity.this.startActivity(new Intent(PeriviousAdminDetailsActivity.this, PerivousAdminActivity.class));
//                                                PeriviousAdminDetailsActivity.this.finish();
                                                //return;
                                            onBackPressed();
                                            }
                                            PeriviousAdminDetailsActivity.this.progressBar.setVisibility(View.VISIBLE);
                                            PeriviousAdminDetailsActivity.this.accept.setEnabled(true);
                                        }
                                    });
                                }
                            }
                            if (foundItem == 0) {
                                progressBar.setVisibility(View.GONE);
                                accept.setEnabled(true);
                                Toast.makeText(PeriviousAdminDetailsActivity.this, "This code not exists", Toast.LENGTH_SHORT).show();
                                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(PeriviousAdminDetailsActivity.this);
                                alert.setMessage("هل تريد مسح هذه المعاينه الخاطئه ؟");
                                alert.setCancelable(true);
                                alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                                        PeriviousAdminDetailsActivity.this.perivousData.child("Perivous_Request").child(intentPerview.getRequestId()).removeValue();
                                        PeriviousAdminDetailsActivity.this.perivousData.child("Perivous_Respons").child(intentPerview.getRequestId()).removeValue();
                                    startActivity(new Intent(PeriviousAdminDetailsActivity.this,PerivousAdminActivity.class));
                                    finish();
                                    }
                                });
                                alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                                        paramAnonymousDialogInterface.cancel();
                                    }
                                });
                                alert.create().show();


                            }
                        }
                    });
                    //}
                }
            }
        });
        this.clientPhone.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View paramAnonymousView) {
                Intent intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", clientPhone.getText().toString(), null));
                startActivity(intent);

            }
        });
        this.refused.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View paramAnonymousView) {
                PeriviousAdminDetailsActivity.this.startActivity(new Intent(PeriviousAdminDetailsActivity.this, PerivousAdminActivity.class));
                PeriviousAdminDetailsActivity.this.finish();
            }
        });
    }

    public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    }

    @SuppressLint({"ResourceType"})
    public void onNothingSelected(AdapterView<?> paramAdapterView) {
    }
}

