package com.example.mangadigitalcollection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;


public class NewFragment extends Fragment {

    private View view;
    private ArrayList<TableRow> ReferenceList = new ArrayList<TableRow>();
    private List<Reference> New;

    public NewFragment() {
        // Required empty public constructor
    }


    public static NewFragment newInstance() {
        NewFragment fragment = new NewFragment();

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


        return view;
    }
}