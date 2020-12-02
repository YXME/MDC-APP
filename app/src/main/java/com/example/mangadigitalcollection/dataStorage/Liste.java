package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

import java.util.ArrayList;

public class Liste {
    private int Id;
    private String Name;
    private ArrayList<Reference> ListContent;

    public Liste(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Name = optJSONObject.optString("name");
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public ArrayList<Reference> getListContent() {
        return ListContent;
    }

    public void setListContent(ArrayList<Reference> listContent) {
        ListContent = listContent;
    }
}
