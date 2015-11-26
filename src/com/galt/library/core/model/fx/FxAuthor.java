package com.galt.library.core.model.fx;

import com.galt.library.core.model.Author;
import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class FxAuthor extends FxBase<Author> {
   /* @DatabaseField(generatedId = true, columnName = "id_author")
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
    private Date death;*/

    private IntegerProperty id;
    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty middleName;
    private ObjectProperty<Date> birthday;
    private ObjectProperty<Date> death;
    private StringProperty fullName;

    FxAuthor(Author object) {
        super(object);
    }


    @Override
    void update(Author object) {
        id = new SimpleIntegerProperty(object.getId());
        lastName = new SimpleStringProperty(object.getLastName());
        firstName = new SimpleStringProperty(object.getFirstName());
        middleName = new SimpleStringProperty(object.getMiddleName());
        birthday = new SimpleObjectProperty<>(object.getBirthday());
        death = new SimpleObjectProperty<>(object.getDeath());
        fullName = new SimpleStringProperty(String.format("%s.%s %s", object.getFirstName().substring(0, 1), object.getMiddleName().substring(0, 1), object.getLastName()));
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

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public Date getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<Date> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday.set(birthday);
    }

    public Date getDeath() {
        return death.get();
    }

    public ObjectProperty<Date> deathProperty() {
        return death;
    }

    public void setDeath(Date death) {
        this.death.set(death);
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
}
