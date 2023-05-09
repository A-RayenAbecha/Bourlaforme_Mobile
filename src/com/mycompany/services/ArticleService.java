/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycomany.entities.Article;
import com.mycomany.entities.Commande;
import com.mycomany.utils.Statics;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rayen
 */
public class ArticleService {

    public static ArticleService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Article> listCommande;

    private ArticleService() {
        cr = new ConnectionRequest();
    }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    public ArrayList<Article> getAll() {
        listCommande = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/APIproducts/");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listCommande = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCommande;
    }

    private ArrayList<Article> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {

                Article article = new Article();
                article.setId((int) Float.parseFloat(obj.get("id").toString()));
                article.setDescription((String) obj.get("description"));
                article.setNom((String) obj.get("nom"));
                article.setImage((String) obj.get("image"));
                article.setPrix((int) Float.parseFloat(obj.get("prix").toString()));
                listCommande.add(article);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCommande;
    }
  
}
