package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private int Id;
    private String Email;
    private String Username;
    private String PictureUrl;
    private String Biographie;
    private ArrayList<Liste> Listes;

    public User(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Email = optJSONObject.optString("email");
        this.Username = optJSONObject.optString("username");
        this.PictureUrl = optJSONObject.optString("profile_pic_url");
        this.Biographie = optJSONObject.optString("biographie");
    }

    public int getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public String getBiographie() {
        return Biographie;
    }

    public ArrayList<Liste> getListes() {
        return Listes;
    }

    public void setListes(ArrayList<Liste> listes) {
        Listes = listes;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public String getEmail() { return Email; }
}
