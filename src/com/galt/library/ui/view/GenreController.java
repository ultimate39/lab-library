package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.Author;
import com.galt.library.core.model.Book;
import com.galt.library.core.model.Genre;
import com.galt.library.core.model.Publisher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Grishechko on 26.11.2015.
 */
public class GenreController {
    @FXML TextField tfName;
    @FXML TextField tfAgeRestriction;

    @FXML Button btnGenre;
    @FXML Button btnCancel;

    private Stage dialogStage;
    private Genre genre;
    private DatabaseHelper helper;
    private boolean isOkClicked;
    private App app;

    private void updateGenre() throws Exception{
        if(this.genre == null) {
            genre = new Genre();
        }
        genre.setName(tfName.getText());
        genre.setAgeRestriction(Integer.parseInt(tfAgeRestriction.getText()));
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    private void onAddGenreClick() {
        try {
            updateGenre();
            helper.getGenresHelper().createIfNotExists(genre);
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
