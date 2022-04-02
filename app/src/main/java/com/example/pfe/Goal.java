package com.example.pfe;

public class Goal {


    String nom;
   Double total,montant_ajoute;
    String description,categ;



    public Goal(String nom, Double total, String description, String categ) {
        this.nom = nom;
        this.total = total;
        this.description = description;
        this.categ=categ;
//        this.montant_ajoute = montant_ajoute;

    }

    public Double getMontant_ajoute() {
        return montant_ajoute;
    }

    public String getCateg() {
        return categ;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
