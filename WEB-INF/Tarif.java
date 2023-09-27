/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author DELL
 */
public class Tarif {
    String idTarif;
    String idQuai;
    String idPavillon;
    float prix;
    String idPrestation;
    int heureDebut;
    int heureFin;
    
    public boolean isHere(LocalDateTime time){
        LocalTime debut=LocalTime.of(this.heureDebut, 0);
        LocalTime fin=LocalTime.of(this.heureFin, 0);
        
        LocalTime compared=time.toLocalTime();
        if (debut.isBefore(compared)&&fin.isAfter(compared)) {
            return true;
        }
        return false;
    }

    public String getIdTarif() {
        return idTarif;
    }

    public void setIdTarif(String idTarif) {
        this.idTarif = idTarif;
    }

    public String getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(String idQuai) {
        this.idQuai = idQuai;
    }

    public String getIdPavillon() {
        return idPavillon;
    }

    public void setIdPavillon(String idPavillon) {
        this.idPavillon = idPavillon;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(String idPrestation) {
        this.idPrestation = idPrestation;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }
    
}
