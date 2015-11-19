package com.test.treinamentomobile.series.adapter;

/**
 * Created by treinamentomobile on 11/18/15.
 */
import android.view.View;
import android.view.ViewGroup;

import com.test.treinamentomobile.series.model.Person;
import com.test.treinamentomobile.series.view.PersonItemView;
import com.test.treinamentomobile.series.view.PersonItemView_;

import org.androidannotations.annotations.EBean;

/**
 * Created by hemobile on 17/11/15.
 */
@EBean
public class PersonAdapter extends AABaseAdapter<Person> {

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = PersonItemView_.build(context);
        }

        ((PersonItemView) convertView).bind(getItem(pos));
        return convertView;
    }
}
