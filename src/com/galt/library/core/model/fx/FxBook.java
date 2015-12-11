package com.galt.library.core.model.fx;

import com.galt.library.core.model.Author;
import com.galt.library.core.model.Book;
import com.j256.ormlite.field.DatabaseField;
import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class FxBook extends FxBase<Book> {

    private Book book;
    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<FxGenre> genre;
    private ObjectProperty<Date> date;
    private IntegerProperty pageNumbers;
    private ObjectProperty<FxAuthor> author;
    private ObjectProperty<FxPublisher> publisher;

    public FxBook(Book object) {
        super(object);
    }

    @Override
    void update(Book object) {
        book = object;
        id = new SimpleIntegerProperty(object.getId());
        name = new SimpleStringProperty(object.getName());
        date = new SimpleObjectProperty<>(object.getDate());
        pageNumbers = new SimpleIntegerProperty(object.getPageNumbers());
        author = new SimpleObjectProperty<>(new FxAuthor(object.getAuthor()));
        genre = new SimpleObjectProperty<>(new FxGenre(object.getGenre()));
        publisher = new SimpleObjectProperty<>(new FxPublisher(object.getPublisher()));
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

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public int getPageNumbers() {
        return pageNumbers.get();
    }

    public IntegerProperty pageNumbersProperty() {
        return pageNumbers;
    }

    public void setPageNumbers(int pageNumbers) {
        this.pageNumbers.set(pageNumbers);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public FxGenre getGenre() {
        return genre.get();
    }

    public ObjectProperty<FxGenre> genreProperty() {
        return genre;
    }

    public void setGenre(FxGenre genre) {
        this.genre.set(genre);
    }

    public FxAuthor getAuthor() {
        return author.get();
    }

    public ObjectProperty<FxAuthor> authorProperty() {
        return author;
    }

    public void setAuthor(FxAuthor author) {
        this.author.set(author);
    }

    public FxPublisher getPublisher() {
        return publisher.get();
    }

    public ObjectProperty<FxPublisher> publisherProperty() {
        return publisher;
    }

    public void setPublisher(FxPublisher publisher) {
        this.publisher.set(publisher);
    }
}
