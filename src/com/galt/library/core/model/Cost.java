package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Grishechko on 11.12.2015.
 */
public class Cost {

    @DatabaseField(columnName = "cost_id", generatedId = true)
    Integer id;

    @DatabaseField(columnName = "cost_size")
    Integer size;

    @DatabaseField(columnName = "discount")
    Integer discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
