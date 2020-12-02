package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class Licence {
    private int Id;
    private String Name;

    public Licence(JSONObject optJSONObject) {
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
