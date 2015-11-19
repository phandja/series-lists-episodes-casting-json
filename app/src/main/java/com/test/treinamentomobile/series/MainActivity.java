package com.test.treinamentomobile.series;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.test.treinamentomobile.series.fragment.TvShowListFragment;
import com.test.treinamentomobile.series.service.TvShowsIntentService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {

    private static final long ONE_HOUR = 60 * 60 * 1000;

    @FragmentById
    TvShowListFragment fragment;

    @Pref
    MyPrefs_ prefs;

    @OptionsMenuItem
    MenuItem menuSearch;

    @AfterViews
    public void init() {
        long lastUpdate = prefs.lastUpdate().getOr(0L);
        long oneHourAgo = System.currentTimeMillis() - ONE_HOUR;
        if (lastUpdate < oneHourAgo) {
            update();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean b = super.onCreateOptionsMenu(menu);

        SearchView v = (SearchView) menuSearch.getActionView();
        v.setOnQueryTextListener(fragment);

        return b;
    }

    @OptionsItem(R.id.menu_refresh)
    void update() {
        Toast.makeText(this, "Atualizando...", Toast.LENGTH_SHORT).show();
        TvShowsIntentService_.intent(this).fetchAndSaveData().start();
    }
}
