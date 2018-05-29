package com.computadoras.computadoras;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

 public class ComputerAdapter extends RecyclerView.Adapter<ComputerAdapter.ComputerViewHolder> {
     private ArrayList<Computer> computers;
     private Resources res;
     private OnComputerClickListener clickListener;
     private String txtBrand[], txtColor[], txtType[], txtOS[];

     public ComputerAdapter(Context contexto, ArrayList<Computer> computers, OnComputerClickListener clickListener){
         this.computers = computers;
         res = contexto.getResources();
         this.clickListener = clickListener;
     }

     @Override
     public ComputerAdapter.ComputerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_computer, parent, false);
         return new ComputerViewHolder(v);
     }

     @Override
     public void onBindViewHolder(ComputerAdapter.ComputerViewHolder holder, int position) {
         final Computer c = computers.get(position);

         txtBrand = res.getStringArray(R.array.brand_array);
         txtColor = res.getStringArray(R.array.color_array);
         txtType = res.getStringArray(R.array.type_array);
         txtOS = res.getStringArray(R.array.os_array);

         holder.image.setImageDrawable(ResourcesCompat.getDrawable(res, c.getImage(), null));
         holder.ram.setText(c.getRam()+"GB");

         holder.color.setText(txtColor[c.getColor()]);

         holder.type.setText(txtType[c.getType()]);
         holder.OS.setText(txtOS[c.getOS()]);

         holder.v.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 clickListener.onComputerClick(c);
             }
         });
     }

     @Override
     public int getItemCount() {
         return computers.size();
     }

     public static class ComputerViewHolder extends RecyclerView.ViewHolder{
         private ImageView image;
         private TextView brand;
         private TextView ram;
         private TextView color;
         private TextView type;
         private TextView OS;
         private View v;

         public ComputerViewHolder(View itemView) {
             super(itemView);
             v = itemView;

             image = itemView.findViewById(R.id.image);
             brand = itemView.findViewById(R.id.lblBrand);
             ram = itemView.findViewById(R.id.lblRam);
             color = itemView.findViewById(R.id.lblColor);
             type = itemView.findViewById(R.id.lblType);
             OS = itemView.findViewById(R.id.lblOS);
         }
     }

     public interface OnComputerClickListener {
         void onComputerClick(Computer c);
     }
 }
