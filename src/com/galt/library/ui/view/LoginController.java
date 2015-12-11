package com.galt.library.ui.view;

import com.galt.library.App;
import com.galt.library.core.Constants;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by Grishechko on 06.12.2015.
 */
public class LoginController {
    @FXML PasswordField pfPassword;
    @FXML TextField tfLogin;
    @FXML Text textError;

    private App app;

    private String passwordAdmin = "1234";
    private String loginAdmin = "sasha";

    private String passwordStudent = "student";
    private String loginStudent = "student";

    private Stage dialogStage;
    private boolean isLogin = false;

    @FXML
    public void onLogin() {
        String enteredPassword = pfPassword.getText();
        String enteredLogin = tfLogin.getText();
        if (enteredPassword.equals(passwordAdmin) && enteredLogin.equals(loginAdmin)) {
            app.setRights(Constants.RIGHTS_TEACHER);
            isLogin = true;
            dialogStage.close();
        } else if (enteredPassword.equals(passwordStudent) && enteredLogin.equals(loginStudent)) {
            app.setRights(Constants.RIGHTS_STUDENT);
            isLogin = true;
            dialogStage.close();
        } else {
            textError.setVisible(true);
        }
    }

    @FXML
    public void initialize() {
        textError.setVisible(false);
    }

    @FXML
    public void onFinishApp() {
        System.exit(0);
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (!isLogin) {
                    onFinishApp();
                }
            }
        });
    }
}
