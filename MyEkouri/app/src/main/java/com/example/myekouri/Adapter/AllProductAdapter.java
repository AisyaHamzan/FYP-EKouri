package com.example.myekouri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myekouri.Domain.AllProductDomain;
import com.example.myekouri.R;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {

    Context context;
    List<AllProductDomain> allProductDomains;

    public AllProductAdapter(Context context, List<AllProductDomain> allProductDomains) {
        this.context = context;
        this.allProductDomains = allProductDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_allproduct,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.name.setText(allProductDomains.get(position).getName());
        holder.price.setText(String.valueOf(allProductDomains.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return allProductDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        ImageView nextBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.proName);
            price = itemView.findViewById(R.id.proPrice);
            nextBtn = itemView.findViewById(R.id.next);
        }
    }
}
