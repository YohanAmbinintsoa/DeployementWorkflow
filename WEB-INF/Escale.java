/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class Escale {
    String idEscale;
    String idBateau;
    Timestamp dateArrivee;
    Timestamp dateFin;
    Vector<Prestation> prestation= new Vector<>();
    String idQuai;
    
    public Vector<Prestation> getPrestation() {
        return prestation;
    }

    public Escale(){
      
    }
    
    public Escale(String idBateau,String dateArrivee) throws Exception{
        this.setIdBateau(idBateau);
        this.setDateArrivee(dateArrivee);
    }
    
    public void setLastQuai(Connection con) throws Exception{
        boolean nisokatra=false;
        if (con==null) {
            con=Connect.ConnectToPostgres("postgres", "root");
            nisokatra=true;
        }
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from escalequai where idescale='"+this.idEscale+"'");
        if (res.next()) {
            this.idQuai=res.getString("idquai");
        }
        if (nisokatra==true) {
            con.close();
        }
    }
    
    public void insertEscale(String idQuai) throws Exception{
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("insert into escale values(default,'"+this.idBateau+"','"+this.dateArrivee+"',NULL) returning idescale");
        String idEscale=null;
        if (res.next()) {
            idEscale=res.getString("idescale");
        }
        state.close();
        Statement idQuaiInsertion = con.createStatement();
        idQuaiInsertion.execute("insert into escalequai values(default,'"+idQuai+"','"+idEscale+"','"+this.dateArrivee+"',NULL)");
        con.commit();
        con.close();
    }
    
    public static Vector<Escale> getAllEscale() throws Exception{
        Vector<Escale> allEscale=new Vector<>();
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from escale");
        while (res.next()) {
            Escale esc=new Escale();
            esc.idEscale=res.getString("idescale");
            esc.idBateau=res.getString("idbateau");
            esc.dateArrivee=res.getTimestamp("datedebut");
            esc.dateFin=res.getTimestamp("datefin");
            allEscale.add(esc);
        }
        return allEscale;
    }
    
    public void setPrestation(Connection con) throws  Exception{
        boolean nisokatra=false;
        if (con==null) {
            con=Connect.ConnectToPostgres("postgres", "root");
            nisokatra=true;
        }
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select prestationescale.*,prestation.nom,prestation.tranche from prestationescale join prestation on prestation.idprestation=prestationescale.idprestation where idescale='"+this.idEscale+"'");
        while (res.next()) {
            Prestation prest=new Prestation();
            prest.setIdPrestEsc("prestesc");
            prest.setIdPrestation(res.getString("idprestation"));
            prest.setNom(res.getString("nom"));
            prest.setIdEscale(res.getString("idescale"));
            prest.setTranche(res.getInt("tranche"));
            prest.setIdQuai("idquai");
            prest.setEtat(res.getInt("etat"));
            prest.setDateDebut(res.getTimestamp("datedebut"));
            prest.setDateFin(res.getTimestamp("datefin"));
            this.prestation.add(prest);
        }
        if (nisokatra==true) {
            con.close();
        }
    }
    
    public static Escale getEscaleById(String idescale,Connection con) throws Exception{
        Escale esc=new Escale();
         boolean nisokatra=false;
        if (con==null) {
            con=Connect.ConnectToPostgres("postgres", "root");
            nisokatra=true;
        }
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from escale where idescale='"+idescale+"'");
        while (res.next()) {
           esc.setIdEscale(res.getString("idescale"));
           esc.setIdBateau(res.getString("idbateau"));
           esc.dateArrivee=res.getTimestamp("datedebut");
           esc.setDateFin(res.getTimestamp("datefin"));
        }
        esc.setPrestation(con);
        esc.setLastQuai(con);
        if (nisokatra==true) {
            con.close();
        }
        return esc;
    }

    public String getIdEscale() {
        return idEscale;
    }

    public void setIdEscale(String idEscale) {
        this.idEscale = idEscale;
    }

    public String getIdBateau() {
        return idBateau;
    }

    public void setIdBateau(String idBateau) {
        this.idBateau = idBateau;
    }

    public Timestamp getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) throws Exception{
        java.util.Date currentDate=new java.util.Date();
        if (dateArrivee==null || dateArrivee.compareToIgnoreCase("")==0) {
            this.dateArrivee=new Timestamp(System.currentTimeMillis());
        } else {
            dateArrivee=dateArrivee.replace('T', ' ');
         SimpleDateFormat htmlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date=htmlDateFormat.parse(dateArrivee);
        if (date.compareTo(currentDate)<0) {
            throw new Exception("La date arrivee doit etre plus recente que la date aujourd'hui!");
        }
        this.dateArrivee = new Timestamp(date.getTime());
        }
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public String getIdQuai() {
        return idQuai;
    }

    public void setIdQuai(String idQuai) {
        this.idQuai = idQuai;
    }
   
    
}
