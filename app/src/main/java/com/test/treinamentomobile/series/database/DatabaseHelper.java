package com.test.treinamentomobile.series.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.test.treinamentomobile.series.model.Episode;
import com.test.treinamentomobile.series.model.Person;
import com.test.treinamentomobile.series.model.PersonTvShow;
import com.test.treinamentomobile.series.model.Persona;
import com.test.treinamentomobile.series.model.TvShow;

import java.sql.SQLException;

/**
 * Created by hemobile on 17/11/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private Class[] classes = {TvShow.class, Episode.class,
            Persona.class, Person.class,
            PersonTvShow.class
    };

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class c : classes) {
                TableUtils.createTable(connectionSource, c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            for (int i = classes.length - 1; i >= 0; i--) {
                TableUtils.dropTable(connectionSource, classes[i], true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }
}
