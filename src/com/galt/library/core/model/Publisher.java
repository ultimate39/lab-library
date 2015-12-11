package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class Publisher {

    @DatabaseField(generatedId = true, columnName = "publisher_id")
    private Integer id;

    @DatabaseField(columnName = "name_publisher")
    private String namePublisher;

    @DatabaseField(columnName = "phone_number_publisher")
    private String phoneNumberPublisher;

    @DatabaseField(columnName = "address_id")
    Integer addressId = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public void setNamePublisher(String namePublisher) {
        this.namePublisher = namePublisher;
    }

    public String getPhoneNumberPublisher() {
        return phoneNumberPublisher;
    }

    public void setPhoneNumberPublisher(String phoneNumberPublisher) {
        this.phoneNumberPublisher = phoneNumberPublisher;
    }

    @Override
    public String toString() {
        return namePublisher;
    }
}
