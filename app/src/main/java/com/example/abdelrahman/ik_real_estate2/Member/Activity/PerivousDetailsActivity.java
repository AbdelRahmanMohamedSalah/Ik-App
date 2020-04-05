package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PerivousDetailsActivity extends AppCompatActivity {
    EditText EtFeedBack;
    DatabaseReference databaseReference;
    ImageView ic_back;
    ImageView ic_delete;
    Button save;
    TextView textClientName;
    TextView textClientPhone;
    TextView textCode;
    TextView textComment;
    TextView textDate;
    TextView textOwnerName;
    TextView textOwnerPhone;
    TextView textTime;
    Intent intent;
    Perivous perivousIntent;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perivous_details);
        perivousIntent = (Perivous) getIntent().getSerializableExtra("ItemPervious");
        this.textCode = ((TextView) findViewById(R.id.textCodePerivousDetailsActivity));
        this.textOwnerName = ((TextView) findViewById(R.id.textOwnerNamePerivousDetailsActivity));
        this.textOwnerPhone = ((TextView) findViewById(R.id.textOwnerPhonePerivousDetailsActivity));
        this.textClientName = ((TextView) findViewById(R.id.textClientNamePerivousDetailsActivity));
        this.textClientPhone = ((TextView) findViewById(R.id.textClientPhonePerivousDetailsActivity));
        this.textTime = ((TextView) findViewById(R.id.textTimePerivousDetailsActivity));
        this.textDate = ((TextView) findViewById(R.id.textDatePerivousDetailsActivity));
        this.textComment = ((TextView) findViewById(R.id.textCommentPerivousDetailsActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backPerivousDetailsActivity));
        this.ic_delete = ((ImageView) findViewById(R.id.ic_DeletePerivousDetailsActicity));

        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                PerivousDetailsActivity.this.onBackPressed();
            }
        });
        this.textCode.setText(perivousIntent.getCode().toString());
        this.textClientName.setText(perivousIntent.getClientName());
        this.textClientPhone.setText(perivousIntent.getClientPhone());
        this.textOwnerName.setText(perivousIntent.getOwnerName());
        this.textOwnerPhone.setText(perivousIntent.getOwnerPhone());
        this.textTime.setText(perivousIntent.getTime());
        TextView localTextView = this.textDate;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(perivousIntent.getMonth());
        localStringBuilder.append(" / ");
        localStringBuilder.append(perivousIntent.getDay());
        localTextView.setText(localStringBuilder.toString());
        this.textComment.setText(perivousIntent.getComment());

        this.textOwnerPhone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", PerivousDetailsActivity.this.textOwnerPhone.getText().toString(), null));
                PerivousDetailsActivity.this.startActivity(intent);
            }
        });
        this.textClientPhone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", PerivousDetailsActivity.this.textClientPhone.getText().toString(), null));
                PerivousDetailsActivity.this.startActivity(intent);
            }
        });
        this.ic_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(PerivousDetailsActivity.this);
                alert.setMessage("هل انت متاكد من مسح المعاينه ");
                alert.setCancelable(true);
                alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                        Dialog dialog = new Dialog(PerivousDetailsActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
                        dialog.setContentView(R.layout.layout_feed_back);

                        PerivousDetailsActivity.this.EtFeedBack = ((EditText) dialog.findViewById(R.id.EtLayoutFeedBack));
                        progressBar = dialog.findViewById(R.id.ProgressBarLayoutFeedBack);
                        progressBar.setVisibility(View.GONE);
                        PerivousDetailsActivity.this.save = ((Button) dialog.findViewById(R.id.BtnSaveLayoutFeedBack));

                        dialog.show();

                        PerivousDetailsActivity.this.save.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramAnonymous3View) {
                                if (!PerivousDetailsActivity.this.EtFeedBack.getText().toString().isEmpty()) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    PerivousDetailsActivity.this.databaseReference = FirebaseDatabase.getInstance().getReference();
                                    Map<String, Object> map = new HashMap();
                                    map.put("code", perivousIntent.getCode());
                                    map.put("afterPrevious", EtFeedBack.getText().toString());
                                    map.put("clientMemberId", perivousIntent.getClientMemberId());
                                    map.put("clientName", perivousIntent.getClientName());
                                    map.put("clientPhone", perivousIntent.getClientPhone());
                                    map.put("comment", perivousIntent.getComment());
                                    map.put("day", perivousIntent.getDay());
                                    map.put("memberName", perivousIntent.getMemberName());
                                    map.put("month", perivousIntent.getMonth());
                                    map.put("ownerName", perivousIntent.getOwnerName());
                                    map.put("ownerPhone", perivousIntent.getOwnerPhone());
                                    map.put("periviewMemberId", perivousIntent.getPeriviewMemberId());
                                    map.put("requestId", perivousIntent.getRequestId());
                                    map.put("state", perivousIntent.getState());
                                    map.put("time", perivousIntent.getTime());
                                    PerivousDetailsActivity.this.databaseReference.child("Perivous_recycle").child(perivousIntent.getRequestId()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                PerivousDetailsActivity.this.databaseReference.child("Perivous_Respons").child(perivousIntent.getRequestId()).removeValue();
                                                PerivousDetailsActivity.this.onBackPressed();
                                            }
                                        }
                                    });
                                    PerivousDetailsActivity.this.onBackPressed();
                                    return;
                                } else {
                                    PerivousDetailsActivity.this.EtFeedBack.setError("Field is Empty");
                                }
                            }
                        });
                    }
                });
                alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                        paramAnonymous2DialogInterface.cancel();
                    }
                });
                alert.create().show();
            }
        });
    }
}
