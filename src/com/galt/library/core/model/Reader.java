package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

/**
 * Created by Grishechko on 27.11.2015.
 */
public class Reader {
    @DatabaseField(generatedId = true, columnName = "id_reader")
    Integer id;

    @DatabaseField (columnName = "last_name_reader")
    String lastname;

    @DatabaseField (columnName = "name_reader")
    String firstname;

    @DatabaseField (columnName = "middle_name_reader")
    String secondname;

    @DatabaseField (columnName = "phone_number_reader")
    String phonenumber;

    List<IssuedBook> issuedBooks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<IssuedBook> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<IssuedBook> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    @Override
    public String toString() {
        return String.format("%s.%s %s", firstname.substring(0, 1), secondname.substring(0, 1), lastname);
    }
}
