package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.Genre;
import com.galt.library.core.model.Reader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;

/**
 * Created by Grishechko on 27.11.2015.
 */
public class ReaderController {
    @FXML TextField tfFirstname;
    @FXML TextField tfSecondname;
    @FXML TextField tfLastname;
    @FXML TextField tfPhone;

    @FXML Button btnReader;
    @FXML Button btnCancel;

    private Stage dialogStage;
    private Reader reader;
    private DatabaseHelper helper;
    private boolean isOkClicked;
    private boolean isEdit;
    private App app;

    private void updateReader() throws Exception{
        if(this.reader == null) {
            reader = new Reader();
        }
        reader.setFirstname(tfFirstname.getText());
        reader.setSecondname(tfSecondname.getText());
        reader.setLastname(tfLastname.getText());
        reader.setPhonenumber(tfPhone.getText());
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    private void onAddReaderClick() {
        try {
            updateReader();
            if(isEdit) {
                helper.getReaders().update(reader);
            } else {
                helper.getReaders().createIfNotExists(reader);
            }
            isOkClicked = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialogStage.close();
    }

    public void setReader(Reader reader) {
        this.reader = reader;
        if(reader != null) {
            btnReader.setText("Редактировать");
            isEdit = true;
            tfFirstname.setText(reader.getFirstname());
            tfSecondname.setText(reader.getSecondname());
            tfLastname.setText(reader.getLastname());
            tfPhone.setText(reader.getPhonenumber());
        }
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
