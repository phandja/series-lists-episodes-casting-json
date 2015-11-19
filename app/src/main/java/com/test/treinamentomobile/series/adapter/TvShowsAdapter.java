package com.test.treinamentomobile.series.adapter;

/**
 * Created by treinamentomobile on 11/19/15.
 */
import android.view.View;
import android.view.ViewGroup;

import com.test.treinamentomobile.series.model.TvShow;
import com.test.treinamentomobile.series.view.TvShowItemView;
import com.test.treinamentomobile.series.view.TvShowItemView_;

import org.androidannotations.annotations.EBean;

/**
 * Created by hemobile on 17/11/15.
 */

@EBean
public class TvShowsAdapter extends AABaseAdapter<TvShow> {

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = TvShowItemView_.build(context);
        }
        ((TvShowItemView) convertView).bind(getItem(position));
        return convertView;
    }
}
