/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.front.article;

import com.mycomany.entities.Article;
import com.mycompany.services.ArticleService;
import com.mycompany.services.CommandeService;
import com.mycompany.services.PanierService;
import com.mycomany.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author rayen
 */
public class ShowAll extends Form {

    public static Article currentArticle = null;
    Label nameLabel, priceLabel, quantityLabel;
    Button modifBtn, deleteBtn;
    Container btnsContainer;
    Resources theme = UIManager.initFirstTheme("/theme1");

    public ShowAll(Form previous) {
        super("Article", new BoxLayout(BoxLayout.Y_AXIS));

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        this.refreshTheme();
    }

    private void addGUIs() {

        ArrayList<Article> listArticle = ArticleService.getInstance().getAll();
        if (listArticle.size() > 0) {
            for (Article article : listArticle) {
                this.add(makeCommandeModel(article));
                System.out.println(article);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private Component makeCommandeModel(Article article) {

        Container commandeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        commandeModel.setUIID("containerRounded");

//        dateLabel = new Label("date : " + commande.getDate());
//        dateLabel.setUIID("labelDefault");
//        montantLabel = new Label("Montant : " + commande.getMontant());
//        montantLabel.setUIID("labelDefault");
        quantityLabel = new Label("id : " + article.getId());
        quantityLabel.setUIID("labelDefault");
        nameLabel = new Label("nom : " + article.getNom());
        nameLabel.setUIID("labelDefault");
        priceLabel = new Label("prix : " + article.getPrix());
        priceLabel.setUIID("labelDefault");
        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        deleteBtn = new Button("ajout Panier");
        deleteBtn.setUIID("buttonDanger");
        deleteBtn.addActionListener(action -> {
            PanierService.getInstance().manage(article, false);
        });
        


//        btnsContainer.add(BorderLayout.CENTER, modifBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        commandeModel.addAll(
                quantityLabel,
                
                nameLabel,
                priceLabel,
                btnsContainer
        );

        return commandeModel;
    }
}
