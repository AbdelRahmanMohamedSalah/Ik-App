package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Moudel.Requests;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.auth.FirebaseAuth;

public class RequestDetailsActivity extends AppCompatActivity {
    TextView comment;
    TextView details;
    ImageView ic_back;
    TextView name;
    TextView phone;
    TextView price;
    Requests requests;
    TextView time;
    TextView title;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        this.title = ((TextView) findViewById(R.id.textTitleRequestDetailsActivity));
        this.details = ((TextView) findViewById(R.id.textDetailsRequestDetailsActivity));
        this.name = ((TextView) findViewById(R.id.textNameRequestDetailsActivity));
        this.phone = ((TextView) findViewById(R.id.textPhoneRequestDetailsActivity));
        this.price = ((TextView) findViewById(R.id.textPriceRequestDetailsActivity));
        this.comment = ((TextView) findViewById(R.id.textCommentRequestDetailsActivity));
        this.time = ((TextView) findViewById(R.id.textTimeRequestDetailsActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backRequestDetailsActivity));
        this.requests = ((Requests) getIntent().getSerializableExtra("Request_Details"));
        String type = getIntent().getStringExtra("Type");
        if (type.equals("member")) {
            name.setVisibility(View.GONE);
            phone.setVisibility(View.GONE);
        } else {
            name.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            this.name.setText(this.requests.getClientName().toString());
            this.phone.setText(this.requests.getClientPhone().toString());

        }
        this.title.setText(this.requests.getTitle().toString());
        this.price.setText(this.requests.getPrice().toString());
        this.details.setText(this.requests.getDetails().toString());
        this.comment.setText(this.requests.getComment().toString());
        this.time.setText(this.requests.getTime().toString());

        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                RequestDetailsActivity.this.onBackPressed();
            }
        });
    }
}

