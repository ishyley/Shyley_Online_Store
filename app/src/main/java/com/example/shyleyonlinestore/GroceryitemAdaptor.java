package com.example.shyleyonlinestore;

import static com.example.shyleyonlinestore.GroceryItemActivity.GROCERY_ITEM_KEY;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class GroceryitemAdaptor extends RecyclerView.Adapter<GroceryitemAdaptor.ViewHolder> {

     private ArrayList<GroceryItem> items = new ArrayList<>();
     private Context context;

    public GroceryitemAdaptor(Context context) {
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_item, parent, false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtName.setText(items.get(position).getName());
        holder.txtPrice.setText(String.valueOf("₦"+items.get(position).getPrice()));
        Glide.with(context)
                .asBitmap()
                .load(items.get(position).getImageUrl())
                .into(holder.image);
        
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GroceryItemActivity.class);
                intent.putExtra(GROCERY_ITEM_KEY, items.get(position) );
                context.startActivity(intent);

                // TODO: 6/21/2022
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<GroceryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtPrice;
        private ImageView image;
        private MaterialCardView parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            image = itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
