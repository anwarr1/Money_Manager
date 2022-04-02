package com.example.pfe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AjoutRevenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText ValeurRevenu,DescriptionRevenu;
    db2 db;
Button insertRev;
Dialog dialog,dialogerreur;
Revenu revenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        masquer la barre lfoq
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_revenu);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this,R.array.categrev,android.R.layout.simple_spinner_item);
        Spinner spinner=findViewById(R.id.spinner);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinner.setAdapter(adapter) ;
        ValeurRevenu=findViewById(R.id.Trevenu);
        DescriptionRevenu=findViewById(R.id.descRev);
spinner.setOnItemSelectedListener(this);
//ta=findViewById(R.id.ta);
        db=new db2(this);
        insertRev=findViewById(R.id.insertRev);

}


public void Onback(View v){

    Intent intent=new Intent(this,MainActivity.class);
    startActivity(intent);


}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        insertRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categorie=parent.getItemAtPosition(position).toString();
                Double valeur=Double.parseDouble(ValeurRevenu.getText().toString());
                String description=DescriptionRevenu.getText().toString();


                revenu=new Revenu(categorie,valeur,description);
                if(valeur>0){

                    db.insertRevenu(revenu);
                    ValeurRevenu.setText("");
                    DescriptionRevenu.setText("");



                  ouvrirboite();


                } else
                {
                    ouvrirboiteerreur();
                }


            }
        });

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void ouvrirboite() {
        dialog=new Dialog(AjoutRevenu.this);
        dialog.setContentView(R.layout.dialogue);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.modifier_revenu));}
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.show();
        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    private void ouvrirboiteerreur() {
        dialogerreur=new Dialog(AjoutRevenu.this);
        dialogerreur.setContentView(R.layout.dialogueerreur);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialogerreur.getWindow().setBackgroundDrawable(getDrawable(R.drawable.modifier_revenu));}
        dialogerreur.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogerreur.setCancelable(true);
        dialogerreur.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialogerreur.show();
        dialogerreur.findViewById(R.id.okerreur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogerreur.dismiss();
            }
        });



    }

}


