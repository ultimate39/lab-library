package com.galt.library.core.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by Grishechko on 25.11.2015.
 */
public class Book {
    public static final String COLUMN_BOOK_NAME = "name_book";

    @DatabaseField(generatedId =  true, columnName = "book_id")
    private int id;

    @DatabaseField(columnName = COLUMN_BOOK_NAME)
    private String name;

    @DatabaseField(columnName = "release_date")
    private Date date;

    @DatabaseField(columnName = "number_pages")
    private Integer pageNumbers;

    @DatabaseField(columnName = "language_book")
    String language;

    @DatabaseField(columnName = "cost_id", foreign = true, foreignColumnName = "cost_id")
    private Cost cost;

    @DatabaseField(columnName = "book_type")
    String type = " ";

    @DatabaseField(columnName = "book_format")
    String format = " ";

    @DatabaseField(columnName = "book_tome")
    Integer booktome = 1;

    @DatabaseField(columnName = "book_numbers")
    Integer booknumbers;

    @DatabaseField(foreign = true, columnName = "author_id", foreignColumnName = "author_id")
    private Author author;

    @DatabaseField(foreign = true, columnName = "genre_id", foreignColumnName = "genre_id")
    private Genre genre;

    @DatabaseField(foreign = true, columnName = "publisher_id", foreignColumnName = "publisher_id")
    private Publisher publisher;

    @DatabaseField(foreign = true, columnName = "audiobooks_id", foreignColumnName = "audiobooks_id")
    private AudioBook audioBook;

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

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public AudioBook getAudioBook() {
        return audioBook;
    }

    public void setAudioBook(AudioBook audioBook) {
        this.audioBook = audioBook;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getBooktome() {
        return booktome;
    }

    public void setBooktome(Integer booktome) {
        this.booktome = booktome;
    }

    public Integer getBooknumbers() {
        return booknumbers;
    }

    public void setBooknumbers(Integer booknumbers) {
        this.booknumbers = booknumbers;
    }
}
