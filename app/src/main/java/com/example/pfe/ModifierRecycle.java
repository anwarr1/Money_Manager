package com.example.pfe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ModifierRecycle extends RecyclerView.Adapter<ModifierRecycle.History> {
db2 db;
    Context context;
    ArrayList categorie,valeur,description,date,time;

    public ModifierRecycle(Context context, ArrayList categorie, ArrayList valeur, ArrayList description, ArrayList date,ArrayList time) {
        this.context = context;
        this.categorie = categorie;
        this.valeur = valeur;
        this.description = description;
        this.date = date;
        this.time = time;

    }




    @NonNull
    @Override
    public History onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.recycle_ligne,parent,false);
        return new History(view);
    }

    @Override
    public void onBindViewHolder(@NonNull History holder, int position) {
db=new db2(context.getApplicationContext());
        holder.ca.setText(String.valueOf(categorie.get(position))+": ");

            holder.va.setText(String.valueOf(valeur.get(position))+"DH");
            holder.da.setText(String.valueOf(date.get(position)));
            holder.de.setText(String.valueOf(description.get(position)));
        holder.ti.setText(String.valueOf(time.get(position)));
        holder.corbeilledep.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            db.SupprimerHistoDep(holder.de.getText().toString());
                Intent intent=new Intent(context.getApplicationContext(),ChoixHistory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
        }

        });

    }

    @Override
    public int getItemCount() {
        return categorie.size();
    }

    public class History extends RecyclerView.ViewHolder {

        TextView ca,de,va,da,ti;
        ImageView corbeilledep;
        public History(@NonNull View itemView) {
            super(itemView);
            ca=itemView.findViewById(R.id.ca);
            da=itemView.findViewById(R.id.da);
            de=itemView.findViewById(R.id.de);
            va=itemView.findViewById(R.id.va);
            ti=itemView.findViewById(R.id.ti);
            corbeilledep=itemView.findViewById(R.id.corbeilledep);


        }
    }
}
