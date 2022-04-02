package com.example.pfe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
Button Rrev,Rdep;
TextView totaldep,totalrev,totalrest;
    db2 db;
    User user;
Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        masquer la barre li lfoug
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
getSupportActionBar().hide();

setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        Rrev=findViewById(R.id.Rrev);
        Rdep=findViewById(R.id.Rdep);
        totaldep=findViewById(R.id.totaldep);
        totalrev=findViewById(R.id.totalrev);
        totalrest=findViewById(R.id.totalrest);
        db=new db2(this);


        mouni(this);
LogOut();
AfficherTotalDepense();
        AfficherTotalRevenus();
        AfficherTotalRestant();
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
            ImageView prof,profil;
            TextView name,nom;
            name=findViewById(R.id.name);
            profil=findViewById(R.id.profil);
            nom=findViewById(R.id.nom);
            prof=findViewById(R.id.prof);

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {

                        try {

                            String id=me.getString("id");;
                            name.setText(response.getJSONObject().getString("name"));
                            nom.setText(response.getJSONObject().getString("name"));
                            nom.setText(response.getJSONObject().getString("name"));

                          String profileImageUrl = "https://graph.facebook.com/1693413167658928/picture?type=large";

                           Picasso.with(MainActivity.this).load(profileImageUrl).transform(new RoundedCornersTransformation()).resize(100, 100).into(prof);
                            Picasso.with(MainActivity.this).load(profileImageUrl).transform(new RoundedCornersTransformation()).resize(100, 100).into(profil);



//prof.setProfileId(id);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        GraphRequest.executeBatchAsync(request);
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

    public   static void closeDrawer(DrawerLayout drawerLayout){
if(drawerLayout.isDrawerOpen(GravityCompat.START))
{drawerLayout.closeDrawer(GravityCompat.START);

    }
}

public  void ClickHome(View view){

        recreate();

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

    public void  ClickGoal(View view){


        redirectActivity(this,Objectif.class);

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

   public void redirectActivity(Activity activity, Class classs) {
Intent intent=new Intent(activity,classs);
        startActivity(intent);


    }
@Override
protected void onPause(){

        super.onPause();
        closeDrawer(drawerLayout);

}

    public void AfficherTotalDepense(){

        db2 db=new db2(this);

        String totaldepense=String.format("%.1f",db.TotalDepenses());
        totaldep.setText(totaldepense);

    }
        public void AfficherTotalRevenus(){
db2 db=new db2(this);

String totalrevenu=String.format("%.1f",db.TotalRevenus());
totalrev.setText(totalrevenu);


    }



    public void AfficherTotalRestant(){

     Double reste= db.TotalRevenus()-db.TotalDepenses();
     String Reste=String.format("%.1f",reste);

     totalrest.setText(Reste);
    }

}