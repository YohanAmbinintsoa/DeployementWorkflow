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
public class Profil {
    String idProfil;
    String nom;
    int isAdmin;
    
    public static Vector<Profil> getAllProfil() throws Exception{
        Vector<Profil> allProfil=new Vector<>();
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from profil");
        while (res.next()) {            
            Profil prof=new Profil();
            prof.idProfil=res.getString("idprofil");
            prof.nom=res.getString("nom");
            prof.isAdmin=res.getInt("isadmin");
            allProfil.add(prof);
        }
        con.close();
        return allProfil; 
    }

    public String getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(String idProfil) {
        this.idProfil = idProfil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
