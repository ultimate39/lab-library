package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Grishechko on 11.12.2015.
 */
@DatabaseTable(tableName = "audiobooks")
public class AudioBook {
    @DatabaseField(columnName = "audiobooks_id", generatedId = true)
    Integer id;

    @DatabaseField(columnName = "name_audiobooks")
    String name;

    @DatabaseField(columnName = "length")
    Integer length;

    @DatabaseField(columnName = "bitrate")
    Integer bitrate;

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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    @Override
    public String toString() {
        return name;
    }
}
