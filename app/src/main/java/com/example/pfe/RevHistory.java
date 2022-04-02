package com.example.pfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RevHistory extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MainActivity h;
    DatePickerDialog.OnDateSetListener setListener;
    TextView lbl;
    Button btn;
    int year;
    ListView listView;
    int day;
    int month;
    ArrayList list;
    BottomNavigationView NavHis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        Masquer la barre li lfoq
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_rev_history);
        drawerLayout=findViewById(R.id.drawer_layout);
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month =calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        NavHis=findViewById(R.id.NavHist);
        h=new MainActivity();
        mouni(this);
        NavHis.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dep:
                        startActivity(new Intent(getApplicationContext(),History.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.rev:
                        startActivity(new Intent(getApplicationContext(),RevHistory.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });




    }




    public void datte(View v){
        //Boutton pour choisir la date

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();

        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month =calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        listView=findViewById(R.id.listview); listView.setVisibility(View.GONE);


        drawerLayout=findViewById(R.id.drawer_layout);
        list=new ArrayList<>();
        lbl=findViewById(R.id.lbl);
        btn=findViewById(R.id.datebtn);
        NavHis=findViewById(R.id.NavHist);
        ArrayAdapter<String> adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,list);
        listView.setAdapter(adapter);
        db2 dbb=new db2(this);
        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                listView.setVisibility(View.VISIBLE);
                //                month++;
                Calendar cal = Calendar.getInstance();
                cal.set(year, month,dayOfMonth);
                CharSequence FormatDate = DateFormat.format("dd/MM/yyyy", cal);
                String date=FormatDate.toString();
                lbl.setText(date);

                CharSequence FormatDatee = DateFormat.format("yyyy-MM-dd", cal);
                String datee=FormatDatee.toString();



                Cursor c=dbb.AfficherHistoRev(datee);

                    while(c.moveToNext()) {
                        String column1 = c.getString(c.getColumnIndex("categorie"));
                        String column2 = c.getString(c.getColumnIndex("valeur"));
                        String column3=  c.getString(c.getColumnIndex("descr"));
                        String column4=  c.getString(c.getColumnIndex("time"));

                        SpannableString s1 = new SpannableString(column1+" :  ");
                        SpannableString s2 = new SpannableString("+"+column2+"DH   ");
                        SpannableString s3 = new SpannableString(column3+"   ");
                        SpannableString s4 = new SpannableString(column4+"\n");
                        int flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
                        s1.setSpan(new StyleSpan(Typeface.BOLD), 0, s1.length(), flag);
                        s2.setSpan(new TypefaceSpan("monospace"), 0, s2.length(), flag);
                        s2.setSpan(new ForegroundColorSpan(Color.rgb(0,127,0)), 0, s2.length(), flag);

                        s3.setSpan(new StyleSpan(Typeface.ITALIC), 0, s3.length(), flag);
                        s3.setSpan(new ForegroundColorSpan(Color.LTGRAY), 0, s3.length(), flag);
                        s3.setSpan(new StyleSpan(Typeface.BOLD), 0, s3.length(), flag);

                        SpannableStringBuilder builder = new SpannableStringBuilder();
                        builder.append(s1);
                        builder.append(s2);
                        builder.append(s3);
                        builder.append(s4);

                        list.add(builder);


                    }

                }};}
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

    public void redirect(Activity activity, Class classs) {
        Intent intent=new Intent(activity,classs);
        startActivity(intent);


    }



    //Affiche les infos de l'utilisateur dans le menu
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