package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.R;

public class AppartmentTypeActivity extends AppCompatActivity {

    ImageView ic_back;
    TextView TypeN, TypeS, TypeL;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appartment_type);
        intent = new Intent(AppartmentTypeActivity.this, ListItemActivity.class);
        ic_back = findViewById(R.id.ic_backAppartmentTypeActivity);
        TypeL = findViewById(R.id.textTypeLAppartmentTypeActivity);
        TypeS = findViewById(R.id.textTypeSAppartmentTypeActivity);
        TypeN = findViewById(R.id.textTypeNAppartmentTypeActivity);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        intent.putExtra("intent", "شقق");
        TypeN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("Type", "N");
                startActivity(intent);
            }
        });
        TypeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("Type", "L");
                startActivity(intent);
            }
        });  TypeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("Type", "S");
                startActivity(intent);
            }
        });
    }
}
