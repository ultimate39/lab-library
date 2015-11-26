package com.galt.library.core.model.fx;

import com.galt.library.core.model.Genre;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class FxGenre extends FxBase<Genre>{
    IntegerProperty id;
    StringProperty name;
    IntegerProperty ageRestriction;

    public FxGenre(Genre object) {
        super(object);
    }

    @Override
    void update(Genre object) {
        id = new SimpleIntegerProperty(object.getId());
        name = new SimpleStringProperty(object.getName());
        ageRestriction = new SimpleIntegerProperty(object.getAgeRestriction());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAgeRestriction() {
        return ageRestriction.get();
    }

    public IntegerProperty ageRestrictionProperty() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction.set(ageRestriction);
    }
}
