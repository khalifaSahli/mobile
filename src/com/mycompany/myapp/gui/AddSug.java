/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
public class AddSug extends Form{

    public AddSug(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("ajouter un nouveau suggestion");
        setLayout(BoxLayout.y());
        
    
        TextComponent tfdes = new TextComponent().label("Description");
        TextComponent tftype = new TextComponent().label("type");
        Picker date = new Picker();
        Button btnValider = new Button("Add ");
        
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
                      Suggestion s = new Suggestion(tfdes.getText(), tftype.getText(),format.format(date.getDate()));
                      
                        if( ServiceSugggestion.getInstance().addsug(s))
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
