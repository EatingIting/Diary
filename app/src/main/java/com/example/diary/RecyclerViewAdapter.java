package com.example.diary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface ItemClickListener {
        void onTitleClicked(int position);
    }

    private ArrayList<RecyclerViewItem> items;
    private Context context;
    public ItemClickListener mListener;

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public void setOnClickListener(ItemClickListener listener) {//인터페이스 연결
        mListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof RecyclerViewHolder) {
            ((RecyclerViewHolder) holder).tv.setText(items.get(position).getTitle());
            ((RecyclerViewHolder) holder).tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onTitleClicked(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (items == null) ? 0 : items.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.recyclerview_item);
        }
    }
}