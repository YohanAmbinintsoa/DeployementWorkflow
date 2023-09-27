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
public class Quai {
    String idQuai;
    String nom;
    float profondeur;
    
    public static Vector<Quai> getAllQuai() throws Exception{
        Vector<Quai> quai=new Vector<>();
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from quai");
        while(res.next()){
            Quai q=new Quai();
            q.idQuai=res.getString("idquai");
            q.nom=res.getString("nom");
            q.profondeur=res.getFloat("profondeur");
            quai.add(q);
        }
        return quai;
    }
    
    public static Quai getQuaiById(String id) throws Exception{
        Quai q=new Quai();
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from quai where idquai="+id);
        while(res.next()){            
            q.idQuai=res.getString("idquai");
            q.nom=res.getString("nom");
            q.profondeur=res.getFloat("profondeur");
        }
        return q;
    }

    public String getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(String idQuai) {
        this.idQuai = idQuai;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(float profondeur) {
        this.profondeur = profondeur;
    }
    
    
}
