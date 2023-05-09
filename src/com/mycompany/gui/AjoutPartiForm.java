/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author obba
 */

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Article;
import com.mycomany.entities.Commentaire;
import com.mycomany.entities.Participation;
import com.mycompany.services.ServiceArticle;
import com.mycompany.services.ServiceCommentaire;
import com.mycompany.services.ServiceParticipation;
import java.text.SimpleDateFormat;
import java.util.Date;



public class AjoutPartiForm extends BaseForm{
    
    @SuppressWarnings("unused")
    private Participation c = new Participation();
    private ServiceParticipation cs = new ServiceParticipation();
    
    Form current;
    public AjoutPartiForm(Resources res){
     super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout participation");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes participations", barGroup);
        mesListes.setUIID("SelectBar");
       
       
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

      
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      
        TextField id_club = new TextField("", "entrer l id du club!!");
        id_club.setUIID("TextFieldBlack");
        addStringValue("id_club",id_club);
        
        TextField dateDebut = new TextField("", "entrer la date de debut!!");
        dateDebut.setUIID("TextFieldBlack");
        addStringValue("dateDebut",dateDebut);
        
        TextField dateFin = new TextField("", "entrer date de fin!!");
        dateFin.setUIID("TextFieldBlack");
        addStringValue("dateFin",dateFin);
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        
         Button btnReturn = new Button("Return");
        addStringValue("", btnReturn );
       
        btnReturn.addActionListener((e) -> {  //Appui sur boutton return
            try {
                InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                      final Dialog iDialog = ip.showInfiniteBlocking();
               iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                                   new    Crud_Participations(res).show();
               refreshTheme();//Actualisation
               }catch(Exception ex ) {
                ex.printStackTrace();
            }});
       
        
        
        //onclick button event 

        btnAjouter.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             try {
                 
                 if(id_club.getText().equals(""))  {
                     Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                 }
                 else {
                     InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                     
                     final Dialog iDialog = ip.showInfiniteBlocking();
                     
                     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                     
                     //njibo iduser men session (current user)
                     
                     //
                     int club =  Float.valueOf(id_club.getText()).intValue();
                            
                        String dd=     String.valueOf(dateDebut.getText()).toString();
                         String df=    String.valueOf(dateFin.getText()).toString();
                     //
                     Participation r = new Participation(club,dd,df);
                     
                     
                     
                     
                     System.out.println("data  reclamation == "+r.getId_club());
                     
                     
                     //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base
                     ServiceParticipation.getInstance().ajoutParticipation(r);
                     
                   //  iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                     
                     //ba3d ajout net3adaw lel ListREclamationForm
                 //    new ListReclamationForm(res).show();
                     
                     
                     refreshTheme();//Actualisation
                     
                 }
                 
             }catch(Exception ex ) {
                 ex.printStackTrace();
             }
         }
     });
        
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
