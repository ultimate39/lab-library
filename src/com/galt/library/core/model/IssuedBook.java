package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Grishechko on 27.11.2015.
 */
@DatabaseTable(tableName = "issued_book")
public class IssuedBook {
    @DatabaseField(generatedId = true, columnName = "id_issued_book")
    Integer id;

    @DatabaseField(columnName = "data_issue_book")
    Date dateIssueBook;

    @DatabaseField(columnName = "period_issue_book")
    Date periodIssueBook;

    @DatabaseField(columnName = "data_return_book")
    Date dateReturnBook;

    @DatabaseField(columnName = "id_reader", foreign = true, foreignColumnName = "id_reader")
    User user;

    @DatabaseField(columnName = "id_book", foreignColumnName = "id_book", foreign = true)
    Book book;

    public Date getDateIssueBook() {
        return dateIssueBook;
    }

    public void setDateIssueBook(Date dateIssueBook) {
        this.dateIssueBook = dateIssueBook;
    }

    public Date getPeriodIssueBook() {
        return periodIssueBook;
    }

    public void setPeriodIssueBook(Date periodIssueBook) {
        this.periodIssueBook = periodIssueBook;
    }

    public Date getDateReturnBook() {
        return dateReturnBook;
    }

    public void setDateReturnBook(Date dateReturnBook) {
        this.dateReturnBook = dateReturnBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
