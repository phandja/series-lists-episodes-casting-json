package com.test.treinamentomobile.series.connection;

import com.test.treinamentomobile.series.model.Cast;
import com.test.treinamentomobile.series.model.Episode;
import com.test.treinamentomobile.series.model.TvShow;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

/**
 * Created by treinamentomobile on 11/18/15.
 */
@Rest(rootUrl = "http://api.tvmaze.com", converters = GsonHttpMessageConverter.class)
public interface RestConnection {

    @Get("/shows")
    List<TvShow> getTvShowList();

    @Get("/shows/{id}")
    TvShow getTvShowInfo(long id);

    @Get("/shows/{id}/episodes")
    List<Episode> getTvShowEpisodes(long id);

    @Get("/shows/{id}/cast")
    List<Cast> getTvShowCast(long id);
}