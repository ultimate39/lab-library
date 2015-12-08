package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.*;
import com.galt.library.core.model.fx.FxBook;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseButton;

import javax.swing.table.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Grishechko on 25.11.2015.
 */
public class Main {
    //Book
    @FXML TableView<FxBook> tableBooks;
    @FXML TableColumn<FxBook, String> columnBookName;
    @FXML TableColumn<FxBook, String> columnBookGenre;
    @FXML TableColumn<FxBook, String> columnBookAuthor;
    @FXML TableColumn<FxBook, String> columnBookPublisher;
    @FXML TableColumn<FxBook, String> columnBookYear;
    @FXML TableColumn<FxBook, String> columnBookState;
    ObservableList<FxBook> fxBooks;
    @FXML Button btnAddBook;
    @FXML Button btnDeleteBook;
    @FXML TextField tfBookName;
    @FXML TextField tfAuthor;
    @FXML TextField tfGenre;
    @FXML TextField tfState;
    //Readers
    @FXML TableView<Reader> tableReaders;
    @FXML TableColumn<Reader, String> columnReaderFirstame;
    @FXML TableColumn<Reader, String> columnReaderLastname;
    @FXML TableColumn<Reader, String> columnReaderSecondName;
    @FXML TableColumn<Reader, String> columnReaderPhone;
    @FXML TableColumn<Reader, String> columnReaderCountbooks;
    ObservableList<Reader> readers;
    @FXML Button btnAddReader;
    @FXML Button btnDeleteReader;
    //IssuedBooks
    @FXML TableView<IssuedBook> tableIssuedBooks;
    @FXML TableColumn<IssuedBook, String> columnIssueBookName;
    @FXML TableColumn<IssuedBook, String> columnIssueBookStatus;
    @FXML TableColumn<IssuedBook, String> columnIssueBookUser;
    ObservableList<IssuedBook> issuedBooks;
    //Filter books


    private App app;
    private DatabaseHelper helper;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        helper = DatabaseHelper.getInstance();
        initializeTableBooks();
        initializeTableReaders();
        initializeTableIssuedBooks();
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
                    if (app.showBookDialog(book.getBook())) {
                        findBooks();
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    showIssueBookContextMenu(tv, event.getScreenX(), event.getScreenY(), row.getItem().getBook());
                }
            });
            return row;
        });
    }

    private void initializeTableIssuedBooks() {
        issuedBooks = FXCollections.observableArrayList();
        updateTableIssuedBook();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        columnIssueBookName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBook().getName()));
        columnIssueBookUser.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReader().toString()));
        columnIssueBookStatus.setCellValueFactory(cellData -> new SimpleStringProperty(formatter.format(cellData.getValue().getDateReturnBook())));
    }

    private void showIssueBookContextMenu(Node node, double x, double y, Book book) {
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem issueBook = new MenuItem("Выдать книгу");
        contextMenu.getItems().add(issueBook);
        issueBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (app.showIssueBook(book)) {
                    updateTableReaders();
                }
            }
        });
        contextMenu.show(node, x, y);
    }

   /* private void showIssueBookContextMenu(Node node, double x, double y, IssuedBook book) {
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem issueBook = new MenuItem("Возврат книги");
        contextMenu.getItems().add(issueBook);
        issueBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(app.showReturnBookDialog(book)) {
                    updateTableIssuedBook();
                }
            }
        });
        contextMenu.show(node, x, y);
    }*/

    private void initializeTableReaders() {
        readers = FXCollections.observableArrayList();
        updateTableReaders();
        tableReaders.setItems(readers);
        columnReaderFirstame.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        columnReaderLastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        columnReaderSecondName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondname()));
        columnReaderPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhonenumber()));
        columnReaderCountbooks.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIssuedBooks().size())));
        tableReaders.setRowFactory(tv -> {
            TableRow<Reader> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Reader reader = row.getItem();
                    if (app.showReaderDialog(reader)) {
                        updateTableReaders();
                    }
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
    public void onAddBookBtnClick() {
        if (app.showBookDialog(null)) {
            findBooks();
        }
    }

    @FXML
    public void onDeleteBtnClick() {
        if(helper.deleteBook(tableBooks.getSelectionModel().getSelectedItem().getBook())) {
            findBooks();
        }
    }

    @FXML
    public void onAddReaderClick() {
        if(app.showReaderDialog(null)) {
            updateTableReaders();
        }
    }

    @FXML
    public void onDeleteReaderClick() {
        try {
            if(helper.getReaders().delete(tableReaders.getSelectionModel().getSelectedItem()) > 0) {
                updateTableReaders();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTableReaders() {
        readers.clear();
        try {
            readers.addAll(helper.getReaders().queryForAll());
            for(Reader reader : readers) {
                PreparedQuery<IssuedBook> query = helper.getIssuedBooks().queryBuilder().where().eq("id_reader", reader.getId()).prepare();
                reader.setIssuedBooks(helper.getIssuedBooks().query(query));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTableIssuedBook() {
        issuedBooks.clear();
        try {
            issuedBooks.addAll(helper.getIssuedBooks().queryForAll());
            for(IssuedBook issuedBook : issuedBooks) {
                tableIssuedBooks.setItems(issuedBooks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void findBooks() {
        String nameLike = "%" + tfBookName.getText() + "%";
        String state = "%" + tfState.getText() + "%";
        String author = "%" + tfAuthor.getText() + "%";
        String genre = "%" + tfGenre.getText() + "%";

        QueryBuilder<Genre, Integer> genreQb = helper.getGenresHelper().queryBuilder();
        try {
            genreQb.where().like("name_genre", genre);
            QueryBuilder<Author, Integer> authorQb = helper.getAuthorsHelper().queryBuilder();
            authorQb.where().like("last_name_author", author).or().like("middle_name_author", author).or().like("name_author", author);

            QueryBuilder<Book, Integer> queryBuilder = helper.getBooksHelper().queryBuilder();
            queryBuilder.join(genreQb).join(authorQb).where().like("state_book", state).and().like("book_name", nameLike);

            convertBooksToFxBooksAndSet(helper.getBooksHelper().query(queryBuilder.prepare()));
            tableBooks.setItems(fxBooks);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
