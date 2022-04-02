package com.example.pfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
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

public class Week_History extends AppCompatActivity {
db2 db;
ArrayList <String>categorie,valeur,description,date,time;
ArrayList im;
ModifierRecycleRev modifierRecycle;
MainActivity h ;
DrawerLayout drawerLayout;
RecyclerView recyclerView;
TextView week;
BottomNavigationView NavHis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //        Masquer la barre li lfoq
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_history);
h=new MainActivity();
        drawerLayout=findViewById(R.id.drawer_layout);
        categorie=new ArrayList();
        valeur=new ArrayList();
        date=new ArrayList();
        time=new ArrayList<>();
        description=new ArrayList();
        recyclerView=findViewById(R.id.Recycle);
//        mouni(this);
        afficher();
        modifierRecycle=new ModifierRecycleRev(this,categorie,valeur,description,date,time);
        recyclerView.setAdapter(modifierRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



    }
    public  void afficher(){
        db=new db2(getApplicationContext());
        Cursor cursor=db.AfficherHistoRevWeek();

while (cursor.moveToNext()){

    categorie.add(cursor.getString(cursor.getColumnIndex("categorie")));
    valeur.add(cursor.getString(cursor.getColumnIndex("valeur")));
    description.add(cursor.getString(cursor.getColumnIndex("descr")));

    date.add(cursor.getString(cursor.getColumnIndex("date")));
    time.add(cursor.getString(cursor.getColumnIndex("time")));


}
Double s= 0.0;
for(int i=0;i<valeur.size();i++){

    s=Double.parseDouble(valeur.get(i))+s;

}

//week.setText(week.getText().toString()+" "+s.toString());
        NavHis=findViewById(R.id.NavHist);

        NavHis.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dep:
                        startActivity(new Intent(getApplicationContext(),week_HistoryDep.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.rev:
                        startActivity(new Intent(getApplicationContext(),Week_History.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


    }

    public void mouni(Context x){

        ImageView profil,prof;
        TextView name,logout,nom;

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager.getInstance().logOut();
                redirectActivity(Week_History.this,Log.class);
                finish();


            }
        });
        name=findViewById(R.id.name);
        profil=findViewById(R.id.profil);
        nom=findViewById(R.id.nom);
        prof=findViewById(R.id.prof);

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {
                        db2 db=new db2(getApplicationContext());

                        try { String id=me.getString("id");
                            name.setText(response.getJSONObject().getString("name"));
                            nom.setText(response.getJSONObject().getString("name"));


                            String profileImageUrl = "https://graph.facebook.com/"+id+"/picture?type=large";
//                            Picasso.with(x).load(profileImageUrl).transform(new RoundedCornersTransformation()).resize(100, 100).into(profil);
//                            Picasso.with(x).load(profileImageUrl).transform(new RoundedCornersTransformation()).resize(100, 100).into(prof);

                            String name=me.optString("name");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        GraphRequest.executeBatchAsync(request);
    }


    public  void ClickHome(View view){

        redirectActivity(this,MainActivity.class);


    }
    public void AddDep(View v){
        redirectActivity(this, ChoixSaisiDep.class);


    }

    public void AddRev(View v){
        redirectActivity(this,AjoutRevenu.class);

    }



    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }


    public static void openDrawer(DrawerLayout drawerLayout){

        drawerLayout.openDrawer(GravityCompat.START);

    }
    public  void ClickLogo(View view){
        closeDrawer(drawerLayout);


    }
    public   static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {drawerLayout.closeDrawer(GravityCompat.START);

        }
    }



    public void ClickRevenus(View view){
        redirectActivity(this,AjoutRevenu.class);

    }
    public void ClickDepenses(View view){
        redirectActivity(this, ChoixSaisiDep.class);

    }
    public void  ClickStatistiques(View view){
        redirectActivity(this,Stats.class);

    }

    public void  ClickHistory(View view){


        redirectActivity(this,ChoixHistory.class);

    }

    public void backhisto(View v) {
        redirectActivity(this,ChoixHistory.class);


    }


    public void redirectActivity(Activity activity, Class classs) {
        Intent intent=new Intent(activity,classs);
        startActivity(intent);


    }

}