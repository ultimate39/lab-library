package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class Genre {
    @DatabaseField(generatedId = true, columnName = "id_genre")
    Integer id;

    @DatabaseField(columnName = "name_genre")
    String name;

    @DatabaseField(columnName = "age_restriction")
    int ageRestriction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @Override
    public String toString() {
        return name;
    }
}
