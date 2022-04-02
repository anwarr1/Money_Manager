package com.example.pfe;

import androidx.annotation.Nullable;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Calendar.DATE;


public class db2 extends SQLiteOpenHelper {

    private static final String table= "create table Revenu(id integer PRIMARY KEY ,categorie TEXT,id_user TEXT, valeur FLOAT,descr TEXT,date DATE,time TEXT,FOREIGN KEY(id_user) REFERENCES user(id))";
    private static final String user= "create table user(id TEXT PRIMARY KEY ,nom TEXT, mail TEXT,firstname TEXT)";
    private  static final String table11= "create table Depense(id integer PRIMARY KEY ,categorie TEXT,id_user TEXT, valeur FLOAT,descr TEXT,date DATE,time TEXT,FOREIGN KEY(id_user) REFERENCES user(id))";
    private static final String goal= "create table goal(nom TEXT PRIMARY KEY ,total FLOAT,AjouterMontant FLOAT,date DATE,description TEXT,categ TEXT,id_user TEXT,FOREIGN KEY(id_user) REFERENCES user(id))";
    private Context context;

        public  static  final String nameBD="jjaybii.db";

        public db2( @Nullable Context context) {
            super(context, nameBD, null,8);
            SQLiteDatabase db=this.getWritableDatabase();
this.context=context;

        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(user);
            db.execSQL(table11);
            db.execSQL(table);
            db.execSQL(goal);



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Depense");
            db.execSQL("DROP TABLE IF EXISTS Revenu");
            db.execSQL("DROP TABLE IF EXISTS user");
            db.execSQL("DROP TABLE IF EXISTS goal");

            onCreate(db);
        }


// **************************************************************************************** INSERTION DES DONNEES*******************************************************************************

    public void insertuser(User utilisateur){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues user=new ContentValues();
        user.put("nom",utilisateur.getNom());
        user.put("id",utilisateur.getId());

//        user.put("mail",utilisateur.getMail());
//        user.put("firstname",prenom);
db.insert("user",null,user);
        }

    public void insertgoal(Goal goal){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues Goal=new ContentValues();
        Goal.put("nom",goal.getNom());
        Goal.put("total",goal.getTotal());
        Goal.put("description",goal.getDescription());
        Goal.put("categ",goal.getCateg());
        Goal.put("AjouterMontant",0);
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Goal.put("id_user",idd);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
Goal.put("date",simpleDateFormat.format(new Date()));
        db.insert("goal",null,Goal);
    }

    public void UpdateMontantAjout(Double montant,String nom){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor MontantAncien=db.rawQuery ("Select AjouterMontant From goal WHERE nom=?",new String[]{nom});
        Double lastMontant=0.0;
        if  (MontantAncien.moveToNext())
      lastMontant =Double.parseDouble(MontantAncien.getString(MontantAncien.getColumnIndex("AjouterMontant")));
Double x=lastMontant+montant;

        db.execSQL("UPDATE goal SET AjouterMontant= '"+x+"'  WHERE nom=?",new String[]{nom});
    }


    public void insertRevenu(Revenu revenu){
        SQLiteDatabase db=this.getWritableDatabase();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        ContentValues Revenu=new ContentValues();
            Revenu.put("categorie",revenu.getCategorie());
            Revenu.put("valeur",revenu.getValeur());
            Revenu.put("categorie",revenu.getCategorie());
            Revenu.put("id_user",idd);

             Revenu.put("date", dateFormat.format(date));
       Revenu.put("time", timeFormat.format(date));
        if(revenu.getDescription()!=" " && revenu.getDescription()!=null){ Revenu.put("descr",revenu.getDescription());}
            db.insert("Revenu",null,Revenu);

    }
    public void insertDepense(Depense depense){
        SQLiteDatabase db=this.getWritableDatabase();
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        ContentValues Depense=new ContentValues();
        Depense.put("categorie",depense.getCategorie());
        Depense.put("valeur",depense.getValeur());
        Depense.put("id_user", idd);
        Depense.put("date", dateFormat.format(date));
        Depense.put("time", timeFormat.format(date));
        if(depense.getDescription()!=" " && depense.getDescription()!=null){ Depense.put("descr",depense.getDescription());}
        db.insert("Depense",null,Depense);


    }






// **************************************************************************************** RECUPERER lES DONNEES*******************************************************************************

    public Double TotalDepenses(){
        SQLiteDatabase db=this.getWritableDatabase();
        Double sommeDep=0.0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cur =db.rawQuery("select SUM(valeur) as valeur from Depense where id_user=?",new String[]{idd});
        if(cur.moveToNext()){
            sommeDep=cur.getDouble(cur.getColumnIndex("valeur"))+sommeDep;
            return sommeDep;
        }
        return sommeDep;


    }

    public Double TotalRevenus(){
        SQLiteDatabase db=this.getWritableDatabase();
        Double sommeRev=0.0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cur =db.rawQuery("select SUM(valeur) as valeur from Revenu where id_user=?",new String[]{idd});
        if(cur.moveToFirst()){
            sommeRev=cur.getDouble(cur.getColumnIndex("valeur"))+sommeRev;
            return sommeRev;
        }
        return sommeRev;

    }

    public double PourcentageCategorieDepense(String categ){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cur =db.rawQuery("select SUM(valeur) as valeur from Depense where categorie=? and id_user=?",new String[]{categ,idd});

        if (cur.moveToFirst()){ Double total = cur.getDouble(cur.getColumnIndex("valeur")); return total*100/TotalDepenses();}
        return 0;
    }
    public double PourcentageCategorieRevenu(String categ){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cur =db.rawQuery("select SUM(valeur) as valeur from Revenu where categorie=? and id_user=?",new String[]{categ,idd});


        if (cur.moveToFirst()){ Double total = cur.getDouble(cur.getColumnIndex("valeur")); return total*100/TotalRevenus();}


        return 0;
    }


    public Integer MontantTotalObjetif(String nom){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cur =db.rawQuery("select total from goal where nom=? and id_user=?",new String[]{nom,idd});


        if (cur.moveToFirst()){  return cur.getInt(cur.getColumnIndex("total")); }

          return 0;
    }
    public Integer MontantObjetifAjoute(String nom){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cur =db.rawQuery("select  AjouterMontant from goal where nom=? and id_user=?",new String[]{nom,idd});


        if (cur.moveToFirst()){ return cur.getInt(cur.getColumnIndex("AjouterMontant")); }

        return 0;
    }


    public Double DepenseMax(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cur =db.rawQuery("select  MAX(valeur) as valeur from Depense where id_user=?",new String[]{idd});


        if (cur.moveToFirst()){ Double total = cur.getDouble(cur.getColumnIndex("valeur")); return total;}


        return 0.0;


    }
    // **************************************************************************************** AFFICHER HISTORIQUE DES DONNEES*******************************************************************************



    public Cursor AfficherHistoDep(String date){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery("select * from Depense where date=? and id_user=?",new String[]{date,idd});


            return cursor;

    }

    public Cursor AfficherHistoRev(String date){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery("select * from Revenu where date=? and id_user=?",new String[]{date,idd});


        return cursor;

    }


    public Cursor AfficherHistoRevWeek(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery( "SELECT * FROM Revenu WHERE date >= datetime('now', '-6 days') and id_user=?",new String[]{idd});



        return cursor;



    }
    public Cursor AfficherHistoDepWeek(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery( "SELECT * FROM Depense WHERE date >= datetime('now', '-6 days')and id_user=?",new String[]{idd});



        return cursor;



    }




    public Cursor AfficherHistoRevMonth(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");

        Cursor cursor=db.rawQuery( "SELECT * FROM Revenu WHERE date >= datetime('now', 'start of month', '-1 month') and id_user=?",new String[]{idd});



        return cursor;



    }
    public Cursor AfficherHistoDepMonth(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery( "SELECT * FROM Depense WHERE date >= datetime('now', 'start of month', '-1 month') and id_user=?",new String[]{idd});



        return cursor;



    }





    public Cursor AfficherHistoRevToday(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery( "SELECT * FROM Revenu WHERE date >= datetime('now', '-1 day') and id_user=?",new String[]{idd});



        return cursor;



    }
    public Cursor AfficherHistoDepToday(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery( "SELECT * FROM Depense WHERE date >= datetime('now', '-1 day') and id_user=?",new String[]{idd});



        return cursor;

        }


    public Cursor AfficherGoal(){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        Cursor cursor=db.rawQuery( "SELECT * FROM goal where id_user=?",new String[]{idd});



        return cursor;

    }




//   ************************************************************************Supprimer Objectif*************************************************************************************************

    public void SupprimerGoal(String nom){
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        db.delete("goal","nom=? and id_user=? ",new String[]{nom,idd});

    }


    public void SupprimerHistoRev(String toString) {

        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        db.delete("Revenu","descr=? and id_user=?",new String[]{toString,idd});


    }
    public void SupprimerHistoDep(String toString) {
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        db.delete("Depense","descr=? and id_user=?",new  String[]{toString,idd});


    }



    public void SupprimerAll() {
        SQLiteDatabase db=this.getWritableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String idd=sharedPreferences.getString("id", "abc");
        db.delete("Depense"," id_user=?",new  String[]{idd});
        db.delete("Revenu"," id_user=?",new  String[]{idd});
        db.delete("goal"," id_user=?",new  String[]{idd});
    }
}








