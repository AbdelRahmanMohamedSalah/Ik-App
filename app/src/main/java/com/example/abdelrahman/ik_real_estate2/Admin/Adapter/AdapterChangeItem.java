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
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Admin.Activity.ChangeItemDetailsAdminActivity;
import com.example.abdelrahman.ik_real_estate2.Admin.Activity.ItemDetailsAdminActivity;
import com.example.abdelrahman.ik_real_estate2.Admin.Activity.ListChangeItemAdminActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterChangeItem
        extends RecyclerView.Adapter<AdapterChangeItem.Holder> {
    public ArrayList<Item> array;
    public ArrayList<Item> arrayList_copy;
    public Context context;
    DatabaseReference databaseReference;

    public AdapterChangeItem(ArrayList<Item> paramArrayList1, ArrayList<Item> paramArrayList2, Context paramContext) {
        this.array = paramArrayList1;
        this.arrayList_copy = paramArrayList2;
        this.context = paramContext;
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


    public int getItemCount() {
        return this.array.size();
    }


    public void onBindViewHolder(@NonNull Holder paramHolder, int paramInt) {
        final Item
                itemData = (Item) this.array.get(paramInt);
        System.out.println(itemData.toString());
        paramHolder.code.setText(itemData.getCode());
        System.out.println("code = " + itemData.getCode());


        paramHolder.title.setText(itemData.getTitle());

        final Object localObject = itemData.getPrice();
        paramHolder.price.setText(formatString((String) localObject).concat(" EGP"));
        paramHolder.space.setText(itemData.getSpace().concat(" m"));
        paramHolder.location.setText(itemData.getLocation());


        final Intent intent = new Intent(this.context, ItemDetailsAdminActivity.class);

        intent.putExtra("IntentItemAdmin", itemData);


        this.databaseReference = FirebaseDatabase.getInstance().getReference("Data");

        paramHolder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AdapterChangeItem.this.context);
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("هل انت متاكد من مسح");
                localStringBuilder.append(itemData.getCode());
                dialog.setMessage(localStringBuilder.toString());
                dialog.setCancelable(true);
                dialog.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                        AdapterChangeItem.this.databaseReference.child(itemData.getId()).removeValue();


                        context.startActivity(new Intent(context, ListChangeItemAdminActivity.class));
                        ((Activity) context).finish();

                    }
                });
                dialog.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                        paramAnonymous2DialogInterface.cancel();
                    }
                });
                dialog.create().show();
            }
        });
        paramHolder.edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent = new Intent(AdapterChangeItem.this.context, ChangeItemDetailsAdminActivity.class);
                intent.putExtra("intent_Data", itemData);
                AdapterChangeItem.this.context.startActivity(intent);
            }
        });
        paramHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AdapterChangeItem.this.context.startActivity(intent);
            }
        });
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt) {
        return new Holder(LayoutInflater.from(this.context).inflate(R.layout.item_change_details, paramViewGroup, false));
    }

    public class Holder
            extends RecyclerView.ViewHolder {
        TextView code;
        ImageView delete;
        ImageView edit;
        TextView location;
        TextView price;
        TextView space;
        TextView title;

        public Holder(View paramView) {
            super(paramView);
            this.code = ((TextView) paramView.findViewById(R.id.TextCodeItemDetailsAdminActivity));
            this.title = ((TextView) paramView.findViewById(R.id.TextTitleItemDetailsAdminActivity));
            this.price = ((TextView) paramView.findViewById(R.id.TextPriceItemDetailsAdminActivity));
            this.space = ((TextView) paramView.findViewById(R.id.TextSpaceItemDetailsAdminActivity));
            this.location = ((TextView) paramView.findViewById(R.id.TextLocationchangeItemDetailsAdminActivity));
            this.delete = ((ImageView) paramView.findViewById(R.id.icDeleteItemDetailsAdminActivity));
            this.edit = ((ImageView) paramView.findViewById(R.id.icEditItemDetailsAdminActivity));
        }
    }


    public Filter getFilter(final int filterType) {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                //  ArrayList<Item> array = new ArrayList<>();
                if (constraint.length() == 0) {
                    array = arrayList_copy;

                } else {

                    if (filterType == 0) {
                        array = getItemFilter(constraint.toString().toLowerCase());
                    } else {
                        array = getCodeFilter(constraint.toString().toLowerCase());
                    }

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = array;


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                array = (ArrayList<Item>) results.values;
                AdapterChangeItem.this.notifyDataSetChanged();
            }
        };


    }

    public ArrayList<Item> getItemFilter(String text) {

        ArrayList<Item> result = new ArrayList<>();
        for (Item it : arrayList_copy) {

            if (it.getTitle().toLowerCase().contains(text)) {

                result.add(it);

            }
        }


        return result;
    }


    public ArrayList<Item> getCodeFilter(String text) {

        ArrayList<Item> result = new ArrayList<>();
        for (Item it : arrayList_copy) {

            if (it.getCode().toLowerCase().contains(text)) {

                result.add(it);

            }
        }


        return result;
    }
}


