/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Suggestion;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author khlifa
 */
public class ServiceSugggestion {
     public ArrayList<Suggestion> suggestion;
    
    public static ServiceSugggestion instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceSugggestion() {
         req = new ConnectionRequest();
    }

    public static ServiceSugggestion getInstance() {
        if (instance == null) {
            instance = new ServiceSugggestion();
        }
        return instance;
    }

    
    public boolean addsug(Suggestion s) {
        String url = Statics.BASE_URL + "/ADDJSON/new?Description=" + s.getDescription() + "&type=" + s.getType()+"&date="+s.getDate(); //création de l'URL
        req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){
       
            

            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode ()==200;
                req.removeResponseListener(this);
            }
    });
       NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean EditSug(Suggestion s) {
        String url = Statics.BASE_URL + "/UpdateSuggestionsJSON/"+s.getId()+"?Description=" + s.getDescription() + "&Type=" + s.getType()+"&Date="+s.getDate()+"&image="+s.getNomimg(); //création de l'URL
        req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){
       
            

            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode ()==200;
                req.removeResponseListener(this);
            }
    });
       NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Suggestion> parseSugs(String jsonText){
        try {
            suggestion=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> SugsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
          
            List<Map<String,Object>> list = (List<Map<String,Object>>)SugsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Suggestion s = new Suggestion();
                float id = Float.parseFloat(obj.get("id").toString());
                s.setId((int)id);
                s.setDescription(obj.get("description").toString());
                s.setType(obj.get("type").toString());
             
                s.setNomimg(obj.get("image").toString());
               // s.setDate(obj.get((Date)"date"));
           
           s.setDateS(obj.get("date").toString());
            NewMain n = new NewMain();
           //Date da = n.trs("");
           
            
          
             //   s.setDate();
                //Ajouter la tâche extraite de la réponse Json à la liste
                suggestion.add(s);
            }
            
            
        } catch (IOException ex) {
            
        }
         
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return suggestion;
    }
    
    public ArrayList<Suggestion> getAllSug(){
        String url = Statics.BASE_URL+"/AllSuggestions/";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String reponsejson=new String(req.getResponseData());
                
                suggestion = parseSugs(reponsejson);
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return suggestion;
    }
   
}
