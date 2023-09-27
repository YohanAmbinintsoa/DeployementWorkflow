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
import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class Parametre {
        String idParam;
        int seuil;
        int tranche;
        float prixSeuil;
        Timestamp dateMaj;
        
        public static Parametre getParametre() throws Exception{
            Parametre parametre=new Parametre();
            Connection con =Connect.ConnectToPostgres("postgres", "root");
            Statement state=con.createStatement();
            ResultSet res=state.executeQuery("select * from paramStation order by datemaj");
            if (res.next()) {
                parametre.idParam=res.getString("idparam");
                parametre.prixSeuil=res.getFloat("prixseuil");
                parametre.tranche=res.getInt("tranche");
                parametre.seuil=res.getInt("seuil");
                parametre.dateMaj=res.getTimestamp("datemaj");
            }
            con.close();
            return parametre;
        }
        
        public Vector<float[]> createIntervalle(){
            Vector<float[]> vect=new Vector<>();
            int heure=this.seuil*60;
            int pas=0;
            for (int i = 0; i < heure/this.tranche; i++) {
                float[] f=new float[2];
                f[0]=pas;
                pas=pas+this.tranche;
                f[1]=pas;
                vect.add(f);
            }
            return vect;
        }
        
        public static void insertIntervalle(String[] first,String[] second,String[] prix) throws Exception{
            Connection con=Connect.ConnectToPostgres("postgres", "root");
            Statement state=con.createStatement();
            state.executeUpdate("delete from intervalle");
            state.close();
            for (int i = 0; i < prix.length; i++) {
                Statement queries=con.createStatement();
                queries.executeUpdate("insert into intervalle values(default, "+first[i]+","+second[i]+","+prix[i]+")");
                queries.close();
            }
        }

    public String getIdParam() {
        return idParam;
    }

    public void setIdParam(String idParam) {
        this.idParam = idParam;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }

    public float getPrixSeuil() {
        return prixSeuil;
    }

    public void setPrixSeuil(float prixSeuil) {
        this.prixSeuil = prixSeuil;
    }

    public Timestamp getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Timestamp dateMaj) {
        this.dateMaj = dateMaj;
    }
        
        
}
