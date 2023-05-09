/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.util.Resources;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Commentaire;
import com.mycompany.services.ServiceCommentaire;

import java.util.ArrayList;

public class CommentaireForm extends BaseForm{
    ServiceCommentaire commentaireService = new ServiceCommentaire();
 
    public ArrayList<Commentaire> commentaires;
    @SuppressWarnings("unused")
    private Resources theme;
    
    public CommentaireForm(Resources res, int article_id){
        super("Commentaires", GridLayout.autoFit());
        this.theme = theme;
        
       
        System.out.println(commentaires);
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
       
        
       
}

}
