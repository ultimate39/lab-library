package com.galt.library.core.db;

import com.galt.library.core.model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class DatabaseHelper {
    private final String URL = "jdbc:mysql://localhost:3306/music_internet_library?user=root&password=root";
    private Dao<Book, Integer> books;
    private Dao<Author, Integer> authors;
    private Dao<Genre, Integer> genres;
    private Dao<Publisher, Integer> publishers;
    private Dao<User, Integer> readers;
    private Dao<AudioBook, Integer> audioBook;
    private Dao<Cost, Integer> costs;

    private static DatabaseHelper instance;

    private DatabaseHelper() {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(URL);
            books = DaoManager.createDao(connectionSource, Book.class);
            authors = DaoManager.createDao(connectionSource, Author.class);
            genres = DaoManager.createDao(connectionSource, Genre.class);
            publishers = DaoManager.createDao(connectionSource, Publisher.class);
            readers = DaoManager.createDao(connectionSource, User.class);
            audioBook = DaoManager.createDao(connectionSource, AudioBook.class);
            costs = DaoManager.createDao(connectionSource, Cost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseHelper getInstance() {
        if(instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }


    public List<Book> getBooks(){
        try {
            return books.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBook(Book book) {
        try {
            books.update(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        try {
            books.createIfNotExists(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteBook(Book book) {
        try {
            return books.delete(book) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Author> getAuthors() {
        try {
           return authors.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAuthor(Author author) {
        try {
            authors.update(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAuthor(Author author) {
        try {
            authors.createIfNotExists(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAuthor(Author author) {
        try {
            return authors.delete(author) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Genre> getGenres() {
        try {
            return genres.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Publisher> getPublishers() {
        try {
            return publishers.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Dao<User, Integer> getReaders() {
        return readers;
    }

    public Dao<Book, Integer> getBooksHelper() {
        return books;
    }

    public Dao<Genre, Integer> getGenresHelper() {
        return genres;
    }

    public Dao<Author, Integer> getAuthorsHelper() {
        return authors;
    }

    public Dao<Publisher, Integer> getPublishersHelper() {
        return publishers;
    }

    public Dao<AudioBook, Integer> getAudioBook() {
        return audioBook;
    }

    public void setAudioBook(Dao<AudioBook, Integer> audioBook) {
        this.audioBook = audioBook;
    }

    public Dao<Cost, Integer> getCosts() {
        return costs;
    }

    public void setCosts(Dao<Cost, Integer> costs) {
        this.costs = costs;
    }
}
