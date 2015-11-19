package com.test.treinamentomobile.series.fragment;

/**
 * Created by treinamentomobile on 11/19/15.
 */
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.test.treinamentomobile.series.TvShowActivity_;
import com.test.treinamentomobile.series.adapter.TvShowsAdapter;
import com.test.treinamentomobile.series.database.DatabaseHelper;
import com.test.treinamentomobile.series.model.TvShow;
import com.test.treinamentomobile.series.service.TvShowsIntentService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.Receiver;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hemobile on 18/11/15.
 */

@EFragment
public class TvShowListFragment extends ListFragment implements SearchView.OnQueryTextListener {

    @Bean
    TvShowsAdapter adapter;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    @AfterViews
    void init() {
        fetchData();
    }

    @Receiver(actions = {TvShowsIntentService.ACTION_SHOW_LIST_SAVED})
    void fetchData() {
        List<TvShow> tvShowList = null;
        try {
            tvShowList = daoTvShow.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter.setList(tvShowList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Long tvShowId = adapter.getItem(position).getId();
        TvShowActivity_.intent(this).tvShowId(tvShowId).start();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}