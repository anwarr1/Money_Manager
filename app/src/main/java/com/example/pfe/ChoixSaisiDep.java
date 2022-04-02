package com.example.pfe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ChoixSaisiDep extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.choix_saisie_dep);


    }
    public  void  onclickk(View v){

        Intent intent=new Intent(this,AjoutDepense.class);
        startActivity(intent);


    }

    public void Onscan(View v){
        IntentIntegrator intentIntegrator=new IntentIntegrator(ChoixSaisiDep.this);

        intentIntegrator.setPrompt("flash");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(capture.class);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requC, int resultCode, @Nullable Intent data){
        super.onActivityResult(requC,resultCode,data);
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requC,resultCode,data);
if(intentResult.getContents()!=null){
    AlertDialog.Builder builder=new AlertDialog.Builder(ChoixSaisiDep.this);
builder.setTitle("RESULTAT");
builder.setMessage(intentResult.getContents());
builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
dialog.dismiss();
    }
});
builder.show();
}else{
    Toast.makeText(getApplicationContext(),"anwar",Toast.LENGTH_SHORT).show();
}
    }



}