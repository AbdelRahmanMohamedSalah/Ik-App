package com.example.abdelrahman.ik_real_estate2.Admin.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Member.Activity.ItemDetailsActivity;
import com.example.abdelrahman.ik_real_estate2.Member.Activity.TrendingMemberActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.Holder> {

    Context context;
    ArrayList<Item> arrayList;
    DatabaseReference databaseReference;
    Activity activity;

    public TrendingAdapter(Context context, ArrayList<Item> arrayList, Activity activity) {
        this.context = context;
        this.arrayList = arrayList;
        this.activity = activity;
    }

    public String formatString(String paramString) {
        String str = paramString.toString();
        paramString = str;
        if (str.contains(",")) {
            paramString = str.replaceAll(",", "");
        }
        long l = Long.parseLong(paramString);
        return new DecimalFormat("#,###,###").format(Long.valueOf(l));
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new TrendingAdapter.Holder(LayoutInflater.from(this.context).inflate(R.layout.trending_item, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        final Item itemObject = arrayList.get(i);
        holder.code.setText(itemObject.getCode());
        holder.title.setText(itemObject.getTitle());
        holder.location.setText(itemObject.getLocation());
        holder.price.setText(formatString((String) itemObject.getPrice()).concat(" EGP"));
        holder.space.setText(itemObject.getSpace().toString().concat("m"));
        if (activity instanceof TrendingMemberActivity) {
            holder.delete.setVisibility(View.INVISIBLE);
        } else {
            holder.delete.setVisibility(View.VISIBLE);
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("Trending");
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("هل انت متاكد من مسح " + itemObject.getCode());
                alert.setCancelable(true);
                alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {

                        databaseReference.child(itemObject.getId()).removeValue();
                    }
                });
                alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        paramAnonymousDialogInterface.cancel();
                    }
                });
                alert.create().show();


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                intent.putExtra("IntentItem", itemObject);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView code, title, location, price, space;
        ImageView delete;

        public Holder(View paramView) {
            super(paramView);

            code = paramView.findViewById(R.id.TextCodeTrendingItemAdminActivity);
            title = paramView.findViewById(R.id.TextTitleTrendingItemAdminActivity);
            location = paramView.findViewById(R.id.TextLocationchangeTrendingItemAdminActivity);
            price = paramView.findViewById(R.id.TextPriceTrendingItemAdminActivity);
            space = paramView.findViewById(R.id.TextSpaceTrendingItemAdminActivity);
            delete = paramView.findViewById(R.id.icDeleteTrendingItemAdminActivity);


        }

    }
}
