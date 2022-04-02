package com.example.pfe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

public class Objectif extends AppCompatActivity {
ImageView goal;
RecyclerView recyclerView;
ArrayList nom,note,montant,categ,progress,date;
ModifierRecycleGoal modifierRecycleGoal;
db2 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        masquer la barre li lfoug
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectif);
        db=new db2(this);
        nom=new ArrayList();
        note=new ArrayList();
        montant=new ArrayList();
        categ=new ArrayList();
        progress=new ArrayList();
        date=new ArrayList<>();
        afficher();
        recyclerView=findViewById(R.id.recycle);
        modifierRecycleGoal=new ModifierRecycleGoal(this,nom,montant,note,categ,date);
        recyclerView.setAdapter(modifierRecycleGoal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    public  void afficher(){

        Cursor cursor=db.AfficherGoal();
        if(cursor.getCount()!=0){

            findViewById(R.id.t2).setVisibility(View.GONE);

            findViewById(R.id.t1).setVisibility(View.GONE);
            findViewById(R.id.goal).setVisibility(View.GONE);

        while (cursor.moveToNext()){
            String nomgoal=cursor.getString(cursor.getColumnIndex("nom"));
            Double total=Double.parseDouble(cursor.getString(cursor.getColumnIndex("total")));
            String description=cursor.getString(cursor.getColumnIndex("description"));
            String catego=cursor.getString(cursor.getColumnIndex("categ"));
            String Date=cursor.getString(cursor.getColumnIndex("date"));

            nom.add(nomgoal);
note.add(description);
montant.add(total);
categ.add(catego);
date.add(Date);

        }

        }
    }
    public void retour(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }


public void addgoal(View view){startActivity(new Intent(getApplicationContext(),ChoixGoal.class));}
}