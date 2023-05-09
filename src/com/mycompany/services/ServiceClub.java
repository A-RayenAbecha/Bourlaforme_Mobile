/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Club;
import com.mycomany.entities.Seance;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceClub {
     //singleton
    public static ServiceClub instance = null ;
   
    public static boolean resultOk = true;

    //initilisation connection request
    private ConnectionRequest req;
   
    public static ServiceClub getInstance() {
        if(instance == null )
            instance = new ServiceClub();
        return instance ;
    }
     public ServiceClub() {
        req = new ConnectionRequest();
       
    }
     //ajout
    public void ajoutClub(Club club){
   
    String url=Statics.BASE_URL+"/addClub1?nom="+club.getNom()+"&localisation="+club.getLocalisation()+"&telephone="+club.getTelephone()+"&description="+club.getDescription()+"&type_activite="+club.getType_activite()+"image"+club.getImage();
    req.setUrl(url);
    req.addResponseListener((e)-> {
    String str = new String(req.getResponseData());
    System.out.println("data =="+str);
   
    });
   
    NetworkManager.getInstance().addToQueueAndWait(req);
   
   
   
   
    }
    
       //Update
    /* public boolean modifierClub(Club club) {
         
         String url = Statics.BASE_URL +"/updateClub1?id="+club.getId()+"&nom="+club.getNom()+"&localisation="+club.getLocalisation()+"&telephone"+club.getTelephone()+"&description="+club.getDescription()+"&type_activite="+club.getType_activite()+"&image="+club.getImage();

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    } */
    
     //Update
     public boolean modifierClub(Club club) {
         String str="aa.png";
        String url = Statics.BASE_URL +"/updateClub1?id="+club.getId()+"&nom="+club.getNom()+"&localisation="+club.getLocalisation()+"&description="+club.getDescription()+"&telephone="+club.getTelephone()+"&type_activite="+club.getType_activite()+"&image"+club.getImage();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//
    return resultOk;
        
    }
     
      //Delete
   public boolean deleteClub(int id ) {
        String url = Statics.BASE_URL +"/deleteClub1?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
   
   
   public ArrayList<Club>affichageClubs() {
        ArrayList<Club> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayClub1";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    String jsonText =  req.getResponseData().toString();
                      
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                   //   System.out.println("TESTTTTTTTTTTT== "+listOfMaps.get(1));
                    for(Map<String, Object> obj : listOfMaps) {
                   
                        Club re =new Club();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                       
    
                        String nom = obj.get("nom").toString();
                        
                         String localisation = obj.get("localisation").toString();
                         
                        String description ="";// obj.get("description").toString();
                        
                        String telephone = obj.get("telephone").toString();
                        
                        String type_activite = obj.get("typeActivite").toString();
                       // System.out.println("TESTTTTTTTTTTT==");
                        String image = obj.get("image").toString();
                        
                     
                        
                        re.setId((int)id);
                        re.setNom(nom);     
                        re.setLocalisation(localisation);
                        re.setDescription(description);
                        re.setTelephone(telephone);
                        re.setType_activite(type_activite);
                        re.setImage(image);
                       
                        //Date 
                        /*
                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);*/
                    
                     
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }

}