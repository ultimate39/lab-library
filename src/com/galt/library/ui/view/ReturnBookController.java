package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.Book;
import com.galt.library.core.model.IssuedBook;
import com.galt.library.core.model.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Grishechko on 27.11.2015.
 */
public class ReturnBookController {
    @FXML TextField tfDate;
    @FXML TextField tfDatereturn;

    @FXML ChoiceBox<Reader> cbReaders;

    @FXML Button btnIssuebook;
    @FXML Button btnCancel;

    private Stage dialogStage;
    private IssuedBook issuedBook;
    private Book book;
    private DatabaseHelper helper;
    private boolean isOkClicked;
    private App app;

    private void updateIssuedbook() throws Exception{
        if(this.issuedBook == null) {
            issuedBook = new IssuedBook();
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        issuedBook.setDateIssueBook(formatter.parse(tfDate.getText()));
        issuedBook.setDateReturnBook(formatter.parse(tfDatereturn.getText()));
        issuedBook.setReader(cbReaders.getSelectionModel().getSelectedItem());
        issuedBook.setBook(book);
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }


    @FXML
    private void onIssueBookClick() {
        try {
            updateIssuedbook();
            helper.getIssuedBooks().createIfNotExists(issuedBook);
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
    private void initialize() throws SQLException {
        helper = DatabaseHelper.getInstance();
        ObservableList<Reader> readers = FXCollections.observableArrayList();
        readers.addAll(helper.getReaders().queryForAll());
        cbReaders.setItems(readers);
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
