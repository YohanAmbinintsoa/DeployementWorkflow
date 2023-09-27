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
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class Prestation {
    String idPrestEsc;
    String idPrestation;
    String idEscale;
    String idBateau;
    Timestamp dateDebut;
    Timestamp dateFin;
    String idQuai;
    String nom;
    int tranche;
    int etat;
    Vector<Tarif> allTarif=new Vector<>();
    
    public static Vector<Prestation> getAllPrestation() throws Exception{
        Vector<Prestation> allPrestation = new Vector<>();
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from prestation where idprestation!=' PRESTATION3' or idprestation!='PRESTATION1'");
        while (res.next()) {            
            Prestation prest=new Prestation();
            prest.idPrestation=res.getString("idprestation");
            prest.nom=res.getString("nom");
            prest.tranche=res.getInt("tranche");
            allPrestation.add(prest);
        }
        return allPrestation;
    }
    
    public Prestation(){
        
    }
    
    public Prestation(String idEscale,String dateDebut,String dateFin,String idPrestation,String idQuai,String idBateau) throws Exception{
        this.insertIdescale(idEscale);
        this.insertDebut(dateDebut);
        this.insertFin(dateFin);
        this.insertPrestation(idPrestation);
        this.setEtat(1);
        this.insertBateau(idBateau);
        this.insertQuai(idQuai);
    }
    
    public void setTarif(Connection con) throws Exception{
        boolean nisokatra=false;
        if (con==null) {
            con=Connect.ConnectToPostgres("postgres", "root");
            nisokatra=true;
        }
        Statement state=con.createStatement();
        ResultSet res =state.executeQuery("select * from tarif where idprestation='"+this.idPrestation+"' and idquai='"+this.idQuai+"' ");
        if (nisokatra==true) {
            con.close();
        }
    }
    
    public void inserer(Connection con) throws Exception{
        boolean nisokatra=false;
        if (con==null) {
            con=Connect.ConnectToPostgres("postgres", "root");
            nisokatra=true;
        }
        Statement state=con.createStatement();
        System.out.println("insert into prestationescale values(default,'"+this.idPrestation+"','"+this.idEscale+"',"+this.etat+",'"+this.idQuai+"','"+this.dateDebut+"','"+this.dateFin+"')");
        state.execute("insert into prestationescale values(default,'"+this.idPrestation+"','"+this.idEscale+"',"+this.etat+",'"+this.idQuai+"','"+this.dateDebut+"','"+this.dateFin+"')");
        if (nisokatra==true) {
            con.commit();
            con.close();
        }
    }
    
    public void insertQuai(String quai) throws Exception{
        if (quai==null||quai.compareToIgnoreCase("")==0) {
            throw new Exception("Id Quai requis!");
        }
        this.idQuai=quai;
    }
    
    public void insertBateau(String bateau) throws Exception{
        if (bateau==null||bateau.compareToIgnoreCase("")==0) {
            throw new Exception("id Bateau requis!");
        }
        this.idBateau=bateau;
    }
    
    public void insertIdescale(String idescale)throws Exception{
        if (idescale==null||idescale.compareToIgnoreCase("")==0) {
            throw new Exception("Id Escale requis!");
        }
        this.idEscale=idescale;
    }
    
     public void insertDebut(String debut)throws Exception{
         if (debut==null||debut.compareToIgnoreCase("")==0) {
             this.dateDebut=new Timestamp(System.currentTimeMillis());
         }else {
             debut=debut.replace('T', ' ');
            SimpleDateFormat htmlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            this.dateDebut=new Timestamp(htmlDateFormat.parse(debut).getTime());
         }
    }
     
     public void insertFin(String fin)throws Exception{
        if (fin==null || fin.compareToIgnoreCase("")==0) {
            
        } else {
                fin=fin.replace('T', ' ');
         SimpleDateFormat htmlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date=htmlDateFormat.parse(fin);
        if (date.compareTo(this.dateDebut)<0) {
            throw new Exception("La date arrivee doit etre plus recente que la date d'arrivee!");
        }
        this.dateFin = new Timestamp(date.getTime());
        }
    }
     
     public void insertPrestation(String idprestation)throws Exception{
         if (idprestation==null||idprestation.compareToIgnoreCase("")==0) {
             throw new Exception("id Prestation requis!");
         }
         this.idPrestation=idprestation;
     }

    public String getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(String idPrestation) {
        this.idPrestation = idPrestation;
    }

    public String getIdEscale() {
        return idEscale;
    }

    public void setIdEscale(String idEscale) {
        this.idEscale = idEscale;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTranche() {
        return tranche;
    }

    public void setTranche(int tranche) {
        this.tranche = tranche;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIdPrestEsc() {
        return idPrestEsc;
    }

    public void setIdPrestEsc(String idPrestEsc) {
        this.idPrestEsc = idPrestEsc;
    }

    public String getIdBateau() {
        return idBateau;
    }

    public void setIdBateau(String idBateau) {
        this.idBateau = idBateau;
    }

    public String getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(String idQuai) {
        this.idQuai = idQuai;
    }
    
}
