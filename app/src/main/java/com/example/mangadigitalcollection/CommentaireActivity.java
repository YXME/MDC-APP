package com.example.mangadigitalcollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CommentaireActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button PostAction;
    EditText CommentaireText;
    Spinner Note;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaire);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        int userID = (DataFromAPI.getUserList().stream().filter(a -> (a.getEmail().equals(DataFromAPI.getCurrentUserID()))).findFirst().orElse(null)).getId();
        int referenceID = getIntent().getIntExtra("REFERENCE_ID", - 1);
        if(referenceID == -1) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            CommentaireActivity.this.finish();
        }

        PostAction = findViewById(R.id.Post);
        CommentaireText = findViewById(R.id.CommentText);
        Note = findViewById(R.id.spinnerRating);

        PostAction.setOnClickListener(v -> {
            if(CommentaireText.getText() != null) {
                try {
                    ConnexionRest connectionRest = new ConnexionRest();
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("reference_id", referenceID);
                    jsonData.put("user_id", userID);
                    jsonData.put("note", Note.getSelectedItem());
                    jsonData.put("commentaire", CommentaireText.getText());
                    connectionRest.setObj(jsonData);
                    connectionRest.setAction("Commentaire");
                    connectionRest.setToken(DataFromAPI.getToken());
                    connectionRest.execute("POST");
                    connectionRest.get();
                } catch (JSONException | InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                DataFromAPI.FetchDataFromAPI();
                Intent intent = new Intent(getApplicationContext(), ReferenceActivity.class);
                intent.putExtra("REFERENCE_ID", referenceID);
                intent.putExtra("FROM", getIntent().getIntExtra("FROM", 1));
                startActivity(intent);
                CommentaireActivity.this.finish();
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavBar);

        switch(getIntent().getIntExtra("FROM",1)){
            case 1:
                bottomNavigationView.setSelectedItemId(R.id.action_accueil);
                break;
            case 2:
                bottomNavigationView.setSelectedItemId(R.id.action_recherche);
                break;
            case 3:
                bottomNavigationView.setSelectedItemId(R.id.action_random);
                break;
            case 4:
                bottomNavigationView.setSelectedItemId(R.id.action_profil);
                break;
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_accueil:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.action_recherche:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.action_random:
                    startActivity(new Intent(getApplicationContext(), RandomActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.action_profil:
                    startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }
}