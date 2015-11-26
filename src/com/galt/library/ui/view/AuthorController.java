package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.Author;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Grishechko on 27.11.2015.
 */
public class AuthorController {
    @FXML TextField tfFirstname;
    @FXML TextField tfSecondname;
    @FXML TextField tfLastname;
    @FXML TextField tfBirthday;
    @FXML TextField tfDeath;

    @FXML Button btnOk;
    @FXML Button btnCancel;

    private Author author;
    private boolean isOkClick;
    private Stage dialogStage;
    private App app;

    private void updateAuthor() {
        if (author == null) {
            author = new Author();
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            author.setBirthday(formatter.parse(tfBirthday.getText()));
            if(!tfDeath.getText().isEmpty()) {
                author.setDeath(formatter.parse(tfDeath.getText()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        author.setFirstName(tfFirstname.getText());
        author.setLastName(tfLastname.getText());
        author.setMiddleName(tfSecondname.getText());
    }

    @FXML
    public void onAddAuthorClick() {
        updateAuthor();
        DatabaseHelper.getInstance().addAuthor(author);
        isOkClick = true;
        dialogStage.close();
    }

    @FXML
    public void onCancelClick() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return isOkClick;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
