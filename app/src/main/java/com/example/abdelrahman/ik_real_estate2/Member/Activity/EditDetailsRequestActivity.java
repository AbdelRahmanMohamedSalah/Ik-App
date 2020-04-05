package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Moudel.Requests;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditDetailsRequestActivity extends AppCompatActivity {

    EditText comment;
    DatabaseReference databaseReference;
    EditText details;
    FirebaseAuth firebaseAuth;
    ImageView ic_accept;
    ImageView ic_back;
    EditText name;
    EditText phone;
    EditText price;
    Requests requests;
    TextView time;
    EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details_request);
        this.title = ((EditText) findViewById(R.id.EtEditTitleRequestDetailsActivity));
        this.details = ((EditText) findViewById(R.id.EtEditDetailsRequestDetailsActivity));
        this.name = ((EditText) findViewById(R.id.EtEditNameRequestDetailsActivity));
        this.phone = ((EditText) findViewById(R.id.EtEditPhoneRequestDetailsActivity));
        this.comment = ((EditText) findViewById(R.id.EtEditCommentRequestDetailsActivity));
        this.price = ((EditText) findViewById(R.id.EtEditPriceRequestDetailsActivity));
        this.time = ((TextView) findViewById(R.id.textEditTimeRequestDetailsActivity));
        this.ic_accept = ((ImageView) findViewById(R.id.ic_acceptEditRequestDetailsActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backEditRequestDetailsActivity));
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.requests = ((Requests) getIntent().getSerializableExtra("Edit_Request_Details"));
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Requests").child(this.requests.getId().toString());
        this.title.setText(this.requests.getTitle());
        this.price.setText(this.requests.getPrice());
        this.details.setText(this.requests.getDetails());
        this.name.setText(this.requests.getClientName());
        this.phone.setText(this.requests.getClientPhone());
        this.comment.setText(this.requests.getComment());
        this.time.setText(this.requests.getTime());
        this.ic_accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!EditDetailsRequestActivity.this.title.getText().toString().isEmpty()) {
                    if (!EditDetailsRequestActivity.this.details.getText().toString().isEmpty()) {
                        if (!EditDetailsRequestActivity.this.name.getText().toString().isEmpty()) {
                            if (!EditDetailsRequestActivity.this.phone.getText().toString().isEmpty()) {
                                Map<String, Object> map = new HashMap();
                                map.put("clientName", EditDetailsRequestActivity.this.name.getText().toString());
                                map.put("clientPhone", EditDetailsRequestActivity.this.phone.getText().toString());
                                map.put("details", EditDetailsRequestActivity.this.details.getText().toString());
                                map.put("price", EditDetailsRequestActivity.this.price.getText().toString());
                                map.put("title", EditDetailsRequestActivity.this.title.getText().toString());
                                map.put("memberID", EditDetailsRequestActivity.this.requests.getMemberID());
                                map.put("id", EditDetailsRequestActivity.this.requests.getId());
                                map.put("comment", EditDetailsRequestActivity.this.comment.getText().toString());
                                map.put("time", EditDetailsRequestActivity.this.requests.getTime());
                                map.put("memberName", EditDetailsRequestActivity.this.requests.getMemberName());
                                EditDetailsRequestActivity.this.databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            EditDetailsRequestActivity.this.onBackPressed();
                                            Toast.makeText(EditDetailsRequestActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(EditDetailsRequestActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                EditDetailsRequestActivity.this.phone.setError("field is Empty");
                            }
                        } else {
                            EditDetailsRequestActivity.this.name.setError("field is Empty");
                        }
                    } else {
                        EditDetailsRequestActivity.this.details.setError("field is Empty");
                    }
                } else {
                    EditDetailsRequestActivity.this.title.setError("field is Empty");
                }
            }
        });
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                EditDetailsRequestActivity.this.onBackPressed();
            }
        });
    }
}