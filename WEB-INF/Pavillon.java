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
public class Pavillon {
        String idPavillon;
        String nom;
        float prix;
        
        public static Vector<Pavillon> getAllPavillon() throws Exception{
            Vector<Pavillon> pavillon=new Vector<>();
                Connection con=Connect.ConnectToPostgres("postgres","root");
                Statement state=con.createStatement();
                ResultSet res=state.executeQuery("select * from pavillon");
                while (res.next()) {
                        Pavillon pav=new Pavillon();
                        pav.setIdPavillon(res.getString("idPavillon"));
                        pav.setNom(res.getString("nom"));
                        pav.setPrix(res.getFloat("prix"));
                        pavillon.add(pav);
                }
                con.close();
                return pavillon;
        }
        
        public static Pavillon getPavillonById(String idPavillon) throws Exception{
            Pavillon pav=new Pavillon();
            Connection con=Connect.ConnectToPostgres("postgres","root");
            Statement state=con.createStatement();
            ResultSet res=state.executeQuery("select * from pavillon where idpavillon='"+idPavillon+"'");
            if (res.next()) {
                        pav.setIdPavillon(res.getString("idPavillon"));
                        pav.setNom(res.getString("nom"));
                        pav.setPrix(res.getFloat("prix"));
            }
            con.close();
            return pav;
        }

    public String getIdPavillon() {
        return idPavillon;
    }

    public void setIdPavillon(String idPavillon) {
        this.idPavillon = idPavillon;
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
