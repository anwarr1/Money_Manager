package com.example.pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Saisir_objectif extends AppCompatActivity {
Goal goal ;
db2 db;
EditText nomgoal,totalgoal,desc;
   TextView objectif, selectedgoal;
   ImageView imagegoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        masquer la barre li lfoug
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisir_objectif);
        imagegoal=findViewById(R.id.imagegoal);
        objectif=findViewById(R.id.namegoal);
        selectedgoal=findViewById(R.id.selectedgoal);


        Bundle bundle=getIntent().getExtras();
        String objectif_nom=bundle.getString("nom goal");
        objectif.setText(objectif_nom);



        Bundle bundlepic=getIntent().getExtras();
        String objectif_im=bundlepic.getString("image goal");
        selectedgoal.setText(objectif_im);
        if(selectedgoal.getText().toString().equals("Nouvelle voiture"))
            imagegoal.setImageResource(R.drawable.car);

      else if(selectedgoal.getText().toString().equals("Nouvelle maison"))
            imagegoal.setImageResource(R.drawable.house);

        else if(selectedgoal.getText().toString().equals("Fete"))
            imagegoal.setImageResource(R.drawable.party);

       else if(selectedgoal.getText().toString().equals("Education"))
            imagegoal.setImageResource(R.drawable.school);

       else if(selectedgoal.getText().toString().equals("Voyage"))
            imagegoal.setImageResource(R.drawable.holiday);
        else
            imagegoal.setVisibility(View.GONE);

    }
    public void insertobj(View view){
        nomgoal=findViewById(R.id.nomgoal);
        desc=findViewById(R.id.descGoal);
        totalgoal=findViewById(R.id.Montant);
        selectedgoal=findViewById(R.id.selectedgoal);

        db=new db2(this);
        String nomGoal= objectif.getText().toString();
        Double valeur=Double.parseDouble(totalgoal.getText().toString());
        String description=desc.getText().toString();
String categ=selectedgoal.getText().toString();
        goal=new Goal(nomGoal,valeur,description,categ);

        db.insertgoal(goal);
    }
    public void creergoal(View view){
        nomgoal=findViewById(R.id.nomgoal);
        desc=findViewById(R.id.descGoal);
        totalgoal=findViewById(R.id.Montant);
        selectedgoal=findViewById(R.id.selectedgoal);
        db=new db2(this);
        String nomGoal= objectif.getText().toString();
        Double valeur=Double.parseDouble(totalgoal.getText().toString());
        String description=desc.getText().toString();
        String categ=selectedgoal.getText().toString();

        goal=new Goal(nomGoal,valeur,description,categ);

        db.insertgoal(goal);
        totalgoal.setText("");
        desc.setText("");
        Toast.makeText(this,"Objectif ajouté avec succes",Toast.LENGTH_LONG).show();
    }
    public void retour(View view){
startActivity(new Intent(getApplicationContext(),Objectif.class));

}

}