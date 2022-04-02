package com.example.pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ChoixGoal extends AppCompatActivity {

TextView name,voyage,maison,party,education,tomobil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        masquer la barre li lfoug
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_goal);
      name=findViewById(R.id.nomgoal);
      voyage=findViewById(R.id.voyage);
        tomobil=findViewById(R.id.tomobil);
        party=findViewById(R.id.fete);
        education=findViewById(R.id.education);
        maison=findViewById(R.id.maison);



    }
    public void retour(View view){
        startActivity(new Intent(getApplicationContext(),Objectif.class));

    }


    public void house(View view){
        Intent intent=new Intent(getApplicationContext(),Saisir_objectif.class);
        intent.putExtra("nom goal",name.getText().toString());
        intent.putExtra("image goal",maison.getText().toString());

        startActivity(intent); }


    public void party(View view){Intent intent=new Intent(getApplicationContext(),Saisir_objectif.class);
        intent.putExtra("nom goal",name.getText().toString());
        intent.putExtra("image goal",party.getText().toString());

        startActivity(intent);;}
    public void car(View view){Intent intent=new Intent(getApplicationContext(),Saisir_objectif.class);
        intent.putExtra("nom goal",name.getText().toString());
        intent.putExtra("image goal",tomobil.getText().toString());

        startActivity(intent);}
    public void school(View view){Intent intent=new Intent(getApplicationContext(),Saisir_objectif.class);
        intent.putExtra("nom goal",name.getText().toString());
        intent.putExtra("image goal",education.getText().toString());

        startActivity(intent);}
    public void holiday(View view){Intent intent=new Intent(getApplicationContext(),Saisir_objectif.class);
        intent.putExtra("nom goal",name.getText().toString());
        intent.putExtra("image goal",voyage.getText().toString());

        startActivity(intent);}


}