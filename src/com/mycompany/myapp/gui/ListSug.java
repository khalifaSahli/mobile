/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Suggestion;
import com.mycompany.myapp.services.ServiceSugggestion;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListSug extends Form{
   private Resources theme;
        private Form current,hi;
    public ListSug(Form previous) {
        setTitle("List Suggestions");
   
     
            theme = UIManager.initFirstTheme("/theme");

       ArrayList<Suggestion> array=ServiceSugggestion.getInstance().getAllSug();
              hi = new Form("Hi World", BoxLayout.y()); 
      for(Suggestion s:array){
    
          System.out.print(s);
       Container cnt1=new Container(BoxLayout.y());
        Label lbdes=new Label("Description :"+s.getDescription());
        Label lbtype=new Label("Type :"+s.getType());
   String h = s.getDateS() ;
        Label lbdate=new Label("date :"+s.getDateS().substring(0, h.indexOf("T")));
        Button btn=new Button("Voir");
        cnt1.add(lbdes);
        cnt1.add(lbtype);
         cnt1.add(lbdate);
        cnt1.add(btn);
        
        Container cnt2=new Container(BoxLayout.x());
        Label lbimg=new Label(theme.getImage("icon.png"));
        
        cnt2.add(lbimg);
        cnt2.add(cnt1);
        Form F2=new Form("Détails",BoxLayout.y());
        
        Label lbi=new Label();
   
       
        Label lbnum=new Label();
       
        
        
        
        btn.addActionListener((e)->{
             setTitle("Editer suggestion");
            lbi.setIcon(theme.getImage(s.getNomimg()));
                 Label lbdes1=new Label("description");
             TextField tfdes = new TextField("","Description");
             Label lbtype1=new Label("type");
       TextField tftype = new TextField("","type");
        Label lbdate1=new Label("date");
        Picker date = new Picker();
           Label lbid=new Label();
   lbid.setText(String.valueOf(s.getId()));
    
       
        Button btnValider = new Button("Editer ");
        Button btnSup = new Button("Suprimer ");
        tfdes.setText(s.getDescription());
        tftype.setText(s.getType());
        F2.addAll(lbi,lbdes1,tfdes,lbtype1,tftype,lbdate1,date,btnValider);
        btnValider.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdes.getText().length()==0)||(tftype.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    String pattern = "yyyy-MM-dd";
                      SimpleDateFormat format = new SimpleDateFormat(pattern);
                      Suggestion s = new Suggestion(Integer.parseInt(lbid.getText()),tfdes.getText(), tftype.getText(),lbi.getName(),format.format(date.getDate()));
                      
                        if( ServiceSugggestion.getInstance().EditSug(s))
                            Dialog.show("Reussir","Suggestion modifier !!",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
           btnSup.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    try {
                 
                      Suggestion s = new Suggestion(Integer.parseInt(lbid.getText()));
                      
                        if( ServiceSugggestion.getInstance().EditSug(s))
                            Dialog.show("Reussir","Suggestion modifier !!",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            
        });
        
     
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e1-> previous.showBack()); // Revenir vers l'interface précédente
    
            F2.show();
            
        });
        
        F2.getToolbar().addCommandToLeftBar("back", null, ev->{show();});
        cnt2.setLeadComponent(btn);
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 
                
                e-> previous.showBack());
   
   
  add(cnt2);
    }
     
         show(); 
//     public Container addItem(Suggestion s)
//    {
//    Container cnt1=new Container(BoxLayout.y());
//        Label lbdes=new Label("Description :"+s.getDescription());
//        Label lbtype=new Label("Type :"+s.getType());
//        Button btn=new Button("Suprimer");
//        cnt1.add(lbdes);
//        cnt1.add(lbtype);
//        cnt1.add(btn);
//        
//        Container cnt2=new Container(BoxLayout.x());
//        Label lbimg=new Label(theme.getImage("icon.png"));
//        
//        cnt2.add(lbimg);
//        cnt2.add(cnt1);
//        Form F2=new Form("Détails",BoxLayout.y());
//        Label lbi=new Label();
//        Label lbn=new Label();
//        Label lbem=new Label();
//        Label lbag=new Label();
//        Label lbnum=new Label();
//       
//        
//        F2.addAll(lbi,lbn,lbem,lbag,lbnum);
//        
//        btn.addActionListener((e)->{
//            lbi.setIcon(theme.getImage(s.getNomimg()));
//            lbn.setText("Description :"+s.getDescription());
//            lbem.setText("type :"+s.getType());
//            lbnum.setText("date :"+s.getDate());
//            
//            
//            
//            System.out.println(s);
//            F2.show();
//            
//        });
//        
//        F2.getToolbar().addCommandToLeftBar("back", null, ev->{hi.show();});
//        cnt2.setLeadComponent(btn);
//        return cnt2;
//    }
    
}
}