/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycomany.entities.Article;
import com.mycomany.utils.Statics;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.stripe.exception.StripeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author rayen
 */
public class PanierService {

    public static PanierService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Article> listArticle;

    private PanierService() {
        cr = new ConnectionRequest();
    }

    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }

    public ArrayList<Article> getAll() {
        listArticle = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/panierAPI/");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listArticle = getList();
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

        return listArticle;
    }

    private ArrayList<Article> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) parsedJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Article article = new Article();
                article.setQuantity((int) Float.parseFloat(obj.get("quantity").toString()));
                Map<String, Object> map = (Map<String, Object>) obj.get("Article");
                article.setId((int) Float.parseFloat(map.get("id").toString()));
                article.setDescription((String) map.get("description"));
                article.setNom((String) map.get("nom"));
                article.setImage((String) map.get("image"));
                article.setPrix((int) Float.parseFloat(map.get("prix").toString()));

                listArticle.add(article);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listArticle;
    }

    public int add(Article article) {
        return manage(article, false);
    }

    public int edit(Article article) {
        return manage(article, true);
    }

    public int manage(Article article, boolean isEdit) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/updateFormAPI/" + article.getId());
            cr.addArgument("id", String.valueOf(article.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/panierAPI/add/" + article.getId());
        }

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int articleId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/panierAPI/remove/" + articleId);
        cr.setHttpMethod("POST");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }

    public int deleteOne(int articleId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/panierAPI/removeOne/" + articleId);
        cr.setHttpMethod("POST");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }

    public String Payer(String numCard, String expMois, String exAnnee, String cvv, int prix) {
        String nom = "sddsdsdsds";
        String email = "rayen.abecha@gmail.com";
        String payed;
        PaymentService servicePayment = new PaymentService(email, nom, prix * 100, numCard, expMois, exAnnee, cvv);
        try {
            servicePayment.payer();
            payed = "true";
            try {
                cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
                NetworkManager.getInstance().addToQueueAndWait(cr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (StripeException ex) {
            ex.printStackTrace();
            payed = "error payment";
        }
        return payed;
    }
}
