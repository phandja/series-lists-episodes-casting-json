package com.test.treinamentomobile.series.service;

import android.content.Intent;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.test.treinamentomobile.series.MyPrefs_;
import com.test.treinamentomobile.series.connection.RestConnection;
import com.test.treinamentomobile.series.database.DatabaseHelper;
import com.test.treinamentomobile.series.model.TvShow;

import org.androidannotations.annotations.EIntentService;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ServiceAction;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.api.support.app.AbstractIntentService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by hemobile on 18/11/15.
 */
@EIntentService
public class TvShowsIntentService extends AbstractIntentService {

    public static final String ACTION_SHOW_LIST_SAVED = "ACTION_SHOW_LIST_SAVED";

    @RestService
    RestConnection connection;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    @Pref
    MyPrefs_ prefs;

    public TvShowsIntentService() {
        super("TvShowsIntentService");
    }

    @ServiceAction
    void fetchAndSaveData() {
        final List<TvShow> tvShowInfo = connection.getTvShowList();
        try {
            TransactionManager.callInTransaction(daoTvShow.getConnectionSource(),
                    new Callable<Object>() {
                        @Override
                        public Object call() throws Exception {
                            for (TvShow tvShow : tvShowInfo) {
                                daoTvShow.createOrUpdate(tvShow);
                            }
                            return null;
                        }
                    }
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(ACTION_SHOW_LIST_SAVED);
        sendBroadcast(intent);

        prefs.lastUpdate().put(System.currentTimeMillis());
    }

}
