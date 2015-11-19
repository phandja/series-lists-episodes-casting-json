package com.test.treinamentomobile.series.service;

/**
 * Created by treinamentomobile on 11/19/15.
 */
import android.content.Intent;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.test.treinamentomobile.series.MyPrefs_;
import com.test.treinamentomobile.series.connection.RestConnection;
import com.test.treinamentomobile.series.database.DatabaseHelper;
import com.test.treinamentomobile.series.model.Cast;
import com.test.treinamentomobile.series.model.Episode;
import com.test.treinamentomobile.series.model.Person;
import com.test.treinamentomobile.series.model.PersonTvShow;
import com.test.treinamentomobile.series.model.Persona;
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
public class TvShowInfoIntentService extends AbstractIntentService {

    public static final String ACTION_SAVE_DONE = "ACTION_SHOW_LIST_SAVED";

    @RestService
    RestConnection connection;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<PersonTvShow, Long> daoPersonTvShow;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Episode, Long> daoEpisodes;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Person, Long> daoPerson;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Persona, Long> daoPersona;

    @Pref
    MyPrefs_ prefs;

    public TvShowInfoIntentService() {
        super("TvShowInfoIntentService");
    }

    @ServiceAction
    void fetchAndSaveData(final long tvShowId) {
        final List<Episode> tvShowEpisodes = connection.getTvShowEpisodes(tvShowId);
        final List<Cast> tvShowCast = connection.getTvShowCast(tvShowId);

        try {
            TransactionManager.callInTransaction(daoEpisodes.getConnectionSource(), new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    TvShow tvShow = daoTvShow.queryForId(tvShowId);
                    for (Episode episode : tvShowEpisodes) {
                        episode.setTvShow(tvShow);
                        daoEpisodes.createOrUpdate(episode);
                    }
                    for (Cast cast : tvShowCast) {
                        Person person = cast.getPerson();
                        daoPersona.createOrUpdate(person.getCharacter());
                        daoPerson.createOrUpdate(person);
                        PersonTvShow many2many = new PersonTvShow(person, tvShow);
                        daoPersonTvShow.createOrUpdate(many2many);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(ACTION_SAVE_DONE);
        sendBroadcast(intent);
        prefs.lastUpdate().put(System.currentTimeMillis());
    }
}
