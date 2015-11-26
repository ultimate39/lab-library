package com.galt.library.core.db;

import com.galt.library.core.model.Author;
import com.galt.library.core.model.Book;
import com.galt.library.core.model.Genre;
import com.galt.library.core.model.Publisher;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class DatabaseHelper {
    private final String URL = "jdbc:mysql://localhost:3306/lab?user=root&password=root";
    private Dao<Book, Integer> books;
    private Dao<Author, Integer> authors;
    private Dao<Genre, Integer> genres;
    private Dao<Publisher, Integer> publishers;
    private static DatabaseHelper instance;

    private DatabaseHelper() {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:mysql://localhost:3306/lab?user=root&password=root");
            books = DaoManager.createDao(connectionSource, Book.class);
            authors = DaoManager.createDao(connectionSource, Author.class);
            genres = DaoManager.createDao(connectionSource, Genre.class);
            publishers = DaoManager.createDao(connectionSource, Publisher.class);
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

}
