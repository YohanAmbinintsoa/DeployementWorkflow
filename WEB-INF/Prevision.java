/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Connect;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class Prevision {
    String idPrevision;
    String idBateau;
    Timestamp dateArrivee;
    Timestamp dateDepart;
    Quai quai;
    float montant;

    public Quai getQuai() {
        return quai;
    }

    public float getMontant() {
        return montant;
    }
    
    public Prevision(){
        
    }

    public Prevision(String idBateau, String dateArrivee, String dateDepart) throws Exception{
        this.setIdBateau(idBateau);
        this.setDateArrivee(dateArrivee);
        this.setDateDepart(dateDepart);
    }
    
    public String insertPrevision() throws Exception{
        Connection con =Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        System.out.println("insert into prevision values(default,'"+this.idBateau+"','"+this.dateArrivee+"','"+this.dateDepart+"' ) returning idPrevision");
        ResultSet res=state.executeQuery("insert into prevision values(default,'"+this.idBateau+"','"+this.dateArrivee+"','"+this.dateDepart+"' ) returning idPrevision");
        String valiny="";
        if (res.next()) {
            valiny=res.getString("idprevision");
        }
        con.close();
        return valiny;
    }
    
    public void getBestQuaiByDepth() throws Exception{
        Bateau bat=Bateau.getBateauById(this.idBateau);
        Quai q=new Quai();
        Connection con=Connect.ConnectToPostgres("postgres","root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from quai where profondeur>="+bat.profondeur+" order by profondeur ASC");
        if (res.next()) {
            q.idQuai=res.getString("idquai");
            q.nom=res.getString("nom");
            q.profondeur=res.getFloat("profondeur");
        }
        this.quai=q;
        con.close();
    }
    
    public static Vector<Prevision> getAllCalculatedPrevision(String idQuai) throws Exception{
        Vector<Prevision> allPrevision=Prevision.getAllPrevision();
        Vector<Prevision> surQuai=new Vector<>();
        for (int i = 0; i < allPrevision.size(); i++) {
            allPrevision.get(i).getBestQuaiByDepth();
            if (allPrevision.get(i).getQuai().getIdQuai().compareTo(idQuai)==0) {
                surQuai.add(allPrevision.get(i));
            }
        }
       Timestamp dateFin=null;
        for (int i = 0; i < surQuai.size(); i++) {
            if (dateFin==null) {
                dateFin=surQuai.get(i).getDateDepart();
            } else if (dateFin!=null) {
                if (dateFin.compareTo(surQuai.get(i).dateArrivee)<0) {
                         dateFin=surQuai.get(i).getDateDepart();
                } else {
                    long duree=surQuai.get(i).getMinutesDifference();
                    surQuai.get(i).dateArrivee=dateFin;
                    long dureeFin=dateFin.getTime();
                     surQuai.get(i).dateDepart=new Timestamp(duree+dureeFin);
                    dateFin=surQuai.get(i).getDateDepart();
                }
            }
        }
        return surQuai;
    }
    
    public Prevision  EquilibratePrevision() throws Exception{
        Prevision mifanaraka=new Prevision();
        mifanaraka.setIdPrevision(this.getIdPrevision());
        mifanaraka.setIdBateau(this.getIdBateau());
        this.getBestQuaiByDepth();
        mifanaraka.quai=this.quai;
        Vector<Prevision> allPrev=Prevision.getAllEarlierPrevision(this);
        Vector<Prevision> allPrevMatch=new Vector<>();
//        Vector<Prevision> allLater=Prevision.getAllLaterPrevision(this);
//        Vector<Prevision> allLaterPrev=new Vector<>();
        for (int i = 0; i < allPrev.size(); i++) {
            allPrev.get(i).getBestQuaiByDepth();
            if (this.quai.getIdQuai().compareTo(allPrev.get(i).quai.getIdQuai())==0) {
                 allPrevMatch.add(allPrev.get(i));
            }
        }
        
        int size=allPrevMatch.size();
        if (size==0) {
            System.out.println("SIZE 0");
            mifanaraka.dateArrivee=this.dateArrivee;
            mifanaraka.dateDepart=this.dateDepart;
            mifanaraka.setMontant();
            return mifanaraka;
        }
        if (allPrevMatch.get(size-1).idPrevision.compareTo(this.idPrevision)!=0) {
            System.out.println("MISY");
            if (allPrevMatch.get(size-1).getDateDepart().compareTo(this.dateArrivee)<0) {
                mifanaraka.dateArrivee=this.dateArrivee;
                mifanaraka.dateDepart=this.dateDepart;
                mifanaraka.setMontant();
                return mifanaraka;
            }
            mifanaraka.dateArrivee=allPrevMatch.get(size-1).getDateDepart();
            long difference=this.getDateDepart().getTime()-this.getDateArrivee().getTime();
            mifanaraka.dateDepart=new Timestamp(mifanaraka.getDateArrivee().getTime()+difference);
            mifanaraka.setMontant();
        } else {
             System.out.println("MISY KOA");
            mifanaraka.dateArrivee=this.dateArrivee;
            mifanaraka.dateDepart=this.dateDepart;
            mifanaraka.setMontant();
            return mifanaraka;
        }
        return mifanaraka;
    }
    
    
    public static Prevision getPrevisionById(String id) throws Exception{
        Prevision prev=new Prevision();
        Connection con =Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from prevision where idprevision='"+id+"'");
        if (res.next()) {
            prev.idPrevision=res.getString("idprevision");
            prev.idBateau=res.getString("idbateau");
            prev.dateArrivee=res.getTimestamp("datearrivee");
            prev.dateDepart=res.getTimestamp("datedepart");
        }
        con.close();
        return prev;
    }
     
    
    public String getIdPrevision() {
        return idPrevision;
    }
    
    public static Vector<Prevision> getAllPrevision() throws Exception{
          Vector<Prevision> allPrev=new Vector<>();
          Connection con =Connect.ConnectToPostgres("postgres", "root");
         Statement state=con.createStatement();
         ResultSet res=state.executeQuery("select * from prevision order by datearrivee");
        while (res.next()) {
            Prevision prev=new Prevision();
            prev.idPrevision=res.getString("idprevision");
            prev.idBateau=res.getString("idbateau");
            prev.dateArrivee=res.getTimestamp("datearrivee");
            prev.dateDepart=res.getTimestamp("datedepart");
            allPrev.add(prev);
        }
        con.close();
          return allPrev;
    }
    
    public static Vector<Prevision> getAllEarlierPrevision(Prevision prevision) throws Exception{
         Vector<Prevision> allPrev=new Vector<>();
          Connection con =Connect.ConnectToPostgres("postgres", "root");
         Statement state=con.createStatement();
         ResultSet res=state.executeQuery("select * from prevision where datearrivee<'"+prevision.getDateArrivee()+"'  order by datearrivee");
          while (res.next()) {
                Prevision prev=new Prevision();
                prev.idPrevision=res.getString("idprevision");
                prev.idBateau=res.getString("idbateau");
                prev.dateArrivee=res.getTimestamp("datearrivee");
                prev.dateDepart=res.getTimestamp("datedepart");
                allPrev.add(prev);
            }
          con.close();
          return allPrev;
    }
    public static Vector<Prevision> getAllLaterPrevision(Prevision prevision) throws Exception{
        Vector<Prevision> allPrev=new Vector<>();
          Connection con =Connect.ConnectToPostgres("postgres", "root");
         Statement state=con.createStatement();
         ResultSet res=state.executeQuery("select * from prevision where datearrivee>'"+prevision.getDateArrivee()+"'  order by datearrivee");
          while (res.next()) {
                Prevision prev=new Prevision();
                prev.idPrevision=res.getString("idprevision");
                prev.idBateau=res.getString("idbateau");
                prev.dateArrivee=res.getTimestamp("datearrivee");
                prev.dateDepart=res.getTimestamp("datedepart");
                allPrev.add(prev);
            }
          con.close();
          return allPrev;
    }
    
    public void setMontant() throws Exception{
        Parametre param=Parametre.getParametre();
        ParametreRemorque pam=ParametreRemorque.getRemorquage();
        Bateau bat=Bateau.getBateauById(this.getIdBateau());
        long minute=this.getMinutesDifference();
        int heure=param.seuil*60;
        float price=0;
        float minuteRemorquage=((float)bat.dureeRemorquage)/pam.tranche;
        float reste=((float)bat.dureeRemorquage)%pam.tranche;
        float nombre=(float)Math.ceil((double)minuteRemorquage);
        System.out.println("Minute Remorque="+minuteRemorquage);
//        if(reste==0.){
//            System.out.println("Minute Remorque="+minuteRemorquage);
//            nombre+=1;
//        }
        Connection con=Connect.ConnectToPostgres("postgres", "root");
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select pavillon.prix as prixPavillon,typeBateau.prix as prixType from bateau join pavillon on bateau.idPavillon=pavillon.idPavillon join typeBateau on bateau.idtypebateau=typeBateau.idType where idbateau='"+this.idBateau+"'");
        if (res.next()) {
            price=res.getFloat("prixpavillon")+res.getFloat("prixtype");
        }
        price+=(nombre*pam.getPrix());
        System.out.println("PRIX APRES REMORQUE="+price);
        state.close();
        Statement intervalle=con.createStatement();
        ResultSet inter;
        if (minute<=heure) {
             inter=intervalle.executeQuery("select * from intervalle where debutintervalle<="+minute+" and finintervalle>"+minute);
            System.out.println("select * from intervalle where debutintervalle<="+minute+" and finintervalle>"+minute);
            if (inter.next()) {
                price=price+inter.getFloat("prix");
                this.montant=price;
            }
        } else {
             inter=intervalle.executeQuery("select * from intervalle order by finintervalle desc");
             if (inter.next()) {
                 System.out.println("MINUTE="+minute);
                 System.out.println("DIFFERENCE="+((((int)minute)-heure)));
                price=price+inter.getFloat("prix");
                this.montant=price+((((int)minute)-heure)*param.prixSeuil);
            }
           
        }
        con.close();
    }
    
    public static LocalDateTime timestampToLocalDate(Timestamp time) throws Exception{
        long timestamp = time.getTime(); // Replace with your timestamp

        // Convert timestamp to Instant
        Instant instant = Instant.ofEpochSecond(timestamp);

        // Convert Instant to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }
    
    public long getMinutesDifference() {
        LocalDateTime dateTime1 = this.getDateArrivee().toLocalDateTime();
        LocalDateTime dateTime2 = this.getDateDepart().toLocalDateTime();

        Duration duration = Duration.between(dateTime1, dateTime2);
        return duration.toMinutes();
    }

    public void setIdPrevision(String idPrevision) {
        this.idPrevision = idPrevision;
    }

    public String getIdBateau() {
        return idBateau;
    }

    public void setIdBateau(String idBateau) throws Exception{
        if (idBateau==null || idBateau.compareToIgnoreCase("")==0) {
            throw new Exception("idBateau requis!");
        }
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
                 System.out.println("MANAO DATE ");
                 throw new Exception("La date arrivee doit etre plus recente que la date d'aujourd'hui!");
            }
            this.dateArrivee = new Timestamp(date.getTime());
        }
    }

    public Timestamp getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) throws Exception{
          java.util.Date currentDate=new java.util.Date();
        if (dateDepart==null || dateDepart.compareToIgnoreCase("")==0) {
//            this.dateDepart=new Timestamp(System.currentTimeMillis());
                throw  new Exception("Date depart requis!");
        } else {
                dateDepart=dateDepart.replace('T', ' ');
         SimpleDateFormat htmlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date=htmlDateFormat.parse(dateDepart);
        if (date.compareTo(this.dateArrivee)<0) {
            throw new Exception("La date arrivee doit etre plus recente que la date d'arrivee!");
        }
        this.dateDepart = new Timestamp(date.getTime());
        }
    }
    
    
}
