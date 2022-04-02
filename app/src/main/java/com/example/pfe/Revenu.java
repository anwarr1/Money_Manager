package com.example.pfe;

public class Revenu {

private String categorie;
private Double valeur;
private String description;
    public Revenu(String categorie,Double valeur,String description) {
        this.categorie = categorie;
this.description=description;
        this.valeur = valeur;
    }
    public Revenu(){}

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


    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
