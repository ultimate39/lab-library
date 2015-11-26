package com.galt.library.core.model.fx;

import com.galt.library.core.model.Publisher;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class FxPublisher extends FxBase<Publisher> {
    private IntegerProperty id;
    private StringProperty name;;
    private StringProperty phoneNumber;

    FxPublisher(Publisher object) {
        super(object);
    }

    @Override
    void update(Publisher object) {
        id = new SimpleIntegerProperty(object.getId());
        name = new SimpleStringProperty(object.getNamePublisher());
        phoneNumber = new SimpleStringProperty(object.getPhoneNumberPublisher());
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

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}
