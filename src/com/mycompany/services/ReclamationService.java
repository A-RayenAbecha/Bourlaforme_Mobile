package com.mycompany.services;

import com.mycomany.entities.Reclamation;
import com.mycomany.utils.Statics;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReclamationService {

    public static ReclamationService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Reclamation> listReclamations;


    private ReclamationService() {
        cr = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }

    public ArrayList<Reclamation> getAll() {
        listReclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL2+"/reclamation");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listReclamations = getList();
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

        return listReclamations;
    }

    private ArrayList<Reclamation> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                int userId = 0;
                int coachId = 0;
                int clubId = 0;
                int articleId = 0;

                if (obj.get("userId") != null){
                    System.out.println(obj.get("userId")) ;
                            System.out.println(obj.get("userId") );
                    System.out.println(obj.get("userId") );
                    System.out.println(obj.get("userId") );
                    System.out.println(obj.get("userId") );

                    userId = (int) Float.parseFloat(obj.get("userId").toString());
                }

                if (obj.get("coachId") != null){
                    coachId = (int) Float.parseFloat(obj.get("coachId").toString());
                }

                if (obj.get("clubId") != null){
                    clubId = (int) Float.parseFloat(obj.get("clubId").toString());
                }

                if (obj.get("articleId") != null){
                    articleId = (int) Float.parseFloat(obj.get("articleId").toString());
                }

                Reclamation reclamation = new Reclamation(
             (int) Float.parseFloat(obj.get("id").toString()),
                        userId,
                        coachId,
                        clubId,
                        articleId,
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("date")),
                        (String) obj.get("etat"),
                        (String) obj.get("reponse"),
                        (String) obj.get("type"),
                        (String) obj.get("message")

                );

                listReclamations.add(reclamation);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listReclamations;
    }

    public int add(Reclamation reclamation) {
        return manage(reclamation, false);
    }

    public int edit(Reclamation reclamation) {
        return manage(reclamation, true);
    }

    public int manage(Reclamation reclamation, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL2+"/reclamation/edit");
            cr.addArgument("id", String.valueOf(reclamation.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL2+"/reclamation/add");
        }

        cr.addArgument("userId", String.valueOf(reclamation.getUserId()));
        cr.addArgument("coachId", String.valueOf(reclamation.getCoachId()));
        cr.addArgument("clubId", String.valueOf(reclamation.getClubId()));
        cr.addArgument("articleId", String.valueOf(reclamation.getArticleId()));
        cr.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDate()));
        cr.addArgument("etat", reclamation.getEtat());
        cr.addArgument("reponse", reclamation.getReponse());
        cr.addArgument("type", reclamation.getType());
        cr.addArgument("message", reclamation.getMessage());


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

    public int delete(int reclamationId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL2+"/reclamation/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(reclamationId));

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
}
