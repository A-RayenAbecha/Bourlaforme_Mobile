/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Administrateur
 */
public class ServiceTypeFront extends BaseForm{
    
      Form current;
         
          public ServiceTypeFront(Resources res)
          {
        super("Newsfeed", BoxLayout.y());
        
     /*     Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
            setTitle("Application Mobile");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e ->  {
            
        });*/

          Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        /*
        Button btnArticle = new Button("Articles");
        addStringValue("", btnArticle);
        
        Button btnCommentaire = new Button("Commentaires");
        addStringValue("", btnCommentaire);
        
        Button btnSeance = new Button("Seances");
        addStringValue("", btnSeance);
        */
        Button btnParticipation = new Button("Partcipations");
        addStringValue("", btnParticipation);
        
         Button btnReturn = new Button("Return");
        addStringValue("", btnReturn );
       
  /*       btnArticle.addActionListener((e) -> {  //Appui sur boutton article
            try {
 InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
       final Dialog iDialog = ip.showInfiniteBlocking();
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    new   ListArticleForm(res).show();
refreshTheme();//Actualisation
}
            catch(Exception ex ) {
                ex.printStackTrace();
            }});*/
         
////////////////////
         
   /*      
          btnCommentaire.addActionListener((e) -> {  //Appui sur boutton Commentaire
            try {
 InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
       final Dialog iDialog = ip.showInfiniteBlocking();
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    new     ListCommentaireForm(res).show();
refreshTheme();//Actualisation
}
            catch(Exception ex ) {
                ex.printStackTrace();
            }});
          */
//////////////////
/*
 btnCommentaire.addActionListener((e) -> {  //Appui sur boutton Commentaire
            try {
 InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
       final Dialog iDialog = ip.showInfiniteBlocking();
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    new     ListCommentaireForm(res).show();
refreshTheme();//Actualisation
}
            catch(Exception ex ) {
                ex.printStackTrace();
            }}); */
/////////

btnParticipation.addActionListener((e) -> {  //Appui sur boutton Service
            try {
 InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
       final Dialog iDialog = ip.showInfiniteBlocking();
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    new     Crud_Participations(res).show();
refreshTheme();//Actualisation
}
            catch(Exception ex ) {
                ex.printStackTrace();
            }});
         
     
         
      ////////////
      ///////////
         
          btnReturn.addActionListener((e) -> {  //Appui sur boutton return
            try {
 InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
       final Dialog iDialog = ip.showInfiniteBlocking();
iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    new    FrontBack(res).show();
refreshTheme();//Actualisation
}
            catch(Exception ex ) {
                ex.printStackTrace();
            }});
}
          
           private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
    
    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);   
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }
    
     private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
 
          
    
}
