package com.example.mangadigitalcollection.ui;

import android.os.Bundle;

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
import com.example.mangadigitalcollection.R;
import com.example.mangadigitalcollection.dataStorage.Reference;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;


public class RecommendFragment extends Fragment {

    private View view;

    private TableLayout TableContainer;
    private String token;

    private List<Reference> New;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new, container, false);
        token = getActivity().getIntent().getStringExtra("TOKEN");

        DataFromAPI.FetchDataFromAPI();
        TableContainer = view.findViewById(R.id.tableContainer);

        New = DataFromAPI.getReferenceList();
        Collections.reverse(New);

        int Max = 8;
        if(New.size() < 8){
            Max = New.size();
        }

        for(int i = 0; i < Max; i++)
        {
            TableRow row = new TableRow(getActivity());
            LinearLayout layout = new LinearLayout(getActivity());
            ImageView illustration = new ImageView(getActivity());
            TextView title = new TextView(getActivity());

            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.HORIZONTAL);
            title.setGravity(Gravity.CENTER);
            title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

            title.setText(New.get(i).getName());
            Picasso.get().load(New.get(i).getIllustrationLink()).into(illustration);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            illustration.setLayoutParams(params);
            //illustration.getLayoutParams().width = 111;
            //illustration.getLayoutParams().height = 162;

            layout.addView(illustration);
            layout.addView(title);
            row.addView(layout);
            TableContainer.addView(row);
            row.setOnClickListener(v -> {
                //Intent intent = new Intent(getActivity(), MainActivity.class);
                //intent.putExtra("REFERENCE_ID", );
                //startActivity(intent);
            });
        }
        return view;
    }
}