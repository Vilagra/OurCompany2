package com.example.ourcompany;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ourcompany.entity.Person;

import java.util.List;

/**
 * Created by Vilagra on 22.10.2016.
 */

public class NamePictureAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater layoutInflater;
    List<Person> persons;
    Location location;

    public NamePictureAdapter(Context ctx, List<Person> persons,Location loc) {
        this.ctx = ctx;
        this.persons = persons;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        location = loc;

    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item, parent, false);
            holder=new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.listViewItem);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Person person = (Person) getItem(position);
        if(location==Location.ENGLISH) {
            holder.textView.setText(person.getNameEn());
        }
        else if(location==Location.RUSSIAN) {
            holder.textView.setText(person.getNameRu());
        }
        holder.textView.setCompoundDrawablesWithIntrinsicBounds((int) person.getIdIcon(),0,0,0);
        return view;
    }
}
