package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class Editeur {

    private int Id;
    private String Name;

    public Editeur(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Name = optJSONObject.optString("name");
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
