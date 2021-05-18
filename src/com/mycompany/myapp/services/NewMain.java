/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.l10n.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author khlifa
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
     public Date trs(String d) throws com.codename1.l10n.ParseException {
         SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
          Date date1=new Date();
     
             date1 = formatter.parse(d);
      
        
          return date1 ;
    }
    }
    

