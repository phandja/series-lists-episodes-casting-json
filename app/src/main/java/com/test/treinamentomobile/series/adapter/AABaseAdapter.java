package com.test.treinamentomobile.series.adapter;

/**
 * Created by treinamentomobile on 11/18/15.
 */
import android.content.Context;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemobile on 14/11/15.
 */
@EBean
public abstract class AABaseAdapter<T> extends BaseAdapter {

    @RootContext
    Context context;

    private List<T> list;

    @Override
    public int getCount() {
        return getList().size();
    }

    @Override
    public T getItem(int i) {
        return getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public List<T> getList() {
        if (list == null) {
            list = new ArrayList<T>();
        }
        return list;
    }

    public void setList(List<T> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    @Override
    @UiThread
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
