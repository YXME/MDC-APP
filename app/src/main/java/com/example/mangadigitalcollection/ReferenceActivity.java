package com.example.mangadigitalcollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangadigitalcollection.api.DataFromAPI;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class ReferenceActivity extends AppCompatActivity {


    Button AddCommentButton;
    TableLayout CommentaireContainer;
    LinearLayout LicenceContainer;
    BottomNavigationView bottomNavigationView;

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        int ReferenceId = getIntent().getIntExtra("REFERENCE_ID", 0);
        Reference selectReference = DataFromAPI.getReferenceList().get(ReferenceId - 1);

        TableRow TabNbTome = findViewById(R.id.tabNbTome);
        TableRow TabEdition = findViewById(R.id.tabEdition);
        TableRow TabNbSaisons = findViewById(R.id.tabNbSaisons);
        TableRow TabEpisodeTotal = findViewById(R.id.tabNbEpisodeTotal);
        TableRow TabStudio = findViewById(R.id.tabStudio);

        ImageView ReferenceImage = findViewById(R.id.referenceImage);
        TextView ReferenceName = findViewById(R.id.referenceName);
        TextView OriginalName = findViewById(R.id.originalName);
        TextView Genre = findViewById(R.id.genre);

        TextView NbTome = findViewById(R.id.nbTome);
        TextView Edition = findViewById(R.id.edition);

        TextView NbSaisons = findViewById(R.id.nbSaisons);
        TextView NbEpisodesTotal = findViewById(R.id.nbEpisodeTotal);
        TextView Studio = findViewById(R.id.studio);

        Picasso.get().load(selectReference.getIllustrationLink()).resize(700, 1000).into(ReferenceImage);

        ReferenceName.append(selectReference.getName());
        OriginalName.append(selectReference.getOriginal_Name());
        String genre = selectReference.getGenre().substring(0, 1).toUpperCase() + selectReference.getGenre().substring(1);
        Genre.append(genre);

        if (selectReference.isManga()) {
            TabNbTome.setVisibility(View.VISIBLE);
            TabEdition.setVisibility(View.VISIBLE);
            Edition.append(DataFromAPI.getEditeurList().get(selectReference.getEditeurID() - 1).getName());
            NbTome.append("" + selectReference.getNbTomes());
        }

        if (selectReference.isAnime()) {
            TabNbSaisons.setVisibility(View.VISIBLE);
            TabEpisodeTotal.setVisibility(View.VISIBLE);
            TabStudio.setVisibility(View.VISIBLE);
            NbSaisons.append("" + selectReference.getNbSaisons());
            NbEpisodesTotal.append("" + selectReference.getNbEpisodesTotal());
            Studio.append(DataFromAPI.getStudioList().get(selectReference.getStudioID() - 1).getName());
        }

        AddCommentButton = findViewById(R.id.addCommentButton);
        AddCommentButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CommentaireActivity.class);
            intent.putExtra("REFERENCE_ID", selectReference.getId());
            intent.putExtra("FROM", getIntent().getIntExtra("FROM", 1));
            startActivity(intent);
            ReferenceActivity.this.finish();
        });

        CommentaireContainer = findViewById(R.id.referenceCommentaireContainer);

        if (!selectReference.getCommentaires().isEmpty()) {
            for (int i = 0; i < selectReference.getCommentaires().size(); i++) {

                TableRow row = new TableRow(ReferenceActivity.this);
                LinearLayout VerticalLayout = new LinearLayout(ReferenceActivity.this);
                LinearLayout HorizontalLayout = new LinearLayout(ReferenceActivity.this);
                LinearLayout HeaderLayout = new LinearLayout(ReferenceActivity.this);
                ImageView ProfilePicture = new ImageView(ReferenceActivity.this);
                TextView Username = new TextView(ReferenceActivity.this);
                TextView Note = new TextView(ReferenceActivity.this);
                TextView Text = new TextView(ReferenceActivity.this);

                TableLayout.LayoutParams lp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(10, 5, 10, 5);

                Username.setTextColor(Color.YELLOW);
                Note.setTextColor(Color.WHITE);
                Text.setTextColor(Color.WHITE);

                HorizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                VerticalLayout.setOrientation(LinearLayout.VERTICAL);
                HeaderLayout.setOrientation(LinearLayout.HORIZONTAL);

                Picasso.get().load(DataFromAPI.getUserList().get(selectReference.getCommentaires().get(i).getUserId() - 1).getPictureUrl()).resize(500, 500).into(ProfilePicture);

                Username.setText(DataFromAPI.getUserList().get(selectReference.getCommentaires().get(i).getUserId() - 1).getUsername());

                switch (selectReference.getCommentaires().get(i).getNote()) {
                    case 1:
                        Note.setText("★");
                        break;
                    case 2:
                        Note.setText("★★");
                        break;
                    case 3:
                        Note.setText("★★★");
                        break;
                    case 4:
                        Note.setText("★★★★");
                        break;
                    case 5:
                        Note.setText("★★★★★");
                        break;
                    default:
                        Note.setText("Non renseignée.");
                }

                ProfilePicture.setLayoutParams(lp);
                ProfilePicture.getLayoutParams().width = 150;
                ProfilePicture.getLayoutParams().height = 150;

                HorizontalLayout.addView(ProfilePicture);
                HeaderLayout.addView(Username);
                HeaderLayout.addView(Note);

                VerticalLayout.addView(HeaderLayout);
                Text.setText(selectReference.getCommentaires().get(i).getAvis());
                VerticalLayout.addView(Text);

                HorizontalLayout.addView(VerticalLayout);

                row.addView(HorizontalLayout);
                CommentaireContainer.addView(row);
                int finalI = i;
                ProfilePicture.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                    intent.putExtra("USER_ID", selectReference.getCommentaires().get(finalI).getUserId());
                    intent.putExtra("FROM", getIntent().getIntExtra("FROM", 1));
                    startActivity(intent);
                });

                Username.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                    intent.putExtra("USER_ID", selectReference.getCommentaires().get(finalI).getUserId());
                    intent.putExtra("FROM", getIntent().getIntExtra("FROM", 1));
                    startActivity(intent);
                });
            }
        }

        LicenceContainer = findViewById(R.id.licenceContainer);
        if (selectReference.getLicenceID() != 0) {

            DataFromAPI.getReferenceList().forEach(ref -> {
                if (selectReference.getLicenceID() == ref.getLicenceID() && selectReference.getId() != ref.getId()) {
                    ImageView RefImage = new ImageView(ReferenceActivity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(5, 0, 5, 0);
                    RefImage.setLayoutParams(lp);
                    RefImage.getLayoutParams().width = 400;
                    RefImage.getLayoutParams().height = 500;
                    Picasso.get().load(ref.getIllustrationLink()).resize(700, 1000).into(RefImage);
                    LicenceContainer.addView(RefImage);
                    RefImage.setOnClickListener(v -> {
                        Intent intent = new Intent(ReferenceActivity.this, ReferenceActivity.class);
                        intent.putExtra("REFERENCE_ID", ref.getId());
                        intent.putExtra("FROM", getIntent().getIntExtra("FROM", 1));
                        startActivity(intent);
                        ReferenceActivity.this.finish();
                    });
                }

            });
        }


        bottomNavigationView = findViewById(R.id.bottomNavBar);

        switch (getIntent().getIntExtra("FROM", 1)) {
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
                    overridePendingTransition(0, 0);
                    this.finish();
                    return true;
                case R.id.action_recherche:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(0, 0);
                    this.finish();
                    return true;
                case R.id.action_random:
                    startActivity(new Intent(getApplicationContext(), RandomActivity.class));
                    overridePendingTransition(0, 0);
                    this.finish();
                    return true;
                case R.id.action_profil:
                    startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                    overridePendingTransition(0, 0);
                    this.finish();
                    return true;
            }
            return false;
        });
    }
}