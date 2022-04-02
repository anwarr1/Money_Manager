package com.example.pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ChoixHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //        Masquer la barre li lfoq
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_history);
    }


    public void choosedate(View v){
        Intent intent=new Intent(this,History.class);
        startActivity(intent);}
    public void weekHisto(View v){
        Intent intent=new Intent(this,week_HistoryDep.class);
        startActivity(intent);}
    public void monthHisto(View v){
        Intent intent=new Intent(this,Month_HistoryDep.class);
        startActivity(intent);}

    public void todayHisto(View v){
        Intent intent=new Intent(this,Today_HistoryDep.class);
        startActivity(intent);}
}