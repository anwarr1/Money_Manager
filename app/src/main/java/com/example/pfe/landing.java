package com.example.pfe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);


//**********************************************************************************RUN MERRA WEHDA*************************************************************************************************
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, Log.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }

    }




//    <?xml version="1.0" encoding="utf-8"?>
//<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:background="#E6EAE4"
//    tools:context=".MainActivity"
//    android:id="@+id/drawer_layout"
//
//    android:orientation="horizontal">
//
//
//    <LinearLayout
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:orientation="vertical">
//
//
//        <include
//    layout="@layout/main_toolbar"
//    android:background="#FFFFFC"></include>
//
//
//        <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:background="#FFFCFC"
//    android:orientation="vertical">
//
//            <Button
//    android:id="@+id/Rdep"
//    android:layout_width="136dp"
//    android:layout_height="61dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="72dp"
//    android:layout_marginEnd="52dp"
//    android:layout_marginRight="52dp"
//    android:onClick="AddDep"
//    android:text="+ Depense"
//    app:backgroundTint="#CC1414"
//    app:layout_constraintBottom_toBottomOf="parent"
//    app:layout_constraintEnd_toEndOf="parent" />
//
//            <Button
//    android:id="@+id/Rrev"
//    android:layout_width="136dp"
//    android:layout_height="61dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="72dp"
//    android:layout_marginEnd="235dp"
//    android:layout_marginRight="235dp"
//    android:onClick="AddRev"
//    android:text="+ Revenu "
//    app:backgroundTint="#8BC34A"
//    app:layout_constraintBottom_toBottomOf="parent"
//    app:layout_constraintEnd_toEndOf="parent" />
//
//            <ImageView
//    android:layout_width="116dp"
//    android:layout_height="113dp"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_alignParentTop="true"
//    android:layout_marginEnd="146dp"
//    android:layout_marginRight="146dp"
//    android:layout_marginTop="13dp"
//    android:background="@drawable/money"></ImageView>
//
//            <RelativeLayout
//    android:layout_width="111dp"
//    android:layout_height="162dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="347dp"
//    android:layout_marginEnd="274dp"
//    android:layout_marginRight="274dp"
//    android:background="@drawable/revenu_back"
//    android:elevation="10dp">
//
//                <TextView
//    android:id="@+id/textVie12"
//    android:layout_width="65dp"
//    android:layout_height="69dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="78dp"
//    android:layout_marginEnd="18dp"
//    android:layout_marginRight="18dp"
//    android:fontFamily="@font/allerta"
//    android:text="Total Revenu"
//    android:textColor="#FFFFFF"
//    android:textSize="14sp" />
//
//
//                <TextView
//    android:id="@+id/totalrev"
//    android:layout_width="68dp"
//    android:layout_height="wrap_content"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="26dp"
//    android:layout_marginEnd="29dp"
//    android:layout_marginRight="34dp"
//    android:fontFamily="@font/anton"
//    android:text="0.00"
//    android:textColor="#FFFFFF"
//    android:textSize="18sp" />
//
//                <TextView
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="28dp"
//    android:layout_marginEnd="4dp"
//    android:layout_marginRight="13dp"
//    android:fontFamily="sans-serif-black"
//    android:text="DH"
//    android:textColor="#FFFFFF"
//    android:textSize="16sp" />
//            </RelativeLayout>
//
//            <RelativeLayout
//    android:layout_width="111dp"
//    android:layout_height="162dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="347dp"
//    android:layout_marginEnd="9dp"
//    android:layout_marginRight="9dp"
//    android:background="@drawable/save_back"
//    android:elevation="10dp">
//
//                <TextView
//    android:id="@+id/txtVie12"
//    android:layout_width="65dp"
//    android:layout_height="69dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="78dp"
//    android:layout_marginEnd="18dp"
//    android:layout_marginRight="18dp"
//    android:fontFamily="@font/allerta"
//    android:text="Total restant"
//    android:textColor="#FFFFFF"
//    android:textSize="14sp" />
//
//                <TextView
//    android:id="@+id/totalrest"
//    android:layout_width="68dp"
//    android:layout_height="wrap_content"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="26dp"
//    android:layout_marginEnd="29dp"
//    android:layout_marginRight="34dp"
//    android:fontFamily="@font/anton"
//    android:text="0.00"
//    android:textColor="#FFFFFF"
//    android:textSize="18sp" />
//
//                <TextView
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="28dp"
//    android:layout_marginEnd="13dp"
//    android:layout_marginRight="13dp"
//    android:fontFamily="sans-serif-black"
//    android:text="DH"
//    android:textColor="#FFFFFF"
//    android:textSize="18sp" />
//            </RelativeLayout>
//
//            <RelativeLayout
//    android:layout_width="111dp"
//    android:layout_height="162dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="348dp"
//    android:layout_marginEnd="140dp"
//    android:layout_marginRight="140dp"
//    android:background="@drawable/depense_back"
//    android:elevation="10dp">
//
//                <TextView
//    android:id="@+id/tetVie12"
//    android:layout_width="72dp"
//    android:layout_height="66dp"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="78dp"
//    android:layout_marginEnd="12dp"
//    android:layout_marginRight="12dp"
//    android:fontFamily="@font/allerta"
//    android:text="Total Depense"
//    android:textColor="#FFFFFF"
//    android:textSize="14sp" />
//
//                <TextView
//    android:id="@+id/totaldep"
//    android:layout_width="68dp"
//    android:layout_height="wrap_content"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="26dp"
//    android:layout_marginEnd="29dp"
//    android:layout_marginRight="34dp"
//    android:fontFamily="@font/anton"
//    android:text="0.00"
//    android:textColor="#FFFFFF"
//    android:textSize="18sp" />
//
//                <TextView
//    android:id="@+id/textView17"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_alignParentBottom="true"
//    android:layout_alignParentEnd="true"
//    android:layout_alignParentRight="true"
//    android:layout_marginBottom="28dp"
//    android:layout_marginEnd="13dp"
//    android:layout_marginRight="13dp"
//    android:fontFamily="sans-serif-black"
//    android:text="DH"
//    android:textColor="#FFFFFF"
//    android:textSize="18sp" />
//            </RelativeLayout>
//
//
//        </RelativeLayout>
//    </LinearLayout>
//
//
//    <RelativeLayout
//    android:layout_width="300dp"
//    android:layout_height="match_parent"
//    android:layout_gravity="start"
//    android:background="#EDE7E7">
//
//        <include layout="@layout/main_nav_drawer"></include>
//
//
//    </RelativeLayout>
//</androidx.drawerlayout.widget.DrawerLayout>





    public  void landing(View v){

        Intent intent= new Intent(this,Log.class);
        startActivity(intent);

    }

}

