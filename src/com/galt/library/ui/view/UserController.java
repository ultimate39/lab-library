package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Grishechko on 27.11.2015.
 */
public class UserController {
    @FXML TextField tfFirstname;
    @FXML TextField tfSecondname;
    @FXML TextField tfLastname;
    @FXML TextField tfPhone;
    @FXML TextField tfEmail;
    @FXML TextField tfNickname;
    @FXML TextField tfCountry;
    @FXML TextField tfCity;

    @FXML Button btnReader;
    @FXML Button btnCancel;

    private Stage dialogStage;
    private User user;
    private DatabaseHelper helper;
    private boolean isOkClicked;
    private boolean isEdit;
    private App app;

    private void updateReader() throws Exception{
        if(this.user == null) {
            user = new User();
        }
        user.setFirstname(tfFirstname.getText());
        user.setSecondname(tfSecondname.getText());
        user.setLastname(tfLastname.getText());
        user.setPhonenumber(tfPhone.getText());
        user.setEmail(tfEmail.getText());
        user.setNickname(tfNickname.getText());
        user.setCountry(tfCountry.getText());
        user.setCity(tfCity.getText());

    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    private void onAddReaderClick() {
        try {
            updateReader();
            if(isEdit) {
                helper.getReaders().update(user);
            } else {
                helper.getReaders().createIfNotExists(user);
            }
            isOkClicked = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialogStage.close();
    }

    public void setUser(User user) {
        this.user = user;
        if(user != null) {
            btnReader.setText("Редактировать");
            isEdit = true;
            tfFirstname.setText(user.getFirstname());
            tfSecondname.setText(user.getSecondname());
            tfLastname.setText(user.getLastname());
            tfPhone.setText(user.getPhonenumber());
            tfEmail.setText(user.getEmail());
            tfNickname.setText(user.getNickname());
            tfCountry.setText(user.getCountry());
            tfCity.setText(user.getCity());
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
