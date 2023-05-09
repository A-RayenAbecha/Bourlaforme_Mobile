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
public class ServiceSeance {
     //singleton
    public static ServiceSeance instance = null ;
   
    public static boolean resultOk = true;

    //initilisation connection request
    private ConnectionRequest req;
   
    public static ServiceSeance getInstance() {
        if(instance == null )
            instance = new ServiceSeance();
        return instance ;
    }
     public ServiceSeance() {
        req = new ConnectionRequest();
       
    }
     //ajout
    public void ajoutSeance(Seance seance){
   
    String url=Statics.BASE_URL+"/addSeance1?nbr_grp="+seance.getNbr_grp()+"&description="+seance.getDescription()+"&nbr_seance="+seance.getNbr_seance()+"&color="+seance.getColor()+"&titre="+seance.getTitre();
    req.setUrl(url);
    req.addResponseListener((e)-> {
    String str = new String(req.getResponseData());
    System.out.println("data =="+str);
   
    });
   
    NetworkManager.getInstance().addToQueueAndWait(req);
   
   
   
   
    }

     //affichage
   
 /*   public ArrayList<Seance>affichageSeances() {
        ArrayList<Seance> result = new ArrayList<>();
       
        String url = Statics.BASE_URL+"/displaySeance1";
        req.setUrl(url);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
             try {
                    Map<String,Object>mapSeances = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapSeances.get("root");
                   
                    for(Map<String, Object> obj : listOfMaps) {
                        Seance se = new Seance();
                       
                        //dima id fi codename one float 5outhouha
                        Integer id = Integer.parseInt(obj.get("id").toString());
                       
                        Integer nbr_seance = Integer.parseInt(obj.get("nbr_seance").toString());
                        Integer nbr_grp = Integer.parseInt(obj.get("nbr_grp").toString());
                        String description = obj.get("description").toString();
                       
                        se.setId((int)id);
                        se.setNbr_grp((int)nbr_grp );
                        se.setNbr_grp((int)nbr_seance );
                        se.setDescription(description);
                       
                       
                       
                        //insert data into ArrayList result
                        result.add(se);
                       
                   
                    }
                   
                }catch(Exception ex) {
                   
                    ex.printStackTrace();
                }
           
            }
        });
       
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
       
    }
    */
   //Delete
   public boolean deleteSeance(int id ) {
        String url = Statics.BASE_URL +"/deleteSeance1?id="+id;
        
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
    //Update
     public boolean modifierSeance(Seance seance) {
         String str="aa.png";
        String url = Statics.BASE_URL +"/updateSeance1?id="+seance.getId()+"&nbr_seance="+seance.getNbr_seance()+"&nbr_grp="+seance.getNbr_grp()+"&description="+seance.getDescription()+"&nbr_seance="+seance.getNbr_seance()+"&color="+seance.getColor()+"&titre="+seance.getTitre();
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
        
    }
     
     

   public ArrayList<Seance>affichageSeances() {
        ArrayList<Seance> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displaySeance1";
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
                 //     System.out.println("TESTTTTTTTTTTT== "+listOfMaps.get(1));
                    for(Map<String, Object> obj : listOfMaps) {
                    
                        Seance re =new Seance();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float nbr_seance = Float.parseFloat(obj.get("nbrSeance").toString());
                        float nbr_grp = Float.parseFloat(obj.get("nbrGrp").toString());
    
                        String color = obj.get("color").toString();
                        String description = obj.get("description").toString();
                        String titre = obj.get("titre").toString();
                     
                        
                        re.setId((int)id);
                        re.setNbr_seance((int)nbr_seance);
                        re.setNbr_seance((int)nbr_grp);
                        re.setDescription(description);
                        re.setColor(color);
                       
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