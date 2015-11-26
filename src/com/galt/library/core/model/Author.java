package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by Grishechko on 25.11.2015.
 */
public class Author {

    @DatabaseField(generatedId = true, columnName = "id_author")
    private Integer id;

    @DatabaseField(columnName = "last_name_author")
    private String lastName;

    @DatabaseField(columnName = "name_author")
    private String firstName;

    @DatabaseField(columnName = "middle_name_author")
    private String middleName;

    @DatabaseField(columnName = "year_birth_author")
    private Date birthday;

    @DatabaseField(columnName = "year_death_author")
    private Date death;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    @Override
    public String toString() {
        return String.format("%s.%s %s", firstName.substring(0, 1), middleName.substring(0, 1), lastName);
    }
}
