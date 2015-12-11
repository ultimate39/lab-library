package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.AudioBook;
import com.galt.library.core.model.Genre;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Grishechko on 11.12.2015.
 */
public class AudiobookController {
    @FXML TextField tfName;
    @FXML TextField tfLength;
    @FXML TextField tfBitrate;

    @FXML Button btnAdd;
    @FXML Button btnCancel;

    private Stage dialogStage;
    private AudioBook book;
    private DatabaseHelper helper;
    private boolean isOkClicked;
    private App app;

    private void updateAudiobook() throws Exception {
        if (this.book == null) {
            book = new AudioBook();
        }
        book.setName(tfName.getText());
        book.setBitrate(Integer.parseInt(tfBitrate.getText()));
        book.setLength(Integer.parseInt(tfLength.getText()));
    }

    public void setAudiobook(AudioBook book) {
        this.book = book;
        if (book != null) {
            tfBitrate.setText(String.valueOf(book.getBitrate()));
            tfLength.setText(String.valueOf(book.getLength()));
            tfName.setText(String.valueOf(book.getName()));
            btnAdd.setText("Редактировать");
        }
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    private void onOkClick() {
        try {
            updateAudiobook();
            helper.getAudioBook().createOrUpdate(book);
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
