package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class User {
    private int Id;
    private String Username;
    private String PictureUrl;
    private String Biographie;

    public User(int id, String username, String illustrationLink, String biographie) {
        this.Id = id;
        this.Username = username;
        this.PictureUrl = illustrationLink;
        this.Biographie = biographie;
    }


    public User(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Username = optJSONObject.optString("username");
        this.PictureUrl = optJSONObject.optString("picture_url");
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
}
