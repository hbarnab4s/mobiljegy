package com.example.mobiljegy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

public class TicketItemAdapter extends RecyclerView.Adapter<TicketItemAdapter.ViewHolder> implements Filterable {
    private ArrayList<TicketItem> mTicketItemsData;
    private ArrayList<TicketItem> mTicketItemsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    TicketItemAdapter(Context context, ArrayList<TicketItem> itemsData) {
        this.mTicketItemsData = itemsData;
        this.mTicketItemsDataAll = itemsData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketItemAdapter.ViewHolder holder, int position) {
        TicketItem currentItem = mTicketItemsData.get(position);

        holder.bindTo(currentItem);

        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mTicketItemsData.size();
    }

    @Override
    public Filter getFilter() {
        return ticketFilter;
    }

    private Filter ticketFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<TicketItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mTicketItemsDataAll.size();
                results.values = mTicketItemsDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(TicketItem item : mTicketItemsDataAll) {
                    if(item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;

            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mTicketItemsData = (ArrayList) filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleText;
        private TextView mPriceText;
        private ImageView mItemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             mTitleText = itemView.findViewById(R.id.itemTitle);
             mPriceText = itemView.findViewById(R.id.price);
             mItemImage = itemView.findViewById(R.id.iconImage);

            itemView.findViewById(R.id.buy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Activity", "Buy button clicked");
                }
            });
        }

        public void bindTo(TicketItem currentItem) {
            mTitleText.setText(currentItem.getName());
            mPriceText.setText(currentItem.getPrice());

            Glide.with(mContext).load(currentItem.getImageResource()).into(mItemImage);

        }
    }

}


