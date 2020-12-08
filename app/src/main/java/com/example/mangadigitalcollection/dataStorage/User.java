package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class User {
    private final int Id;
    private final String Email;
    private final String Username;
    private final String PictureUrl;
    private final String Biographie;

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

    public String getPictureUrl() {
        return PictureUrl;
    }

    public String getEmail() { return Email; }
}
