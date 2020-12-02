package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class Ads {

    private int Id;
    private String Url;

    public Ads(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Url = optJSONObject.optString("url");
    }

    public int getId() {
        return Id;
    }

    public String getUrl() {
        return Url;
    }
}
