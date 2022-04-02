package com.example.pfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Month_HistoryDep extends AppCompatActivity {
    db2 db;
    ArrayList <String>categorie,valeur,description,date,time;
    ArrayList im;
    ModifierRecycle modifierRecycle;
    RecyclerView recyclerView;
    BottomNavigationView NavHis;
    MainActivity h;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //        Masquer la barre li lfoq
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_history_dep);
drawerLayout=findViewById(R.id.drawer_layout);
        categorie=new ArrayList();
        valeur=new ArrayList();
        date=new ArrayList();
        description=new ArrayList();
        time=new ArrayList<>();
        recyclerView=findViewById(R.id.Recycle);
        afficher();
        modifierRecycle=new ModifierRecycle(this,categorie,valeur,description,date,time);
        recyclerView.setAdapter(modifierRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        NavHis=findViewById(R.id.NavHist);

        NavHis.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dep:
                        startActivity(new Intent(getApplicationContext(),Month_HistoryDep.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.rev:
                        startActivity(new Intent(getApplicationContext(),Month_History.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


    }
    public  void afficher(){
        db=new db2(getApplicationContext());
        Cursor cursor=db.AfficherHistoDepMonth();

        while (cursor.moveToNext()){

            categorie.add(cursor.getString(cursor.getColumnIndex("categorie")));
            valeur.add(cursor.getString(cursor.getColumnIndex("valeur")));
            description.add(cursor.getString(cursor.getColumnIndex("descr")));

            date.add(cursor.getString(cursor.getColumnIndex("date")));
            time.add(cursor.getString(cursor.getColumnIndex("time")));


        }

    }

    public  void  backhisto(View v){redirect(this,ChoixHistory.class);}


    public void redirect(Activity activity, Class classs) {
        Intent intent=new Intent(activity,classs);
        startActivity(intent);

    }
}