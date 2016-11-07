package com.example.ourcompany.fragments;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ourcompany.Location;
import com.example.ourcompany.MyApplication;
import com.example.ourcompany.NamePictureAdapter;
import com.example.ourcompany.R;
import com.example.ourcompany.StoreOfPersons;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceNameFragment extends Fragment {
    MyApplication myApplication;
    NameFrafmentListener listener;
    String language;
    Location location;

    public void setLanguage(String language) {
        this.language = language;
    }

    public interface NameFrafmentListener{
        void nameItemClicked(View v);
    }

    public ChoiceNameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (NameFrafmentListener) activity;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            language = savedInstanceState.getString("language");
        }
        Locale currentLocale=Locale.getDefault();
        location=Location.getLocationByLanguage(language,currentLocale);
        Locale locale = location.getLocale();
        if(locale.equals(currentLocale)) {
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getResources().updateConfiguration(configuration, null);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice_name, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("language",language);
    }

    @Override
    public void onStart() {
        super.onStart();
        myApplication =((MyApplication)getActivity().getApplication());
        myApplication.setStoreOfPersons(new StoreOfPersons(getActivity(),location));
        NamePictureAdapter adapter = new NamePictureAdapter(getView().getContext(),
                myApplication.getStoreOfPersons().getListAllPersons(),
                myApplication.getStoreOfPersons().getLoc());
        ListView listView = (ListView) getView().findViewById(R.id.list_name);
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener!=null) {
                    listener.nameItemClicked(view);
                }
            }
        };
        listView.setOnItemClickListener(itemClickListener);

    }

}
