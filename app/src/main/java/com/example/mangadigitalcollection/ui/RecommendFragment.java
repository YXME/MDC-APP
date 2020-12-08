package com.example.mangadigitalcollection.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.mangadigitalcollection.DataFromAPI;
import com.example.mangadigitalcollection.R;
import com.example.mangadigitalcollection.ReferenceActivity;
import com.example.mangadigitalcollection.dataStorage.Ads;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class RecommendFragment extends Fragment {

    private final ArrayList<Reference> Recommended = new ArrayList<>();

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        DataFromAPI.FetchDataFromAPI();
        TableLayout tableContainer = view.findViewById(R.id.tableContainer);

        Recommended.addAll(DataFromAPI.getReferenceList());

        for(int i = 0; i < Recommended.size(); i++){
            if (!Recommended.get(i).isSponsorised()) Recommended.remove(i);
        }

        ArrayList<Ads> adList = DataFromAPI.getAdsList();

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

                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                layout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                Picasso.get().load(adList.get(ThreadLocalRandom.current().nextInt(0, 3 + 1)).getUrl()).resize(700, 1000).into(illustration);

                illustration.setLayoutParams(params);
                illustration.getLayoutParams().width = 400;
                illustration.getLayoutParams().height = 500;

                layout.addView(illustration);
                row.addView(layout);
                tableContainer.addView(row);
            }
            else {
                TextView title = new TextView(getActivity());

                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                layout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                Picasso.get().load(Recommended.get(i).getIllustrationLink()).resize(700, 1000).into(illustration);

                illustration.setLayoutParams(params);
                illustration.getLayoutParams().width = 400;
                illustration.getLayoutParams().height = 500;

                title.setLayoutParams(textParams);
                title.getLayoutParams().width = 680;
                title.getLayoutParams().height = 500;
                title.setPadding(25,0,50,0);
                title.setGravity(Gravity.CENTER);
                title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                title.setMaxLines(2);
                title.setEllipsize(TextUtils.TruncateAt.END);
                title.setText(Recommended.get(i).getName());
                title.setTextColor(Color.WHITE);

                layout.addView(illustration);
                layout.addView(title);
                row.addView(layout);
                tableContainer.addView(row);
                int finalI = i;
                row.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), ReferenceActivity.class);
                    intent.putExtra("REFERENCE_ID", Recommended.get(finalI).getId());
                    intent.putExtra("FROM", 1);
                    startActivity(intent);
                });
            }
        }
        return view;
    }
}