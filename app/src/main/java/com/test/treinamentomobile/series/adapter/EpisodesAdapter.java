package com.test.treinamentomobile.series.adapter;

/**
 * Created by treinamentomobile on 11/18/15.
 */

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.treinamentomobile.series.R;
import com.test.treinamentomobile.series.model.Episode;
import com.test.treinamentomobile.series.view.EpisodeItemView;
import com.test.treinamentomobile.series.view.EpisodeItemView_;

import org.androidannotations.annotations.EBean;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by hemobile on 17/11/15.
 */
@EBean
public class EpisodesAdapter extends AABaseAdapter<Episode> implements StickyListHeadersAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = EpisodeItemView_.build(context);
        }
        ((EpisodeItemView) convertView).bind(getItem(position));
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) convertView;
        if (textView == null) {
            textView = new TextView(context);
            textView.setBackgroundResource(R.color.colorPrimaryDark);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(16, 16, 16, 16);
        }
        textView.setText(String.format("Season %s", getItem(position).getSeason()));
        return textView;
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getSeason();
    }
}
