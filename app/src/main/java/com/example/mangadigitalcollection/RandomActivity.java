package com.example.mangadigitalcollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.mangadigitalcollection.dataStorage.Reference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomActivity extends AppCompatActivity {

    ArrayList<Reference> ToRandomize = new ArrayList<>();
    ArrayList<Reference> TempRandomize = new ArrayList<>();

    BottomNavigationView bottomNavigationView;
    SwitchCompat isManga;
    SwitchCompat isAnime;
    Button Randomizer;
    Spinner Genre;
    TextView ErrorMessage;


    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        ToRandomize.addAll(DataFromAPI.getReferenceList());

        isManga = findViewById(R.id.switchManga);
        isAnime = findViewById(R.id.switchAnime);
        Genre = findViewById(R.id.spinnerGenre);
        Randomizer = findViewById(R.id.Randomize);
        ErrorMessage = findViewById(R.id.RandomErrorMessage);

        Randomizer.setOnClickListener(v -> {
            int SelectedReferenceID;
            ToRandomize.clear();
            ToRandomize.addAll(DataFromAPI.getReferenceList());
            TempRandomize.clear();
            TempRandomize.addAll(ToRandomize);
            if(isManga.isChecked() && isAnime.isChecked()){
                for(int i = 0; i < ToRandomize.size(); i++){
                    if (!ToRandomize.get(i).isManga() || !ToRandomize.get(i).isAnime()) TempRandomize.remove(ToRandomize.get(i));
                }
            }

            else if(isManga.isChecked()){
                for(int i = 0; i < ToRandomize.size(); i++){
                    if (!ToRandomize.get(i).isManga()) TempRandomize.remove(ToRandomize.get(i));
                }
            }

            else if(isAnime.isChecked()){
                for(int i = 0; i < ToRandomize.size(); i++){
                    if (!ToRandomize.get(i).isAnime()) TempRandomize.remove(ToRandomize.get(i));
                }
            }

            ToRandomize.retainAll(TempRandomize);

            switch(Genre.getSelectedItemPosition()){
                case 1:
                    for(int i = 0; i < ToRandomize.size(); i++){
                        if (!ToRandomize.get(i).getGenre().equals("shonen"))
                            TempRandomize.remove(ToRandomize.get(i));
                    }
                    break;
                case 2:
                    for(int i = 0; i < ToRandomize.size(); i++){
                        if (!ToRandomize.get(i).getGenre().equals("shoujo"))
                            TempRandomize.remove(ToRandomize.get(i));
                    }
                    break;
                case 3:
                    for(int i = 0; i < ToRandomize.size(); i++){
                        if (!ToRandomize.get(i).getGenre().equals("seinen"))
                            TempRandomize.remove(ToRandomize.get(i));
                    }
                    break;
                case 4:
                    for(int i = 0; i < ToRandomize.size(); i++){
                        if (!ToRandomize.get(i).getGenre().equals("ecchi"))
                            TempRandomize.remove(ToRandomize.get(i));
                    }
                    break;
                default:
                    break;
            }

            ToRandomize.retainAll(TempRandomize);

            if(!ToRandomize.isEmpty()) {
                int Size = ToRandomize.size();
                SelectedReferenceID = ToRandomize.get(ThreadLocalRandom.current().nextInt(0, Size)).getId();

                Intent intent = new Intent(getApplicationContext(), ReferenceActivity.class);
                intent.putExtra("REFERENCE_ID", SelectedReferenceID);
                intent.putExtra("FROM", 3);
                startActivity(intent);
                RandomActivity.this.finish();
            }
            else {
                ErrorMessage.setText("Aucune référence trouvée.");
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setSelectedItemId(R.id.action_random);

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
                case R.id.action_profil:
                    startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

    }
}