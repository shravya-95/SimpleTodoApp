package com.example.simpletodo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    ArrayList<String> items;
    OnLongClickListener longClickListener;

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    public ItemsAdapter(ArrayList<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Create a inflator
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);

        //wrap view in a viewholder and return
        return new ViewHolder(todoView);
    }
    // bidning data to a view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        
        //get item which we want to bind
        String item = items.get(position);
        //bind item to view holder
        holder.bind(item);

    }
    //tells rv how mnay items in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);

        }

        // update view inside the view holder with the data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify listener which position was long clicked
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
