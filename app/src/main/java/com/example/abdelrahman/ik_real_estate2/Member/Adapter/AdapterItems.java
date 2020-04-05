package com.example.abdelrahman.ik_real_estate2.Member.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Member.Activity.ItemDetailsActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterItems
        extends RecyclerView.Adapter<AdapterItems.Holder> {
    public Activity activity;
    public ArrayList<Item> array;
    public ArrayList<Item> arrayList_copy;
    public Context context;


    public AdapterItems(ArrayList<Item> paramArrayList1, ArrayList<Item> paramArrayList2, Context paramContext, Activity paramActivity) {
        this.array = paramArrayList1;
        this.arrayList_copy = paramArrayList1;
        this.context = paramContext;
        this.activity = paramActivity;
    }


    public String formatString(String paramString) {
        String str = paramString.toString();
        paramString = str;
        if (str.contains(",")) {
            paramString = str.replaceAll(",", "");
        }
        long l = Long.parseLong(paramString);
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat formatter = (DecimalFormat)nf;
        formatter.applyPattern("#,###,###");
        String fString = formatter.format(Long.valueOf(l));
        return fString;
    }

    public void onBindViewHolder(@NonNull Holder paramHolder, int paramInt) {
        Item localItem = (Item) this.array.get(paramInt);
        paramHolder.code.setText(localItem.getCode());
        paramHolder.title.setText(localItem.getTitle());
        final Object localObject = localItem.getPrice();
        paramHolder.price.setText(formatString((String) localObject).concat(" EGP"));
        paramHolder.space.setText(localItem.getSpace().concat(" m"));
        paramHolder.location.setText(localItem.getLocation());
        final Intent intent = new Intent(this.context, ItemDetailsActivity.class);

        intent.putExtra("IntentItem", localItem);
        paramHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AdapterItems.this.context.startActivity(intent);
            }
        });
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt) {
        return new Holder(LayoutInflater.from(this.context).inflate(R.layout.item, paramViewGroup, false));
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView code;
        TextView location;
        TextView price;
        TextView space;
        TextView title;

        public Holder(View paramView) {
            super(paramView);
            this.code = ((TextView) paramView.findViewById(R.id.TextCodeItemList));
            this.title = ((TextView) paramView.findViewById(R.id.TextTitleItemList));
            this.price = ((TextView) paramView.findViewById(R.id.TextPriceItemList));
            this.space = ((TextView) paramView.findViewById(R.id.TextSpaceListItem));
            this.location = ((TextView) paramView.findViewById(R.id.textLocationListItemActivity));


        }
    }

    public int getItemCount() {
        return array.size();
    }


    public Filter getFilter(final int filterType) {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                //  ArrayList<Item> array = new ArrayList<>();
                if (constraint.length() == 0) {
                    array = arrayList_copy;

                } else {

                    if(filterType == 0){
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
                AdapterItems.this.notifyDataSetChanged();
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


