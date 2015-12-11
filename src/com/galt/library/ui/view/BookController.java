package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class BookController {
    @FXML TextField tfName;
    @FXML TextField tfYear;
    @FXML TextField tfPageNumbers;
    @FXML TextField tfLanguage;
    @FXML TextField tfBooktype;
    @FXML TextField tfCost;
    @FXML TextField tfDisountCost;
    @FXML TextField tfBooknumbers;


    @FXML ChoiceBox<Author> cbfAuthor;
    @FXML ChoiceBox<Genre> cbGenre;
    @FXML ChoiceBox<Publisher> cbPublisher;
    @FXML ChoiceBox<AudioBook> cbAudiobooks;

    @FXML Button btnAddAuthor;
    @FXML Button btnAddGenre;
    @FXML Button btnAddPublisher;
    @FXML Button btnBook;
    @FXML Button btnCancel;

    @FXML GridPane gpAudiobook;


    private Stage dialogStage;
    private Book book;
    private boolean isEdit;
    private DatabaseHelper helper;
    private boolean isOkClicked;
    private App app;

    public void setBook(Book book) {
        this.book = book;
        isEdit = book != null;
        //Genre
        updateChoiceBoxGenres();
        //Publisher
        updateChoiceBoxPublishers();
        //Authors
        updateChoiceBoxAuthors();
        //Audiobooks
        updateChoiceBoxAudiobooks();
        if(book != null){
            tfName.setText(book.getName());
            //Year
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            tfYear.setText(formatter.format(book.getDate()));
            tfPageNumbers.setText(book.getPageNumbers().toString());
            tfLanguage.setText(book.getLanguage());
            tfBooktype.setText(book.getType());
            tfCost.setText(String.valueOf(book.getCost().getSize()));
            tfDisountCost.setText(String.valueOf(book.getCost().getDiscount()));
            tfBooknumbers.setText(String.valueOf(book.getBooknumbers()));
            btnBook.setText("Редактировать");
        }
    }

    private void updateChoiceBoxAuthors() {
        ObservableList<Author> authors = FXCollections.observableArrayList();
        authors.addAll(helper.getAuthors());
        cbfAuthor.setItems(authors);
        if(book != null && book.getAuthor() != null) {
            int selectPos = 0;
            for(Author author : authors) {
                if(author.getId().equals(book.getAuthor().getId())) {
                    cbfAuthor.getSelectionModel().select(selectPos);
                    break;
                }
                selectPos++;
            }
        }
    }

    private void updateChoiceBoxGenres() {
        ObservableList<Genre> genres = FXCollections.observableArrayList();
        genres.addAll(helper.getGenres());
        cbGenre.setItems(genres);
        if(book != null && book.getGenre() != null) {
            int selectPos = 0;
            for (Genre genre : genres) {
                if (genre.getId().equals(book.getGenre().getId())) {
                    cbGenre.getSelectionModel().select(selectPos);
                    break;
                }
                selectPos++;
            }
        }
    }

    private void updateChoiceBoxPublishers() {
        ObservableList<Publisher> publishers = FXCollections.observableArrayList();
        publishers.addAll(helper.getPublishers());
        cbPublisher.setItems(publishers);
        if(book != null && book.getPublisher() != null) {
            int selectPos = 0;
            for (Publisher publisher : publishers) {
                if (publisher.getId().equals(book.getPublisher().getId())) {
                    cbPublisher.getSelectionModel().select(selectPos);
                    break;
                }
                selectPos++;
            }
        }
    }

    private void updateChoiceBoxAudiobooks() {
        ObservableList<AudioBook> audiobooks = FXCollections.observableArrayList();
        try {
            audiobooks.addAll(helper.getAudioBook().queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cbAudiobooks.setItems(audiobooks);
        if(book != null && book.getAudioBook() != null) {
            int selectPos = 0;
            for (AudioBook audioBook : audiobooks) {
                if (audioBook.getId().equals(book.getAudioBook().getId())) {
                    cbAudiobooks.getSelectionModel().select(selectPos);
                    break;
                }
                selectPos++;
            }
        }
    }

    private void updateBook() throws Exception{
        if(this.book == null) {
            book = new Book();
        }
        book.setName(tfName.getText());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        book.setDate(formatter.parse(tfYear.getText()));
        book.setPageNumbers(Integer.parseInt(tfPageNumbers.getText()));
        book.setLanguage(tfLanguage.getText());
        book.setType(tfBooktype.getText());
        Cost cost = new Cost();
        cost.setSize(Integer.parseInt(tfCost.getText()));
        cost.setDiscount(Integer.parseInt(tfDisountCost.getText()));
        helper.getCosts().create(cost);
        book.setCost(helper.getCosts().queryForAll().get(helper.getCosts().queryForAll().size() - 1));
        book.setAuthor(cbfAuthor.getValue());
        book.setGenre(cbGenre.getValue());
        book.setPublisher(cbPublisher.getValue());
        book.setAudioBook(cbAudiobooks.getValue());
        book.setBooknumbers(Integer.parseInt(tfBooknumbers.getText()));
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    private void onOkClick() {
        try {
            updateBook();
            if (isEdit) {
                helper.updateBook(book);
            } else {
                helper.addBook(book);
            }
            isOkClicked = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialogStage.close();
    }

    @FXML
    private void onCancelClick() {
        dialogStage.close();
    }

    @FXML
    private void onAddAuthorClick() {
        if(app.showAuthorDialog(null)) {
            updateChoiceBoxAuthors();
        }
    }

    @FXML
    private void onAddGenreClick() {
        if(app.showGenreDialog(null)) {
            updateChoiceBoxGenres();
        }
    }

    @FXML
    private void onAddPublisherClick() {
        if(app.showPublisher(null)) {
            updateChoiceBoxPublishers();
        }
    }

    @FXML
    private void onAddAudioBook() {
        if(app.showAudiobook(null)) {
            updateChoiceBoxAudiobooks();
        }
    }

    @FXML
    private void initialize() {
        helper = DatabaseHelper.getInstance();
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
