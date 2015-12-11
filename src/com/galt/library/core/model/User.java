package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

/**
 * Created by Grishechko on 27.11.2015.
 */
public class User {
    @DatabaseField(generatedId = true, columnName = "user_id")
    Integer id;

    @DatabaseField(columnName = "last_name_user")
    String lastname;

    @DatabaseField(columnName = "name_user")
    String firstname;

    @DatabaseField(columnName = "middle_name_user")
    String secondname;

    @DatabaseField(columnName = "phone_number_user")
    String phonenumber;

    @DatabaseField(columnName = "city_user")
    String city;

    @DatabaseField(columnName = "country_user")
    String country;

    @DatabaseField(columnName = "email_user")
    String email;

    @DatabaseField(columnName = "book_id")
    Integer bookId = 1;

    @DatabaseField(columnName = "register_date_user")
    String registerDate = "2015-12-11";

    @DatabaseField(columnName = "site_admin_id")
    Integer siteAdminId = 1;

    @DatabaseField(columnName = "history_pay_id")
    Integer historyPayId = 1;

    @DatabaseField(columnName = "nickname_user")
    String nickname;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getSiteAdminId() {
        return siteAdminId;
    }

    public void setSiteAdminId(Integer siteAdminId) {
        this.siteAdminId = siteAdminId;
    }

    public Integer getHistoryPayId() {
        return historyPayId;
    }

    public void setHistoryPayId(Integer historyPayId) {
        this.historyPayId = historyPayId;
    }

    @Override
    public String toString() {
        return String.format("%s.%s %s", firstname.substring(0, 1), secondname.substring(0, 1), lastname);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
