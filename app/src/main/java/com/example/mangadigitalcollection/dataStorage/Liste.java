package com.example.mangadigitalcollection.dataStorage;

import org.json.JSONObject;

import java.util.ArrayList;

public class Liste {
    private final int Id;
    private final String Name;
    private final int UserId;
    private ArrayList<Reference> ListContent;

    public Liste(JSONObject optJSONObject) {
        this.Id = optJSONObject.optInt("id");
        this.Name = optJSONObject.optString("name");
        this.UserId = optJSONObject.optInt("userId");
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getUserId() { return UserId; }

    public ArrayList<Reference> getListContent() {
        return ListContent;
    }

    public void setListContent(ArrayList<Reference> listContent) {
        ListContent = listContent;
    }


}
