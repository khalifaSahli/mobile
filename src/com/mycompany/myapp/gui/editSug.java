/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Suggestion;
import com.mycompany.myapp.services.ServiceSugggestion;

import com.mycompany.myapp.services.ServiceTask;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class editSug extends Form{

    public editSug(Form previous, Suggestion s) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Editer suggestion");
        setLayout(BoxLayout.y());
        
       TextField tfdes = new TextField("","Description");
       TextField tftype = new TextField("","type");
   Label lbi=new Label();
   lbi.setText(String.valueOf(s.getId()));
        Picker date = new Picker();
        Button btnValider = new Button("Editer ");
        tfdes.setText(s.getDescription());
        tftype.setText(s.getType());
        
        btnValider.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdes.getText().length()==0)||(tftype.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    
         
                      Suggestion s = new Suggestion(Integer.parseInt(lbi.getText()),tfdes.getText(), tftype.getText());
                      
                        if( ServiceSugggestion.getInstance().EditSug(s))
                            Dialog.show("Reussir","Suggestion Ajouter !!",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfdes,tftype,date,btnValider);
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
