package com.example.pfe;

public class User {

    private  String nom,prenom,mail;
    private String id;


    public User(String nom,String id) {
        this.nom = nom;
        this.id = id;

    }

    public User(){}

    public  String getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
