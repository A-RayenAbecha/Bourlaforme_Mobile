/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anarchy.gui.front.article;

import com.anarchy.entities.Article;
import com.anarchy.services.ArticleService;
import com.anarchy.services.CommandeService;
import com.anarchy.services.PanierService;
import com.anarchy.utils.Statics;
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
    Resources theme = UIManager.initFirstTheme("/theme");

    public ShowAll(Form previous) {
        super("Panier", new BoxLayout(BoxLayout.Y_AXIS));

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
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private Component makeCommandeModel(Article article) {
        ImageViewer imageIV;

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
        
        if (article.getImage() != null) {
            String url = Statics.ARTICLE_IMAGE_URL + article.getImage();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("default.jpg").fill(1100, 500), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
        } else {
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
        }
        imageIV.setFocusable(false);

//        btnsContainer.add(BorderLayout.CENTER, modifBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        commandeModel.addAll(
                imageIV,
                quantityLabel,
                //                dateLabel,
                //                montantLabel,
                //                ConfirmeAdminLabel,
                btnsContainer
        );

        return commandeModel;
    }
}
