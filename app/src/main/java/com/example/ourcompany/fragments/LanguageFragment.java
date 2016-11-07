package com.example.ourcompany.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ourcompany.Location;
import com.example.ourcompany.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends Fragment {

    public interface LanguageFragmentListener {
        void languageItemClicked(View v);
    }

    private LanguageFragmentListener listener;

    public LanguageFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (LanguageFragmentListener) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language, container, false);

    }

    public void onStart() {
        super.onStart();
        ListView listView = (ListView) getView().findViewById(R.id.list_language);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, Location.getArrayLanguages());
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listView,
                                            View v,
                                            int position,
                                            long id) {
                        if (listener != null) {
                            listener.languageItemClicked(v);
                        }
                    }
                };
        listView.setOnItemClickListener(itemClickListener);
    }
}
