/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Proposition {
    String idProposition;
    String idQuai;
    Date dateArrivee;
    Date dateDepart;
    float montant;

    public String getIdProposition() {
        return idProposition;
    }

    public void setIdProposition(String idProposition) {
        this.idProposition = idProposition;
    }

    public String getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(String idQuai) {
        this.idQuai = idQuai;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
    
}
