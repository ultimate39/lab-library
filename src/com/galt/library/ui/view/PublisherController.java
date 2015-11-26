package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.db.DatabaseHelper;
import com.galt.library.core.model.Publisher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Grishechko on 27.11.2015.
 */
public class PublisherController {
    @FXML TextField tfName;
    @FXML TextField tfPhone;

    @FXML Button btnPublisher;
    @FXML Button btnCancel;

    private Stage dialogStage;
    private Publisher publisher;
    private DatabaseHelper helper;
    private boolean isOkClicked;
    private App app;

    private void updatePublisher() throws Exception{
        if(this.publisher == null) {
            publisher = new Publisher();
        }
        publisher.setNamePublisher(tfName.getText());
        publisher.setPhoneNumberPublisher(tfPhone.getText());
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }

    @FXML
    private void onAddPublisherClick() {
        try {
            updatePublisher();
            helper.getPublishersHelper().createIfNotExists(publisher);
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
