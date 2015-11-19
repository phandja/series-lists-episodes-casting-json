package com.test.treinamentomobile.series.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.test.treinamentomobile.series.R;
import com.test.treinamentomobile.series.model.Person;
import com.test.treinamentomobile.series.model.Persona;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hemobile on 17/11/15.
 */
@EViewGroup(R.layout.item_person)
public class PersonItemView extends FrameLayout {

    @ViewById
    TextView textPersona, textActor;

    public PersonItemView(Context context) {
        super(context);
    }

    public PersonItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Person item) {
        textActor.setText(item.getName());
        Persona character = item.getCharacter();
        textPersona.setText(character.getName());
    }
}
