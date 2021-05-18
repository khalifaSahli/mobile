/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author khlifa
 */
public class Suggestion {
 
    private int id;
    private String description ;
    private String type,nomimg,dateS ;
    private String date ;
    private int idp;

    public Suggestion(int id, String description, String type, String nomimg, String dateS, String date, int idp) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.nomimg = nomimg;
        this.dateS = dateS;
        this.date = date;
        this.idp = idp;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public String getNomimg() {
        return nomimg;
    }

    public void setNomimg(String nomimg) {
        this.nomimg = nomimg;
    }

    public Suggestion(int id, String description, String type, String nomimg, String date) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.nomimg = nomimg;
        this.date = date;
       
    }

    public Suggestion(int id, String description, String type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }
  

   

    public Suggestion(int id, String description, String type, String date, int idpl) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.date = date;
        this.idp = idp;
       
    }
    

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

   
     public Suggestion( String description, String type, String date, int idp) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.date = date;
        this.idp = idp;
    }

   

     public Suggestion(int id) {
        this.id = id;
        
    }
   

    public Suggestion() {
    }


  

    public Suggestion(String description, String type, String date) {
        this.description = description;
        this.type = type;
        this.date = date;
        
       
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Suggestion{" + "id=" + id + ", description=" + description + ", type=" + type + ", nomimg=" + nomimg + ", dateS=" + dateS + ", date=" + date + ", idp=" + idp + '}';
    }

  
    

 

  
    
}
