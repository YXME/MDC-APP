package com.example.mangadigitalcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangadigitalcollection.dataStorage.Editeur;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.squareup.picasso.Picasso;

public class ReferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        int ReferenceId = getIntent().getIntExtra("REFERENCE_ID",0);
        Reference selectReference = DataFromAPI.getReferenceList().get(ReferenceId);


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

        ReferenceName.setText(selectReference.getName());
        OriginalName.setText(selectReference.getOriginal_Name());
        Genre.setText(selectReference.getGenre());


        if (selectReference.isManga()){
            Edition.setText(DataFromAPI.getEditeurList().get(selectReference.getEditeurID()).getName());
            NbTome.setText(selectReference.getNbTomes());
        }

        if (selectReference.isAnime()){
            NbSaisons.setText(selectReference.getNbSaisons());
            NbEpisodesTotal.setText(selectReference.getNbEpisodesTotal());
            Studio.setText(DataFromAPI.getStudioList().get(selectReference.getStudioID()).getName());
        }







    }
}