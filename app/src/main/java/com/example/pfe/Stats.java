package com.example.pfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NonNls;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

public class Stats extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MainActivity h;
    BottomNavigationView bottomNavigationView;
Dialog dialog;
db2 db;
    TextView Loyer, food, Vetement, Sport,sante,autre;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_stats);
        drawerLayout=findViewById(R.id.drawer_layout);

bottomNavigationView=findViewById(R.id.bottom_nav);
bottomNavigationView.setSelectedItemId(R.id.dep);
db=new db2(this);
mouni(this);
LogOut();

bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.dep:
                startActivity(new Intent(getApplicationContext(),Stats.class));
                overridePendingTransition(0,0);
                return true;
            case R.id.rev:
                startActivity(new Intent(getApplicationContext(),stats2.class));
                overridePendingTransition(0,0);
                return true;

        }
        return false;
    }
});



       Loyer= findViewById(R.id.loyer);
       Vetement = findViewById(R.id.vetements);
        Sport = findViewById(R.id.sprt);
        food= findViewById(R.id.food);
        sante=findViewById(R.id.sante);
        autre=findViewById(R.id.autre);
        pieChart = findViewById(R.id.piechartDep);



        setData();
    }


    private void setData()
    {

        db2 db=new db2(this);



        food.setText( String.format("%.1f",new Float(db.PourcentageCategorieDepense("Fast Food"))));
        Sport.setText(String.format("%.1f",new Float(db.PourcentageCategorieDepense("Sport"))));
        Vetement.setText(String.format("%.1f",new Float(db.PourcentageCategorieDepense("Shopping"))));
        autre.setText(String.format("%.1f",new Float(db.PourcentageCategorieDepense("Autre"))));
        sante.setText(String.format("%.1f",new Float(db.PourcentageCategorieDepense("Santé"))));
        Loyer.setText(String.format("%.1f",new Float(db.PourcentageCategorieDepense("Loyer"))));

        pieChart.addPieSlice(
                new PieModel(Float.parseFloat(Loyer.getText().toString())
                        ,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        Float.parseFloat(Sport.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(

                        Float.parseFloat(Vetement.getText().toString()),
                        Color.parseColor("#024265")));
        pieChart.addPieSlice(
                new PieModel(
                       Float.parseFloat(food.getText().toString()),

                        Color.parseColor("#29B6F6")));


        pieChart.addPieSlice(
                new PieModel(
                        Float.parseFloat(sante.getText().toString()),

                        Color.parseColor("#9DD562")));
        pieChart.addPieSlice(
                new PieModel(
                        Float.parseFloat(autre.getText().toString()),

                        Color.parseColor( "#95928F")));

        // To animate the pie chart
        pieChart.startAnimation();
    }


    public void ClickMenu(View v)
    {h.openDrawer(drawerLayout);

    }

    public void ClickLogo(View view){
        h.closeDrawer(drawerLayout);


    }
    public  void ClickHome(View view){

        redirect(this,MainActivity.class);


    }
    public void ClickRevenus(View view){
        redirect(this,AjoutRevenu.class);

    }
    public void ClickDepenses(View view){
        redirect(this, ChoixSaisiDep.class);

    }
    public void  ClickStatistiques(View view){
        redirect(this,Stats.class);


    }

    public void  ClickHistory(View view){
        redirect(this,ChoixHistory.class);


    }

    public void  ClickGoal(View view){


        redirect(this,Objectif.class);

    }
    public void  DeleteAll(View view){

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.confirmersupprimer);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.modifier_revenu));}
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.show();
        dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.SupprimerAll();
                dialog.dismiss();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
        dialog.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();


            }
        });

    }
    public void redirect(Activity activity, Class classs) {
        Intent intent=new Intent(activity,classs);
        startActivity(intent);


    }
    public  void LogOut(){
        TextView logout;
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager.getInstance().logOut();
                Intent intent =new Intent(getApplicationContext(),Log.class);
                startActivity(intent);
                finish();


            }
        });}
    public void mouni(Context x){

        ImageView profil;
        TextView name;
        name=findViewById(R.id.name);
        profil=findViewById(R.id.profil);

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {


                        try { String id=me.getString("id");
                            name.setText(response.getJSONObject().getString("name"));

                            String profileImageUrl = "https://graph.facebook.com/"+id+"/picture?type=large";
//                            Picasso.with(x).load(profileImageUrl).transform(new RoundedCornersTransformation()).resize(100, 100).into(profil);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        GraphRequest.executeBatchAsync(request);
    }

}