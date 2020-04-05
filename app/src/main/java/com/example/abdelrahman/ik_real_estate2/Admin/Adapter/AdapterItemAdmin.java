package com.example.abdelrahman.ik_real_estate2.Admin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdelrahman.ik_real_estate2.Admin.Activity.ItemDetailsAdminActivity;
import com.example.abdelrahman.ik_real_estate2.Moudel.Item;
import com.example.abdelrahman.ik_real_estate2.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterItemAdmin extends RecyclerView.Adapter<AdapterItemAdmin.Holder>
{
    public ArrayList<Item> array;
    public Context context;

    public AdapterItemAdmin(ArrayList<Item> paramArrayList, Context paramContext)
    {
        this.array = paramArrayList;
        this.context = paramContext;
    }

    public String formatString(String paramString)
    {
        String str = paramString.toString();
        paramString = str;
        if (str.contains(",")) {
            paramString = str.replaceAll(",", "");
        }
        long l = Long.parseLong(paramString);
        return new DecimalFormat("#,###,###").format(Long.valueOf(l));
    }

    public int getItemCount()
    {
        return this.array.size();
    }

    public void onBindViewHolder(@NonNull Holder paramHolder, int paramInt)
    {
        Item localItem = (Item)this.array.get(paramInt);
        final Intent localIntent = new Intent(this.context, ItemDetailsAdminActivity.class);
        localIntent.putExtra("IntentItemAdmin", localItem);
        paramHolder.person.setText(localItem.getMember_name());
        paramHolder.title.setText(localItem.getTitle());
        String str = localItem.getPrice();
        paramHolder.price.setText(formatString(str).concat(" EGP"));
        paramHolder.space.setText(localItem.getSpace().concat(" m"));
        paramHolder.location.setText(localItem.getLocation());
        paramHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                AdapterItemAdmin.this.context.startActivity(localIntent);
            }
        });
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt)
    {
        return new Holder(LayoutInflater.from(this.context).inflate(R.layout.item_admin, paramViewGroup, false));
    }

    public class Holder
            extends RecyclerView.ViewHolder
    {
        TextView location;
        TextView person;
        TextView price;
        TextView space;
        TextView title;

        public Holder(View paramView)
        {
            super(paramView);
            this.person = ((TextView)paramView.findViewById(R.id.textpersonListItemActivityAdmin));
            this.title = ((TextView)paramView.findViewById(R.id.TextTitleItemListAdmin));
            this.price = ((TextView)paramView.findViewById(R.id.TextPriceItemListAdmin));
            this.space = ((TextView)paramView.findViewById(R.id.TextSpaceListItemAdmin));
            this.location = ((TextView)paramView.findViewById(R.id.textLocationListItemActivityAdmin));
        }
    }
}
