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
public class TypeBateau {
        String idType;
        String nom;
        float prix;
        
     public static Vector<TypeBateau> getAllTypeBateau() throws Exception{
         Vector<TypeBateau> allType=new Vector<>();
         Connection con=Connect.ConnectToPostgres("postgres","root");
         Statement state=con.createStatement();
         ResultSet res=state.executeQuery("select * from typebateau");
         while (res.next()) {
             TypeBateau type=new TypeBateau();
             type.setIdType(res.getString("idType"));
             type.setNom(res.getString("nom"));
             type.setPrix(res.getFloat("prix"));
             allType.add(type);
         }
         con.close();
         return allType;
     }
     
     public static TypeBateau getTypeBateauById(String id)throws Exception{
         TypeBateau type=new TypeBateau();
         Connection con=Connect.ConnectToPostgres("postgres","root");
         Statement state=con.createStatement();
         ResultSet res=state.executeQuery("select * from typebateau where idtype="+id);
         if(res.next()){
             type.setIdType(res.getString("idType"));
             type.setNom(res.getString("nom"));
             type.setPrix(res.getFloat("prix"));
         }
         con.close();
         return type;
     }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
        
        
}
