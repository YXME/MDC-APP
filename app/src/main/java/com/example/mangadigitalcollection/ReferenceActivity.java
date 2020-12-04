package com.example.mangadigitalcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangadigitalcollection.dataStorage.Editeur;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class ReferenceActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        int ReferenceId = getIntent().getIntExtra("REFERENCE_ID",0);
        Reference selectReference = DataFromAPI.getReferenceList().get(ReferenceId - 1);


        ImageView ReferenceImage = findViewById(R.id.referenceImage);
        TextView ReferenceName = findViewById(R.id.referenceName);
        TextView OriginalName = findViewById(R.id.originalName);
        TextView Genre = findViewById(R.id.genre);

        TextView NbTome = findViewById(R.id.nbTome);
        TextView Edition = findViewById(R.id.edition);

        TextView NbSaisons = findViewById(R.id.nbSaisons);
        TextView NbEpisodesTotal = findViewById(R.id.nbEpisodeTotal);
        TextView Studio = findViewById(R.id.studio);

        Picasso.get().load(selectReference.getIllustrationLink()).resize(700,1000).into(ReferenceImage);

        ReferenceName.append(selectReference.getName());
        OriginalName.append(selectReference.getOriginal_Name());
        String genre = selectReference.getGenre().substring(0,1).toUpperCase() + selectReference.getGenre().substring(1);
        Genre.append(genre);

        if (selectReference.isManga()){
            Edition.append(DataFromAPI.getEditeurList().get(selectReference.getEditeurID() - 1).getName());
            NbTome.append("" + selectReference.getNbTomes());
        }

        if (selectReference.isAnime()){
            NbSaisons.append("" + selectReference.getNbSaisons());
            NbEpisodesTotal.append("" + selectReference.getNbEpisodesTotal());
            Studio.append(DataFromAPI.getStudioList().get(selectReference.getStudioID() - 1).getName());
        }








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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });
    }
}