package com.test.treinamentomobile.series.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.treinamentomobile.series.R;
import com.test.treinamentomobile.series.model.Episode;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by treinamentomobile on 11/18/15.
 */
@EViewGroup(R.layout.item_episode)
public class EpisodeItemView extends FrameLayout {

    @ViewById
    ImageView image;

    @ViewById
    TextView textName, textEpisode, textSummary;

    public EpisodeItemView(Context context) {
        super(context);
    }

    public EpisodeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Episode item) {
        textName.setText(item.getName());
        textEpisode.setText(String.format("S%02dE%02d", item.getSeason(), item.getNumber()));
        textSummary.setText(Html.fromHtml(item.getSummary()));
        try {
            Picasso.with(getContext())
                    .load(item.getImage().getMedium())
                    .placeholder(R.drawable.placeholder)
                    .into(image);
        } catch (NullPointerException e) {
            image.setImageResource(R.drawable.placeholder);
        }
    }
}
