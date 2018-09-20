package com.example.jayanthdevulapally.volley_demo.model;

/**
 * Created by jayanthdevulapally on 3/26/18.
 */

public class Anime {

    private String name;
    private String description;
    private String Rating;
    private int episode;
    private String categorie;
    private String studio;
    private String img;

    public Anime(){

    }

    public Anime(String name, String description, String rating, int episode, String catagorie, String studio, String image_url){
        this.name = name;
        this.description = description;
        this.Rating = rating;
        this.episode = episode;
        this.categorie = catagorie;
        this.studio = studio;
        this.img = image_url;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public  String getRating(){
        return Rating;
    }
    public void setRating(String rating){
        this.Rating = rating;
    }

    public int getEpisode(){
        return episode;
    }
    public void setEpisode(int episode){
        this.episode = episode;
    }

    public String getCategorie(){
        return categorie;
    }
    public void setCategorie(String categorie){
        this.categorie = categorie;
    }

    public String getStudio(){
        return studio;
    }
    public void setStudio(String studio){
        this.studio = studio;
    }

    public String getImageUrl(){
        return img;
    }
    public void setImageUrl(String image_url){
        this.img = image_url;
    }
}
