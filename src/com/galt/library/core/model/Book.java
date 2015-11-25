package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by Grishechko on 25.11.2015.
 */
public class Book {

    @DatabaseField(generatedId =  true, columnName = "id_book")
    private int id;

    @DatabaseField(columnName = "book_name")
    private String name;

    @DatabaseField(columnName = "year_publish_book")
    private Date date;

    @DatabaseField(columnName = "page_numbers_book")
    private Integer pageNumbers;

    @DatabaseField(columnName = "state_book")
    private String state;

    @DatabaseField(columnName = "size_book")
    private String size;

    @DatabaseField(columnName = "weight_book")
    private String weight;

    @DatabaseField(columnName = "cost_book")
    private String cost;

    @DatabaseField(foreign = true, columnName = "id_author", foreignColumnName = "id_author")
    private Author author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(Integer pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", pageNumbers=" + pageNumbers +
                ", state='" + state + '\'' +
                ", size='" + size + '\'' +
                ", weight='" + weight + '\'' +
                ", cost='" + cost + '\'' +
                ", author=" + author +
                '}';
    }
}
