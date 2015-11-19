package com.test.treinamentomobile.series.fragment;

/**
 * Created by treinamentomobile on 11/18/15.
 */
import android.support.v4.app.Fragment;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.test.treinamentomobile.series.R;
import com.test.treinamentomobile.series.adapter.EpisodesAdapter;
import com.test.treinamentomobile.series.database.DatabaseHelper;
import com.test.treinamentomobile.series.model.Episode;
import com.test.treinamentomobile.series.service.TvShowInfoIntentService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by hemobile on 17/11/15.
 */

@EFragment(R.layout.frag_list_episodes)
public class EpisodesListFragment extends Fragment {

    @FragmentArg
    long tvShowId;

    @Bean
    EpisodesAdapter adapter;

    @ViewById(android.R.id.list)
    StickyListHeadersListView listView;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Episode, Long> episodeDao;

    @AfterViews
    public void init() {
        listView.setDivider(null);
        fetchData();
    }

    @Receiver(actions = {TvShowInfoIntentService.ACTION_SAVE_DONE})
    void fetchData() {
        List<Episode> episodes = null;
        try {
            QueryBuilder<Episode, Long> queryBuilder = episodeDao.queryBuilder();
            episodes = episodeDao.query(queryBuilder
                    .where().eq(Episode.COLUMN_TVSHOW, tvShowId)
                    .prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter.setList(episodes);
        setListAdapter(adapter);
    }

    @UiThread
    public void setListAdapter(StickyListHeadersAdapter adapter) {
        listView.setAdapter(adapter);
    }
}
