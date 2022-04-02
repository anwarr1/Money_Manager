
package com.example.pfe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

    public class ModifierRecycleGoal extends RecyclerView.Adapter<com.example.pfe.ModifierRecycleGoal.History> {
        db2 db;
        int i= 0;
        Context context;
        private Handler handler = new Handler();
        Dialog dialog;

        ArrayList nom,valeur,description,categ,date;

        public ModifierRecycleGoal(Context context, ArrayList nom, ArrayList valeur, ArrayList description, ArrayList categ,ArrayList date) {
            this.context = context;
            this.nom = nom;
            this.valeur = valeur;
            this.description = description;
            this.date=date;
            this.categ=categ;
            db=new db2(context.getApplicationContext());

        }



        @NonNull
        @Override
        public ModifierRecycleGoal.History onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            View view=layoutInflater.inflate(R.layout.recycle_ligne_goal,parent,false);
            return new ModifierRecycleGoal.History(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ModifierRecycleGoal.History holder, int position) {


            holder.categ.setVisibility(View.GONE);
            holder.insertbuttonajoute.setVisibility(View.GONE);
            holder.editmontantajout.setVisibility(View.GONE);
            holder.titre.setVisibility(View.GONE);

            holder.corbeille.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.anim);
                    holder.corbeille.startAnimation(animation);
                    db.SupprimerGoal(holder.nom.getText().toString());
                    Toast.makeText(context.getApplicationContext(), "Objectif supprimé",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(context.getApplicationContext(),Objectif.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.getApplicationContext().startActivity(intent);

                }
            });
            holder.date.setText(String.valueOf(date.get(position)));
            holder.note.setText(String.valueOf(description.get(position))+": ");
            holder.nom.setText(String.valueOf(nom.get(position)));
            holder.categ.setText(categ.get(position).toString());
           Double x=Double.parseDouble(valeur.get(position).toString());


            if (holder.categ.getText().toString().equals("Nouvelle voiture")){
            holder.goalpic.setImageResource(R.drawable.car); }

           else if (holder.categ.getText().toString().equals("Nouvelle maison")){
                holder.goalpic.setImageResource(R.drawable.house); }
            else if (holder.categ.getText().toString().equals("Voyage")){
                holder.goalpic.setImageResource(R.drawable.holiday); }
            else if (holder.categ.getText().toString().equals("Education")){
                holder.goalpic.setImageResource(R.drawable.school); }
            else if (holder.categ.getText().toString().equals("Fete")){
                holder.goalpic.setImageResource(R.drawable.party); }

            holder.ajoutmontant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.date.setVisibility(View.GONE);
                    holder.note.setVisibility(View.GONE);
                    holder.nom.setVisibility(View.GONE);
                    holder.progressBar.setVisibility(View.GONE);
                    holder.ajoutmontant.setVisibility(View.GONE);
                    holder.ajoutmontant.setVisibility(View.GONE);
                    holder.insertbuttonajoute.setVisibility(View.VISIBLE);
                    holder.editmontantajout.setVisibility(View.VISIBLE);
                    holder.titre.setVisibility(View.VISIBLE);
                    holder.flossrestant.setVisibility(View.GONE);

                }
            });


            Integer a=db.MontantObjetifAjoute(holder.nom.getText().toString());
            Integer b=db.MontantTotalObjetif(holder.nom.getText().toString());

//******************************************************************Modifier lprogress bar**************************************************************************************************************

            new Thread(new Runnable() {
                @Override
                public void run() {


                        handler.post(new Runnable() {
                            public void run() {
holder.progressBar.setMax(b);
                                if(b>=a)
                                {holder.progressBar.setProgress(a);
                               holder.progressBar.getProgressDrawable().setColorFilter(Color.rgb(34,139,34), PorterDuff.Mode.SRC_IN);
                                    holder.flossrestant.setText(a.toString()+"DH/"+b.toString()+"Dh");

                                    if((b/2)>a)
                                        holder.progressBar.getProgressDrawable().setColorFilter(Color.rgb(154,205,50), PorterDuff.Mode.SRC_IN);
                                    if((b/3)>a)
                                        holder.progressBar.getProgressDrawable().setColorFilter(Color.rgb(255,165,0), PorterDuff.Mode.SRC_IN);

                                    if((b/4)>a)
                                        holder.progressBar.getProgressDrawable().setColorFilter(Color.rgb(255,69,0), PorterDuff.Mode.SRC_IN);

                                }


                                else if (b<a){
                                    db.SupprimerGoal(holder.nom.getText().toString());
                                Toast.makeText(context.getApplicationContext(),"Objectif Terminé !",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(context.getApplicationContext(),Objectif.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                }

                            }
                        });
                    }
            }).start();
//  ****************************************************************************************************************************************************************************************

            holder.insertbuttonajoute.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    if(b>=a){
                        Intent intent=new Intent(context.getApplicationContext(),Objectif.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.getApplicationContext().startActivity(intent);
                    db.UpdateMontantAjout(Double.parseDouble(holder.editmontantajout.getText().toString()),holder.nom.getText().toString());  }

                    holder.date.setVisibility(View.VISIBLE);
                    holder.note.setVisibility(View.VISIBLE);
                    holder.nom.setVisibility(View.VISIBLE);
                    holder.progressBar.setVisibility(View.VISIBLE);
                    holder.ajoutmontant.setVisibility(View.VISIBLE);
                    holder.insertbuttonajoute.setVisibility(View.GONE);
                    holder.editmontantajout.setVisibility(View.GONE);
                    holder.titre.setVisibility(View.GONE);

                }

            });




        }

        @Override
        public int getItemCount() {
            return nom.size();
        }

        public class History extends RecyclerView.ViewHolder {

            TextView note,date,nom,categ,titre,flossrestant;
            EditText editmontantajout;
            ImageView goalpic,corbeille;
            ProgressBar progressBar;
Button ajoutmontant,insertbuttonajoute;
            public History(@NonNull View itemView) {
                super(itemView);
                nom=itemView.findViewById(R.id.goalname);
                date=itemView.findViewById(R.id.dategoal);
                ajoutmontant=itemView.findViewById(R.id.ajoutmontant);
                note=itemView.findViewById(R.id.goalnote);
                goalpic=itemView.findViewById(R.id.goalpic);
                categ=itemView.findViewById(R.id.categ);
                progressBar=itemView.findViewById(R.id.progressBar);
               insertbuttonajoute=itemView.findViewById(R.id.insertmontantajoute);
editmontantajout=(EditText) itemView.findViewById(R.id.editmontantajout);
             titre=itemView.findViewById(R.id.titre);
               flossrestant=itemView.findViewById(R.id.flossrestant);
                corbeille=itemView.findViewById(R.id.corbeille);


            }
        }
    }
