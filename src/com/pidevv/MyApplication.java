package com.pidevv;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.mycompany.gui.AjoutArticleForm;
import com.mycompany.gui.ModifierArticleForm;
import com.mycompany.gui.ListArticleForm;
import com.mycompany.gui.SuppArticleForm;
import com.mycompany.gui.AjoutCommentaireForm;
import com.mycompany.gui.AjoutSeanceForm;
import com.mycompany.gui.ListCommentaireForm;
import com.mycompany.gui.AjoutCommentaireForm;
import com.mycompany.gui.AjoutReservationForm;
import com.mycompany.gui.ListSeanceForm;
import com.mycompany.gui.SuppCommentaireForm;
//import com.mycompany.gui.MapForm;
import com.mycompany.gui.FrontBack;
//import com.mycompany.gui.SignInForm;
//import com.mycompany.gui.SignUpForm;
//import com.mycompany.gui.StatistiquePieForm;
import com.mycompany.gui.SuppReservationForm;
import com.mycompany.gui.SuppSeanceForm;
import com.mycompany.gui.ModifierArticleForm2;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");
        
        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){    
            current.show();
            return;
        }
       // new ListSeanceForm(theme).show();
       //houni awl interface yet7al
       //new MapForm(); //n7oto signup bch yjibha awl form
       // new SignInForm(theme).show();

       
       // new AjoutSeanceForm(theme).show();
       // new ListSeanceForm(theme).show();
       // new ModifierSeanceForm(theme).show();
          
       //  new ListArticleForm(theme).show();
       // new ModifierArticleForm(theme).show();
       // new AjoutArticleForm(theme).show(); 
      //new SuppArticleForm(theme).show();
       
       
       
       //new SuppCommentaireForm(theme).show();
      // new ListCommentaireForm(theme).show();
        // new ModifierArticleForm2(theme).show();
        // new AjoutCommentaireForm(theme).show(); 
       
       
     //new FrontBack(theme).show();
   new FrontBack(theme).show();
  // new SuppReservationForm(theme).show();
       
     //  
     
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
