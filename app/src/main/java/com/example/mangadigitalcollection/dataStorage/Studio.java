package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

public class Studio {

    private final int Id;
    private final String Name;

    public Studio(JSONObject optJSONObject) {
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
