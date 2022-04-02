package com.example.pfe;


import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class AjoutDepense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    db2 db;
    EditText ValeurDepense,descriptionDepense;
    Button insertDep;
    Depense depense;
    Dialog dialog,dialogerreur;
    Button ok,okerreur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //        masquer la barre lfoq
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_depense);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.categdep,android.R.layout.simple_spinner_item);
        Spinner spinner=findViewById(R.id.spinner);
        ValeurDepense=findViewById(R.id.Tdepense);
        descriptionDepense=findViewById(R.id.descDep);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter) ;
        spinner.setOnItemSelectedListener(this);

insertDep=findViewById(R.id.insertDep);


        db=new db2(this); }


    public void Onback(View v){

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);




    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        insertDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categorie=parent.getItemAtPosition(position).toString();
                Double valeur=Double.parseDouble(ValeurDepense.getText().toString());
                String description=descriptionDepense.getText().toString();


                depense=new Depense(categorie,valeur,description);
                if(valeur>0){
//**********************************************************************************Notification**************************************************************************************************


                    db.insertDepense(depense);
                    ValeurDepense.setText("");
                    descriptionDepense.setText("");
                    ouvrirboite();
                    if ( db.TotalRevenus()<db.TotalDepenses()) {Notification();
                        }




                }
                else {ouvrirboiteerreur(); }
            }}); }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void Notification(){NotificationManager mNotificationManager;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify_001");

        NotificationCompat.BigTextStyle text = new NotificationCompat.BigTextStyle();
        text.setBigContentTitle("Today");
        text.setSummaryText("Text in detail");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Avertissement");
        mBuilder.setContentText("Vos dépenses ont dépassé vos revenus");

        mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "anwar";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "anwarNotif",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());}

 public void ouvrirboite() {
     dialog=new Dialog(AjoutDepense.this);
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
        dialogerreur=new Dialog(AjoutDepense.this);
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