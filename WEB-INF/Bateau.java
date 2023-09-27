/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class Bateau {
        String idBateau;
        String nom;
        String typeBateau;
        String pavillon;
        float profondeur;
        float dureeRemorquage;

    public Bateau(String nom,String typeBateau, String pavillon, String profondeur, String dureeRemorquage) throws Exception{
       this.setNom(nom);
       this.setTypeBateau(typeBateau);
       this.setPavillon(pavillon);
       this.setProfondeur(profondeur);
       this.setDureeRemorquage(dureeRemorquage);
    }

    public Bateau(){
        
    }
    
    public static Vector<Bateau> getAllBateau() throws Exception{
        Vector<Bateau> allBateau=new Vector<>();
         Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from bateau");
        while (res.next()) {            
            Bateau bat=new Bateau();
            bat.idBateau=res.getString("idbateau");
            bat.nom=res.getString("nom");
            bat.typeBateau=res.getString("idtypebateau");
            bat.pavillon=res.getString("idpavillon");
            bat.profondeur=res.getFloat("profondeur");
            bat.dureeRemorquage=res.getFloat("dureeremorquage");
            allBateau.add(bat);
        }
        con.close();
        return allBateau;
    }
    
    public static Bateau getBateauById(String id) throws Exception{
        Bateau bat=new Bateau();
          Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from bateau where idBateau='"+id+"'");
        if (res.next()) {
            bat.idBateau=res.getString("idbateau");
            bat.nom=res.getString("nom");
            bat.typeBateau=res.getString("idtypebateau");
            bat.pavillon=res.getString("idpavillon");
            bat.profondeur=res.getFloat("profondeur");
            bat.dureeRemorquage=res.getFloat("dureeremorquage");
        }
        con.close();
        return bat;
    }
    
    public void insertBateau() throws Exception{ 
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        int isany=state.executeUpdate("insert into bateau values(default,'"+this.nom+"','"+this.typeBateau+"','"+this.pavillon+"',"+this.profondeur+","+this.dureeRemorquage+")"); 
        con.close();
    }

    public String getIdBateau() {
        return idBateau;
    }

    public void setIdBateau(String idBateau) {
        this.idBateau = idBateau;
    }
    
      public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception{
        if (nom.compareToIgnoreCase("")==0||nom==null) {
            throw new Exception("Nom de bateau requis!");
        }
        this.nom = nom;
    }
        

    public String getTypeBateau() {
        return typeBateau;
    }

    public void setTypeBateau(String typeBateau) throws Exception{
        if(typeBateau.compareToIgnoreCase("")==0||typeBateau==null){
                throw new Exception("Type Bateau requis!");
        }
        this.typeBateau = typeBateau;
    }

    public String getPavillon() {
        return pavillon;
    }

    public void setPavillon(String pavillon) throws Exception{
        if (pavillon.compareToIgnoreCase("")==0) {
            throw new Exception("Pavillon requis!");
        }
        this.pavillon = pavillon;
    }

    public float getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(String profondeur) throws Exception{
        if ( profondeur==null || profondeur.compareToIgnoreCase("")==0) {
                throw new Exception("Profondeur Invalide!");
        }
        float prof=Float.parseFloat(profondeur);
        if (prof<2) {
            throw new Exception("Profondeur Invalide!");
        }
        this.profondeur = prof;
    }

    public float getDureeRemorquage() {
        return dureeRemorquage;
    }

    public void setDureeRemorquage(String dureeRemorquage) throws Exception{
        if (dureeRemorquage==null || dureeRemorquage.compareToIgnoreCase("")==0) {
            
            throw new Exception("Duree remorquage invalide!");
        }
        float duree=Float.parseFloat(dureeRemorquage);
        if (duree<0) {
             throw new Exception("Duree remorquage invalide!");
        }
        this.dureeRemorquage = duree;
    }
}
