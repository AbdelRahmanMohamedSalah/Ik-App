package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Moudel.Perivous;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerPerivewMemberActivity extends AppCompatActivity {
    TextView AfterPervious;
    TextView MemberClient;
    TextView PeriviewMember;
    ImageView accept;
    ImageView btnDelete;
    TextView clientName;
    TextView clientPhone;
    TextView code;
    TextView comment;
    DatabaseReference databaseReference;
    TextView date;
    ImageView ic_back;
    ProgressBar progressBar;
    TextView time;
    Perivous IntentPerivous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_perivious_admin);
        this.AfterPervious = ((TextView)findViewById(R.id.textAfterPerviousRecyclePeriviousAdminActivity));
        this.code = ((TextView)findViewById(R.id.textCodeRecyclePeriviousAdminActivity));
        this.PeriviewMember = ((TextView)findViewById(R.id.textPeriviewMemberRecyclePeriviousAdminActivity));
        this.clientName = ((TextView)findViewById(R.id.textclientNameRecyclePeriviousAdminActivity));
        this.clientPhone = ((TextView)findViewById(R.id.textClientPhoneRecyclePeriviousAdminActivity));
        this.time = ((TextView)findViewById(R.id.textTimeRecyclePeriviousAdminActivity));
        this.date = ((TextView)findViewById(R.id.textDateRecyclePeriviousAdminActivity));
        this.comment = ((TextView)findViewById(R.id.textCommentRecyclePeriviousAdminActivity));
        this.MemberClient = ((TextView)findViewById(R.id.textMemberClientRecyclePeriviousAdminActivity));
        this.btnDelete = ((ImageView)findViewById(R.id.ic_refeuusedRecyclePeriviousAdminActivity));
        this.ic_back = ((ImageView)findViewById(R.id.ic_backRecyclePerivousAdminActivity));
        IntentPerivous = (Perivous)getIntent().getSerializableExtra("previewDetails");
        this.btnDelete.setVisibility(View.INVISIBLE);
        String str = getIntent().getStringExtra("RequestName");
        Object localObject = getIntent().getStringExtra("PerviewMember");
        this.code.setText(IntentPerivous.getCode());
        this.PeriviewMember.setText((CharSequence)localObject);
        this.clientName.setText(IntentPerivous.getClientName());
        this.clientPhone.setText(IntentPerivous.getClientPhone());
        this.time.setText(IntentPerivous.getTime());
        localObject = this.date;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(IntentPerivous.getMonth());
        localStringBuilder.append("/");
        localStringBuilder.append(IntentPerivous.getDay());
        ((TextView)localObject).setText(localStringBuilder.toString());
        this.comment.setText(IntentPerivous.getComment());
        this.MemberClient.setText(str);
        this.AfterPervious.setText(IntentPerivous.getAfterPrevious());
        this.ic_back.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                RecyclerPerivewMemberActivity.this.onBackPressed();
            }
        });
        this.btnDelete.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(RecyclerPerivewMemberActivity.this);
                alert.setMessage("هل انت متاكد من مسح المعاينه ");
                alert.setCancelable(true);
                alert.setPositiveButton("نعم", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                        RecyclerPerivewMemberActivity.this.databaseReference = FirebaseDatabase.getInstance().getReference().child("Perivous_recycle");
                        RecyclerPerivewMemberActivity.this.databaseReference.child(IntentPerivous.getRequestId()).removeValue();
                        RecyclerPerivewMemberActivity.this.onBackPressed();
                    }
                });
                alert.setNegativeButton("لا", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                    {
                        paramAnonymous2DialogInterface.cancel();
                    }
                });
                alert.create().show();
            }
        });
    }
}

