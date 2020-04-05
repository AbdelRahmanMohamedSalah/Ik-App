package com.example.abdelrahman.ik_real_estate2.Member.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;

import java.text.DecimalFormat;

public class ItemDetailsActivity extends AppCompatActivity {
    TextView TBathRooms;
    TextView TLevel;
    TextView TOption;
    TextView TRooms;
    TextView bathrooms;
    TextView code;
    TextView details;
    ImageView ic_back;
    ImageView ic_share;
    TextView level;
    TextView location;
    TextView option;
    TextView price;
    TextView rooms;
    public String sharing;
    TextView space;
    TextView time;
    TextView title;
    View view1;
    View view2;
    View view3;
    View view4;

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
        setContentView(R.layout.activity_item_details);


        final Item intentItem = (Item) getIntent().getSerializableExtra("IntentItem");
        this.title = ((TextView) findViewById(R.id.textTitleItemDetailsActivity));
        this.code = ((TextView) findViewById(R.id.textCodeItemDetailsActivity));
        this.price = ((TextView) findViewById(R.id.textPriceItemDetailsActivity));
        this.location = ((TextView) findViewById(R.id.textLocationItemDetailsActivity));
        this.rooms = ((TextView) findViewById(R.id.textRoomItemDetailsActivity));
        this.bathrooms = ((TextView) findViewById(R.id.textBathroomItemDetailsActivity));
        this.level = ((TextView) findViewById(R.id.textLevelItemDetailsActivity));
        this.space = ((TextView) findViewById(R.id.textSpaceItemDetailsActivity));
        this.details = ((TextView) findViewById(R.id.textDetailsItemDetailsActivity));
        this.time = ((TextView) findViewById(R.id.textTimeItemDetailsActivity));
        this.option = ((TextView) findViewById(R.id.textOptionItemDetailsActivity));
        this.ic_back = ((ImageView) findViewById(R.id.ic_backItemDetailsActivity));
        this.ic_share = ((ImageView) findViewById(R.id.ic_shareItemDetailsActivity));
        this.TRooms = ((TextView) findViewById(R.id.TextRoomsItemDetailsActivity));
        this.TLevel = ((TextView) findViewById(R.id.TextLevelItemDetailsActivity));
        this.TBathRooms = ((TextView) findViewById(R.id.TextBathRoomsItemDetailsActivity));
        this.TOption = ((TextView) findViewById(R.id.TextOptionsItemDetailsActivity));
        this.view1 = findViewById(R.id.View1ItemDetailsActivity);
        this.view2 = findViewById(R.id.View2ItemDetailsActivity);
        this.view3 = findViewById(R.id.View3ItemDetailsActivity);
        this.view4 = findViewById(R.id.View4ItemDetailsActivity);

        this.ic_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (intentItem.getType().equals("اراضي")) {

                    sharing =
                            "1- العنوان: " +
                                    (ItemDetailsActivity.this.title.getText().toString()) +
                                    System.getProperty("line.separator") +
                                    "2- المساحه: " +
                                    ItemDetailsActivity.this.space.getText().toString() +
                                    System.getProperty("line.separator") +
                                    "3- التفاصيل: " +
                                    ItemDetailsActivity.this.details.getText().toString() +
                                    System.getProperty("line.separator") +
                                    "4- السعر: " +
                                    ItemDetailsActivity.this.price.getText().toString();

                } else {

                    sharing =
                            "1- العنوان: " +
                                    (ItemDetailsActivity.this.title.getText().toString()) +
                                    System.getProperty("line.separator") +
                                    "2- المساحه: " +
                                    (ItemDetailsActivity.this.space.getText().toString()) +
                                    System.getProperty("line.separator") +
                                    "3- الغرف: " + (ItemDetailsActivity.this.rooms.getText().toString()) +
                                    System.getProperty("line.separator") +

                                    "4- الحمامات: " + (ItemDetailsActivity.this.bathrooms.getText().toString()) +
                                    System.getProperty("line.separator") +

                                    "5- الدور: " + (ItemDetailsActivity.this.level.getText().toString()) +
                                    System.getProperty("line.separator") +

                                    "6- التشطيب: " + (ItemDetailsActivity.this.option.getText().toString()) +
                                    System.getProperty("line.separator") +
                                    "7- التفاصيل: " +
                                    (ItemDetailsActivity.this.details.getText().toString()) +
                                    System.getProperty("line.separator") +
                                    "8- السعر: " +
                                    (ItemDetailsActivity.this.price.getText().toString());


                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", sharing);
                intent.setType("text/plain");
                ItemDetailsActivity.this.startActivity(Intent.createChooser(intent, "send to"));
            }
        });
        String str;
        if (intentItem.getType().equals("اراضي")) {
            this.level.setVisibility(View.GONE);
            this.bathrooms.setVisibility(View.GONE);
            this.rooms.setVisibility(View.GONE);
            this.option.setVisibility(View.GONE);
            this.view1.setVisibility(View.GONE);
            this.view2.setVisibility(View.GONE);
            this.view3.setVisibility(View.GONE);
            this.view4.setVisibility(View.GONE);
            this.TRooms.setVisibility(View.GONE);
            this.TBathRooms.setVisibility(View.GONE);
            this.TOption.setVisibility(View.GONE);
            this.TLevel.setVisibility(View.GONE);
            this.title.setText(intentItem.getTitle());
            this.code.setText(intentItem.getCode());

            str = intentItem.getPrice();

            this.price.setText(formatString(str).concat(" EGP"));
            this.location.setText(intentItem.getLocation());
            this.space.setText(intentItem.getSpace().concat(" m"));
            this.details.setText(intentItem.getDetails());
            this.time.setText(intentItem.getTime());
        } else {
            this.level.setVisibility(View.VISIBLE);
            this.bathrooms.setVisibility(View.VISIBLE);
            this.rooms.setVisibility(View.VISIBLE);
            this.option.setVisibility(View.VISIBLE);
            this.view1.setVisibility(View.VISIBLE);
            this.view2.setVisibility(View.VISIBLE);
            this.view3.setVisibility(View.VISIBLE);
            this.view4.setVisibility(View.VISIBLE);
            this.TRooms.setVisibility(View.VISIBLE);
            this.TBathRooms.setVisibility(View.VISIBLE);
            this.TOption.setVisibility(View.VISIBLE);
            this.TLevel.setVisibility(View.VISIBLE);
            this.title.setText(intentItem.getTitle());
            this.code.setText(intentItem.getCode());
            str = intentItem.getPrice();
            this.price.setText(formatString(str).concat(" EGP"));
            this.location.setText(intentItem.getLocation());
            this.bathrooms.setText(intentItem.getBathRoom());
            this.rooms.setText(intentItem.getRoom());
            this.level.setText(intentItem.getLevel());
            this.space.setText(intentItem.getSpace().concat(" m"));
            this.details.setText(intentItem.getDetails());
            this.time.setText(intentItem.getTime());
            this.option.setText(intentItem.getOption());
        }
        new Intent(this, ListItemActivity.class).putExtra("intent", intentItem.getType());
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ItemDetailsActivity.this.onBackPressed();
            }
        });
    }
}

