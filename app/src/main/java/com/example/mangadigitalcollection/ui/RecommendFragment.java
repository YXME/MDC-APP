package com.example.mangadigitalcollection.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mangadigitalcollection.DataFromAPI;
import com.example.mangadigitalcollection.MainActivity;
import com.example.mangadigitalcollection.R;
import com.example.mangadigitalcollection.dataStorage.Ads;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Random;


public class RecommendFragment extends Fragment {

    private View view;

    private TableLayout TableContainer;
    private String token;

    private List<Reference> Recommended;
    private List<Ads> AdList;

    public RecommendFragment() {
        // Required empty public constructor
    }

    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        token = getActivity().getIntent().getStringExtra("TOKEN");

        DataFromAPI.FetchDataFromAPI();
        TableContainer = view.findViewById(R.id.tableContainer);

        Recommended = DataFromAPI.getReferenceList();
        Recommended.removeIf(a -> (a.isSponsorised()));
        AdList = DataFromAPI.getAdsList();

        int Max = 8;
        if(Recommended.size() < 8){
            Max = Recommended.size();
        }

        for(int i = 0; i < Max; i++)
        {
            TableRow row = new TableRow(getActivity());
            LinearLayout layout = new LinearLayout(getActivity());
            ImageView illustration = new ImageView(getActivity());

            if(i == 2 || i == 5){
                Random AdId = new Random();

                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                layout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                Picasso.get().load(AdList.get(AdId.nextInt(3 - 1 + 1) + 1).getUrl()).resize(700, 1000).into(illustration);

                illustration.setLayoutParams(params);
                illustration.getLayoutParams().width = 350;
                illustration.getLayoutParams().height = 500;

                layout.addView(illustration);
                row.addView(layout);
                TableContainer.addView(row);
            }
            else {
                TextView title = new TextView(getActivity());

                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                layout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                title.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                title.setText(Recommended.get(i).getName());
                title.setTextColor(Color.WHITE);
                //title.setLayoutParams(params);
//            title.getLayoutParams().width = 1000;

                Picasso.get().load(Recommended.get(i).getIllustrationLink()).resize(700, 1000).into(illustration);

                illustration.setLayoutParams(params);
                illustration.getLayoutParams().width = 350;
                illustration.getLayoutParams().height = 500;

                layout.addView(illustration);
                layout.addView(title);
                row.addView(layout);
                TableContainer.addView(row);
                int finalI = i;
                row.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("REFERENCE_ID", Recommended.get(finalI).getId());
                    startActivity(intent);
                });
            }
        }
        return view;
    }
}