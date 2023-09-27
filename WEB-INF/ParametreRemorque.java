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

/**
 *
 * @author DELL
 */
public class ParametreRemorque {
    String idParamRem;
    int tranche;
    float prix;
    
    public static ParametreRemorque getRemorquage() throws Exception{
        ParametreRemorque rem=new ParametreRemorque();
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from paramRemorque");
        if (res.next()) {
            rem.idParamRem=res.getString("idParamRemorque");
            rem.tranche=res.getInt("tranche");
            rem.prix=res.getFloat("prix");
        }
        con.close();
        return rem;
    }
    
    public String getIdParamRem() {
        return idParamRem;
    }

    public void setIdParamRem(String idParamRem) {
        this.idParamRem = idParamRem;
    }

    public int getTranche() {
        return tranche;
    }

    public void setTranche(int tranche) {
        this.tranche = tranche;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    
}
