


package com.example.pfe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfe.databinding.ActivityMainBinding;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.ImageRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;



public class Log extends AppCompatActivity {
   TextView info,logout;
   ImageView prfl;
   LoginButton login;
   LoginButton google;
    CallbackManager callbackManager;
    User user;
    db2 db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//                        Masquer la barre li lfoug
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_log);
        info=findViewById(R.id.info);
        login=findViewById(R.id.login);
        logout=findViewById(R.id.logout);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
if(!isLoggedIn){  login();
}
else {
// ******************************************************* RUN JUSTE F LE CAS DYAL DYAL LOGOUT***********************************************************************************************************


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
}


    }

    public void daba
            (View v){ Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);}
    public  void login(){
        callbackManager =CallbackManager.Factory.create();

        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {
                                db2 db=new db2(getApplicationContext());

                                try { String id=me.getString("id");
                                    String noun=response.getJSONObject().getString("name");

//                                    String profileImageUrl = "https://graph.facebook.com/"+id+"/picture?type=large";

                                                                        String profileImageUrl = "https://graph.facebook.com/1675860826080829/picture?type=large";

                                    String idd=me.optString("id");
                                    String nom=me.optString("noun");

                                    user=new User(noun,idd);
                                    db.insertuser(user);

                                    SharedPreferences sharedPreferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("id", idd);
                                    editor.putString("name", nom);
                                    Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                                    intent.putExtra("imagePath", profileImageUrl);
                                    startActivity(intent);

                                    editor.commit();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                GraphRequest.executeBatchAsync(request);


                Intent intent=new Intent(Log.this,MainActivity.class);

                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });}


        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);


    }

    public  void google(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }




}