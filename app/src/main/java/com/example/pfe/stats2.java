package com.example.pfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
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

import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class stats2 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    MainActivity h;
Dialog dialog;
db2 db;
    TextView bonus, salaire, renum, gift;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_stats2);
        drawerLayout=findViewById(R.id.drawer_layout2);
        bottomNavigationView=findViewById(R.id.bottom_nav2);
        bottomNavigationView.setSelectedItemId(R.id.rev);
        mouni(this);
        db=new db2(this);

        LogOut();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.rev:
                        startActivity(new Intent(getApplicationContext(),stats2.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.dep:
                        startActivity(new Intent(getApplicationContext(),Stats.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });



//
        gift = findViewById(R.id.gift);
        salaire = findViewById(R.id.salaire);
        bonus = findViewById(R.id.bonus);
        renum = findViewById(R.id.renum);
        pieChart = findViewById(R.id.piechart);


        setData();
    }

    private void setData()
    {        db2 db=new db2(this);


        gift.setText(String.format("%.1f",new Float(db.PourcentageCategorieRevenu("Cadeaux"))));
        bonus.setText(String.format("%.1f",new Float(db.PourcentageCategorieRevenu("Bonus"))));
        renum.setText(String.format("%.1f",new Float(db.PourcentageCategorieRevenu("Renumerations"))));
        salaire.setText(String.format("%.1f",new Float(db.PourcentageCategorieRevenu("Salaire"))));

        pieChart.addPieSlice(
                new PieModel(Float.parseFloat(salaire.getText().toString())
                        ,
                        Color.parseColor("#66BB6A")));



        pieChart.addPieSlice(
                new PieModel(Float.parseFloat(bonus.getText().toString())
                        ,
                        Color.parseColor("#EF5350")));

        pieChart.addPieSlice(
                new PieModel(Float.parseFloat(gift.getText().toString())
                        ,
        Color.parseColor("#FFA726")));

        pieChart.addPieSlice(
                new PieModel(Float.parseFloat(renum.getText().toString())
                        ,
                        Color.parseColor("#29B6F6")));


        pieChart.startAnimation();
    }


    public void ClickMenu(View v)
    {h.openDrawer(drawerLayout);

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