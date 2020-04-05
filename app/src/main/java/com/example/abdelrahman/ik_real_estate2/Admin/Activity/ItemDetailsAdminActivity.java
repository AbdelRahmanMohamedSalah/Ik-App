package com.example.abdelrahman.ik_real_estate2.Admin.Activity;

import android.app.Dialog;
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
import android.widget.Toast;

import com.example.abdelrahman.ik_real_estate2.Member.Activity.ListItemActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

public class ItemDetailsAdminActivity extends AppCompatActivity {

    Button BTsave;
    EditText ETcode;
    TextView Member;
    TextView OwnerName;
    TextView OwnerPhone;
    TextView TBathRooms;
    TextView TLevel;
    TextView TOption;
    TextView TRooms;
    TextView bathrooms;
    TextView code;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    TextView details;
    ImageView ic_accept;
    ImageView ic_back;
    ImageView ic_refused;
    ImageView ic_share;
    TextView level;
    TextView location;
    TextView option;
    TextView price;
    TextView rooms;
    TextView space;
    TextView time;
    TextView title;
    TextView type;
    View view1;
    View view2;
    View view3;
    View view4;
    Item intent;

    public String formatString(String paramString) {
        String str = paramString.toString();
        paramString = str;
        if (str.contains(",")) {
            paramString = str.replaceAll(",", "");
        }
        long l = Long.parseLong(paramString);
        return new DecimalFormat("#,###,###").format(Long.valueOf(l));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details_admin);
        intent = (Item) getIntent().getSerializableExtra("IntentItemAdmin");
        this.title = ((TextView) findViewById(R.id.textTitleItemDetailsAdminActivity));
        this.code = ((TextView) findViewById(R.id.textCodeItemDetailsAdminActivity));
        this.price = ((TextView) findViewById(R.id.textPriceItemDetailsAdminActivity));
        this.location = ((TextView) findViewById(R.id.textLocationItemDetailsAdminActivity));
        this.rooms = ((TextView) findViewById(R.id.textRoomItemDetailsAdminActivity));
        this.bathrooms = ((TextView) findViewById(R.id.textBathroomItemDetailsAdminActivity));
        this.level = ((TextView) findViewById(R.id.textLevelItemDetailsAdminActivity));
        this.space = ((TextView) findViewById(R.id.textSpaceItemDetailsAdminActivity));
        this.details = ((TextView) findViewById(R.id.textDetailsItemDetailsAdminActivity));
        this.time = ((TextView) findViewById(R.id.textTimeItemDetailsAdminActivity));
        this.option = ((TextView) findViewById(R.id.textOptionItemDetailsAdminActivity));
        this.type = ((TextView) findViewById(R.id.textTypeItemDetailsAdminActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backItemDetailsAdminActivity));
        this.ic_share = ((ImageView) findViewById(R.id.ic_shareItemDetailsAdminActivity));
        this.OwnerName = ((TextView) findViewById(R.id.textOwnerNameItemDetailsAdminActivity));
        this.OwnerPhone = ((TextView) findViewById(R.id.textOwnerPhoneItemDetailsAdminActivity));
        this.Member = ((TextView) findViewById(R.id.textMemberItemDetailsAdminActivity));
        this.ic_accept = ((ImageView) findViewById(R.id.ic_acceptItem));
        this.ic_refused = ((ImageView) findViewById(R.id.ic_refeuusedItem));
        this.TLevel = ((TextView) findViewById(R.id.TextLevelItemDetailsAdminActivity));
        this.TRooms = ((TextView) findViewById(R.id.TextRoomsItemDetailsAdminActivity));
        this.TBathRooms = ((TextView) findViewById(R.id.TextBathRoomsItemDetailsAdminActivity));
        this.TOption = ((TextView) findViewById(R.id.TextOptionsItemDetailsAdminActivity));
        this.view1 = findViewById(R.id.View1ItemDetailsAdminActivity);
        this.view2 = findViewById(R.id.View2ItemDetailsAdminActivity);
        this.view3 = findViewById(R.id.View3ItemDetailsAdminActivity);
        this.view4 = findViewById(R.id.View4ItemDetailsAdminActivity);
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Data").child(intent.getId());
        String str;
        if (intent.getType().equals("اراضي")) {
            this.TLevel.setVisibility(View.GONE);
            this.TOption.setVisibility(View.GONE);
            this.TBathRooms.setVisibility(View.GONE);
            this.TRooms.setVisibility(View.GONE);
            this.view1.setVisibility(View.GONE);
            this.view2.setVisibility(View.GONE);
            this.view3.setVisibility(View.GONE);
            this.view4.setVisibility(View.GONE);
            this.level.setVisibility(View.GONE);
            this.option.setVisibility(View.GONE);
            this.rooms.setVisibility(View.GONE);
            this.bathrooms.setVisibility(View.GONE);
            this.OwnerPhone.setText(intent.getOwner_phone());
            this.OwnerName.setText(intent.getOwner_name());
            this.type.setText(intent.getType());
            this.Member.setText(intent.getMember_name());
            this.title.setText(intent.getTitle());
            this.code.setText(intent.getCode());
            str = intent.getPrice();
            this.price.setText(formatString(str).concat(" EGP"));
            this.location.setText(intent.getLocation());
            this.space.setText(intent.getSpace().concat(" m"));
            this.details.setText(intent.getDetails());
            this.time.setText(intent.getTime());
        } else {
            this.level.setVisibility(View.VISIBLE);
            this.option.setVisibility(View.VISIBLE);
            this.rooms.setVisibility(View.VISIBLE);
            this.bathrooms.setVisibility(View.VISIBLE);
            this.TLevel.setVisibility(View.VISIBLE);
            this.TOption.setVisibility(View.VISIBLE);
            this.TBathRooms.setVisibility(View.VISIBLE);
            this.TRooms.setVisibility(View.VISIBLE);
            this.view1.setVisibility(View.VISIBLE);
            this.view2.setVisibility(View.VISIBLE);
            this.view3.setVisibility(View.VISIBLE);
            this.view4.setVisibility(View.VISIBLE);
            this.type.setText(intent.getType());
            this.OwnerPhone.setText(intent.getOwner_phone());
            this.OwnerName.setText(intent.getOwner_name());
            this.Member.setText(intent.getMember_name());
            this.title.setText(intent.getTitle());
            this.code.setText(intent.getCode());
            str = intent.getPrice();
            this.price.setText(formatString(str).concat(" EGP"));
            this.location.setText(intent.getLocation());
            this.bathrooms.setText(intent.getBathRoom());
            this.rooms.setText(intent.getRoom());
            this.level.setText(intent.getLevel());
            this.space.setText(intent.getSpace().concat(" m"));
            this.details.setText(intent.getDetails());
            this.time.setText(intent.getTime());
            this.option.setText(intent.getOption());
        }
        this.ic_accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                final Dialog dialog = new Dialog(ItemDetailsAdminActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
                dialog.setContentView(R.layout.layout_code);
                ItemDetailsAdminActivity.this.ETcode = ((EditText) dialog.findViewById(R.id.ETCodeItemDetailsAdminActivity));
                ItemDetailsAdminActivity.this.BTsave = ((Button) dialog.findViewById(R.id.BtnCodeItemDetailsAdminActivity));
                progressBar = dialog.findViewById(R.id.progressLayoutCodeItemDetailsAdminActivity);
                progressBar.setVisibility(View.GONE);

                dialog.show();
                ItemDetailsAdminActivity.this.BTsave.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramAnonymous2View) {
                        progressBar.setVisibility(View.VISIBLE);
                        if (!ItemDetailsAdminActivity.this.ETcode.getText().toString().isEmpty()) {
                            if (!ItemDetailsAdminActivity.this.ETcode.getText().toString().contains(" ")) {
                                ItemDetailsAdminActivity.this.databaseReference.child("code").setValue(ItemDetailsAdminActivity.this.ETcode.getText().toString().toUpperCase()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    public void onComplete(@NonNull Task<Void> paramAnonymous3Task) {
                                        if (paramAnonymous3Task.isSuccessful()) {
                                            ItemDetailsAdminActivity.this.databaseReference.child("state").setValue("1");
                                            ItemDetailsAdminActivity.this.onBackPressed();
                                            new Intent(ItemDetailsAdminActivity.this, ListItemActivity.class).putExtra("intent", intent.getType());

                                        } else {
                                            dialog.hide();
                                            Toast.makeText(ItemDetailsAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            } else {
                                ItemDetailsAdminActivity.this.ETcode.setError("remove space ");
                            }
                        } else {
                            ItemDetailsAdminActivity.this.ETcode.setError("code is Empty");
                        }
                    }
                });
            }
        });
        this.OwnerPhone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent2 = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", OwnerPhone.getText().toString(), null));
                startActivity(intent2);
            }
        });
        if (intent.state.equals("1")) {
            this.ic_refused.setVisibility(View.INVISIBLE);
            this.ic_accept.setVisibility(View.INVISIBLE);
        }
        this.ic_refused.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ItemDetailsAdminActivity.this.finish();
            }
        });
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ItemDetailsAdminActivity.this.onBackPressed();
            }
        });
    }
}