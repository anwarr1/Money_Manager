package com.example.pfe;

public class Depense {

    private String categorie;
    private String Date;
    private Double valeur;
    private String description;
    public Depense(String categorie,Double valeur,String description) {
        this.categorie = categorie;
        this.description=description;
        this.valeur = valeur;
    }
    public Depense(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
