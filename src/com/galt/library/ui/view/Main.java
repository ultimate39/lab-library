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
    @FXML TableView<User> tableReaders;
    @FXML TableColumn<User, String> columnReaderFirstame;
    @FXML TableColumn<User, String> columnReaderLastname;
    @FXML TableColumn<User, String> columnReaderSecondName;
    @FXML TableColumn<User, String> columnReaderPhone;
    @FXML TableColumn<User, String> columnReaderCountbooks;
    @FXML TableColumn<User, String> columnReaderNickname;
    ObservableList<User> users;
    @FXML Button btnAddReader;
    @FXML Button btnDeleteReader;
    //Filter books
    @FXML TableView<AudioBook> tableAudiobook;
    @FXML TableColumn<AudioBook, String> columnAudiobookName;
    @FXML TableColumn<AudioBook, String> columnAudiobookBitrate;
    @FXML TableColumn<AudioBook, String> columnAudiobookLength;
    @FXML Button btnAddAudiobook;
    @FXML Button btnDeleteAudiobook;
    ObservableList<AudioBook> audioBooks;


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
        initializeTableAudibooks();
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
        tableBooks.setRowFactory(tv -> {
            TableRow<FxBook> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) && app.isAdminRights()) {
                    FxBook book = row.getItem();
                    if (app.showBookDialog(book.getBook())) {
                        findBooks();
                    }
                }
            });
            return row;
        });
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

    private void initializeTableAudibooks() {
        audioBooks = FXCollections.observableArrayList();
        updateTableAudiobooks();
        tableAudiobook.setItems(audioBooks);
        columnAudiobookBitrate.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getBitrate())));
        columnAudiobookLength.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLength())));
        columnAudiobookName.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getName())));
        tableAudiobook.setRowFactory(tv -> {
            TableRow<AudioBook> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) && app.isAdminRights()) {
                    AudioBook audiobook= row.getItem();
                    if (app.showAudiobook(audiobook)) {
                        updateTableAudiobooks();
                    }
                }
            });
            return row;
        });
    }

    private void initializeTableReaders() {
        users = FXCollections.observableArrayList();
        updateTableReaders();
        tableReaders.setItems(users);
        columnReaderFirstame.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        columnReaderLastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        columnReaderSecondName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondname()));
        columnReaderPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhonenumber()));
        columnReaderNickname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNickname()));
        tableReaders.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) && app.isAdminRights()) {
                    User user = row.getItem();
                    if (app.showReaderDialog(user)) {
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

    @FXML
    public void onDeleteAudiobookClick() {
        try {
            if(helper.getAudioBook().delete(tableAudiobook.getSelectionModel().getSelectedItem()) > 0) {
                updateTableAudiobooks();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onAddAudioBook() {
        if(app.showAudiobook(null)) {
            updateTableAudiobooks();
        }
    }

    private void updateTableReaders() {
        users.clear();
        try {
            users.addAll(helper.getReaders().queryForAll());
            tableReaders.setItems(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTableAudiobooks() {
        audioBooks.clear();
        try {
            audioBooks.addAll(helper.getAudioBook().queryForAll());
            tableAudiobook.setItems(audioBooks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void findBooks() {
        String nameLike = "%" + tfBookName.getText() + "%";
        String author = "%" + tfAuthor.getText() + "%";
        String genre = "%" + tfGenre.getText() + "%";

        QueryBuilder<Genre, Integer> genreQb = helper.getGenresHelper().queryBuilder();
        try {
            genreQb.where().like("name_genre", genre);
            QueryBuilder<Author, Integer> authorQb = helper.getAuthorsHelper().queryBuilder();
            authorQb.where().like("last_name_author", author).or().like("middle_name_author", author).or().like("name_author", author);

            QueryBuilder<Book, Integer> queryBuilder = helper.getBooksHelper().queryBuilder();
            queryBuilder.join(genreQb).join(authorQb).where().like("name_book", nameLike);

            convertBooksToFxBooksAndSet(helper.getBooksHelper().query(queryBuilder.prepare()));
            tableBooks.setItems(fxBooks);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!app.isAdminRights()) {
            btnAddAudiobook.setDisable(true);
            btnAddBook.setDisable(true);
            btnAddReader.setDisable(true);
            btnDeleteBook.setDisable(true);
            btnDeleteReader.setDisable(true);
            btnDeleteAudiobook.setDisable(true);
        }
    }

}
