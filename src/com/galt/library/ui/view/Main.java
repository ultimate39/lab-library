package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.Author;
import com.galt.library.core.model.Book;
import com.galt.library.core.model.Genre;
import com.galt.library.core.model.fx.FxBook;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Grishechko on 25.11.2015.
 */
public class Main {
    //TableBook
    @FXML TableView<FxBook> tableBooks;
    @FXML TableColumn<FxBook, String> columnBookName;
    @FXML TableColumn<FxBook, String> columnBookGenre;
    @FXML TableColumn<FxBook, String> columnBookAuthor;
    @FXML TableColumn<FxBook, String> columnBookPublisher;
    @FXML TableColumn<FxBook, String> columnBookYear;
    @FXML TableColumn<FxBook, String> columnBookState;

    @FXML TableView tableReaders;
    @FXML TableView tableIssuedBooks;
    @FXML Button btnAddBook;
    @FXML Button btnDeleteBook;
    @FXML TextField tfBookName;
    @FXML TextField tfAuthor;
    @FXML TextField tfGenre;
    @FXML TextField tfState;
    //Filter books

    ObservableList<FxBook> fxBooks;

    private App app;
    private DatabaseHelper helper;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        helper = DatabaseHelper.getInstance();
        initializeTableBooks();
    }

    private void initializeTableBooks() {
        fxBooks = FXCollections.observableArrayList();
        convertBooksToFxBooksAndSet(helper.getBooks());
        tableBooks.setItems(fxBooks);
        columnBookName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnBookGenre.setCellValueFactory(cellData -> cellData.getValue().getGenre().nameProperty());
        columnBookAuthor.setCellValueFactory(cellData -> cellData.getValue().getAuthor().fullNameProperty());
        columnBookPublisher.setCellValueFactory(cellData -> cellData.getValue().getPublisher().nameProperty());
        columnBookYear.setCellValueFactory(cellData -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return new SimpleStringProperty(format.format(cellData.getValue().getDate()));
        });
        columnBookState.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        tableBooks.setRowFactory(tv -> {
            TableRow<FxBook> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    FxBook book = row.getItem();
                    app.showBookDialog(book.getBook());
                }
            });
            return row;
        });
    }

    private void convertBooksToFxBooksAndSet(List<Book> books) {
        List<Book> booksModels = books;
        fxBooks.clear();
        for (Book book : booksModels) {
            fxBooks.add(new FxBook(book));
        }
    }

    @FXML
    public void findBooks() throws SQLException {
        String nameLike = "%" + tfBookName.getText() + "%";
        String state = "%" + tfState.getText() + "%";
        String author = "%" + tfAuthor.getText() + "%";
        String genre =  "%" + tfGenre.getText() + "%";

        QueryBuilder<Genre, Integer> genreQb = helper.getGenresHelper().queryBuilder();
        genreQb.where().like("name_genre", genre);

        QueryBuilder<Author, Integer> authorQb = helper.getAuthorsHelper().queryBuilder();
        authorQb.where().like("last_name_author", author).or().like("middle_name_author", author).or().like("name_author", author);

        QueryBuilder<Book, Integer> queryBuilder = helper.getBooksHelper().queryBuilder();
        queryBuilder.join(genreQb).join(authorQb).where().like("state_book", state).and().like("book_name", nameLike);

        convertBooksToFxBooksAndSet(helper.getBooksHelper().query(queryBuilder.prepare()));
        tableBooks.setItems(fxBooks);
    }

}
