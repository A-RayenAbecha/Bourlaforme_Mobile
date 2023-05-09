/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.mycomany.entities.Article;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
/**
 *
 * @author Administrateur
 */
public class ServiceArticle {
    
       public List<Article> articles;

    public static ServiceArticle instance = null;
    public boolean resultOk;
    private Article article = new Article();
    private ConnectionRequest req;
    
    
    
    public static ServiceArticle getInstance()
    {
    if (instance==null)
        instance = new ServiceArticle();
    return instance;
    
    }
    
    
    public ServiceArticle()
    {
        req= new ConnectionRequest();
        
    
    }
    //crud ajout
    
    public void ajoutArticle(Article article){
    
    String url=Statics.BASE_URL+"/addArticle?nom="+article.getNom()+"&description="+article.getDescription()+"&prix="+article.getPrix()+"&image="+article.getImage();//+article.getImage();
    req.setUrl(url);
    req.addResponseListener((e)-> {
    String str = new String(req.getResponseData());
    System.out.println("data =="+str);
    
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    
    
    }
    
    //crud affich
    
   /* public ArrayList<Article>affichageArticle()
    {
     ArrayList<Article> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/displayArticle"; 
    req.setUrl(url);   
 req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override   
public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                
                jsonp = new JSONParser();
                
                try { 
                    Map<String,Object>mapArticles = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                       String str = new String(req.getResponseData());
                       
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapArticles.get("root");
                     System.out.println("data =="+listOfMaps.size());
                    for(Map<String, Object> obj : listOfMaps) {
                        Article ar = new Article();
                    
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float prix  = Float.parseFloat(obj.get("prix").toString());

                        String nom = obj.get("nom").toString();
                        String image = obj.get("image").toString();
                        String description = obj.get("description").toString();
                        String etat = obj.get("etat").toString();
                        
                        ar.setId((int)id);
                      //  System.out.println("Lahneeee "+nom);

                        ar.setImage(image);
                        ar.setPrix((int)prix);
                        ar.setNom(nom);
                        ar.setDescription(description);
                        ar.setEtat(etat);
                        
                        //Date 
                       /* String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        re.setDate(dateString);
                        
                        //insert data into ArrayList result
                        result.add(ar);
                           
                    
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
    
    //
    public List<Article> parseArticless(String jsonText) throws IOException{
        List<Article> articles = new ArrayList<>();
        //jsonText = jsonText.substring(0, jsonText.length()-1);
//  jsonText = jsonText.substring(1);
  String jsonText2= jsonText.replace('=', ':');
   //
   

   
   
        JSONParser j = new JSONParser(); 
        
         Map<String,Object>       articlesListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
        List<Map<String,Object>> list = (List<Map<String,Object>>)articlesListJson.get("root");
      
               //  System.out.println("TESTTTTTTTTTTT=="+ jsonText);

        for(Map<String,Object> obj : list){
        
            // System.out.println("Lahneeeee ==");
            Article a = new Article();
           // 
            int id = (int)Float.parseFloat(obj.get("id").toString());
            a.setId(id);
            String description = obj.get("description").toString();
            a.setDescription(description);
            String nom = obj.get("nom").toString();
            a.setNom(nom);
            int prix = (int)Float.parseFloat(obj.get("prix").toString());
            a.setPrix(prix);
            String etat = obj.get("etat").toString();
            a.setEtat(etat);
            String image = obj.get("image").toString();
            a.setImage(image);
            /*int nbrreact = (int)Float.parseFloat(obj.get("nbrreact").toString());
           a.setNbrreact(nbrreact);
            Map<String,Object> user = (Map<String,Object>)obj.get("auteur");
            a.setAuteur((int)Float.parseFloat(user.get("id").toString()));*/
           // System.out.println("TESTTTTTTTTTTT=="+articles);
            articles.add(a);
            // System.out.println("TESTTTTTTTTTTT4545== "+ a.getDescription());
        }
          
        return articles;
    }
    
    public Article parseArticle(String jsonText) throws IOException{
        JSONParser j;
         j= new JSONParser(); 
        jsonText = jsonText.substring(0, jsonText.length()-1);
  jsonText = jsonText.substring(1);
         //   System.out.println("Lahneeeee affichage wdkahba=="+  jsonText);

 
        
        Map<String,Object> articleJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        Article a = new Article(); 
        float id = 5;//Float.parseFloat((String) articleJson.get("data"));
            System.out.println("Lahneeeee affichage =="+  articleJson.get("nom"));
              System.out.println("Lahneeeee xagivac ==");
            a.setId((int)id);
                System.out.println("Lahneeeee 22 ==");
            String description ="ccc"; //articleJson.get("description").toString();
        
            a.setDescription(description);
            String nom ="ggg";// articleJson.get("nom").toString();
            System.out.println("Lahneeeee 33 ==");
            a.setNom(nom);
            String etat ="desarchiver" ;//articleJson.get("etat").toString();
            a.setEtat(etat);
            String image = "";//articleJson.get("image").toString();
            a.setImage(image);
           /* int nbrreact = (int)Float.parseFloat(articleJson.get("nbrreact").toString());
            a.setNbrreact(nbrreact);
            Map<String,Object> user = (Map<String,Object>)articleJson.get("auteur");
            a.setAuteur((int)Float.parseFloat(user.get("id").toString()));*/
            return a;
    }

    public List<Article> getAllArticles(){
       // ArrayList<Article> articles = new ArrayList<Article>();
        // List<Article> articles =  null;
        String url = Statics.BASE_URL+"/displayArticle";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try{
                    articles = parseArticless(new String(req.getResponseData()));
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    
    //
    
 public boolean deleteArticle(int id ) {
        String url = Statics.BASE_URL +"/deleteArticle?id="+id;
        
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
    
     public boolean modifierArticle(Article article) {
         String str="aa.png";
        String url = Statics.BASE_URL +"/updateArticle?id="+article.getId()+"&image="+str+ "&nom="+article.getNom()+"&Description="+article.getDescription()+"&prix="+article.getPrix();
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
    
    
}
