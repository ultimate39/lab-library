package com.galt.library;

import com.galt.library.core.Constants;
import com.galt.library.core.model.*;
import com.galt.library.ui.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Grishechko on 25.11.2015.
 */
public class App extends Application {

    Stage primaryStage;
    public Pane mainPane;
    private Integer rights;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("ui/view/main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Интернет библиотека");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setMinWidth(610);
        primaryStage.setMinHeight(600);
        Main main = loader.getController();
        main.setApp(this);
        primaryStage.getIcons().add(new Image(App.class.getResource("book.png").toURI().toString()));
        primaryStage.hide();
        if(showLoginDialog()) {
            primaryStage.show();
            main.update();
        }
    }

    public boolean showBookDialog(Book book) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/book.fxml"));
            mainPane =  loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(book == null ? "Добавить книгу" : "Редактировать книгу");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(mainPane);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            BookController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBook(book);
            controller.setApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAuthorDialog(Author author) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/author.fxml"));
            Pane page =  loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(author == null ? "Добавить автора" : "Редактировать автора");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AuthorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);
            //controller.setBook(book);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showGenreDialog(Genre genre) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/genre.fxml"));
            Pane page =  loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(genre == null ? "Добавить жанр" : "Редактировать жанр");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            GenreController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showReaderDialog(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/user.fxml"));
            Pane page =  loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(user == null ? "Добавить пользователя" : "Редактировать пользователя");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            UserController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);
            controller.setUser(user);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPublisher(Publisher publisher) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/publisher.fxml"));
            Pane page =  loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(publisher == null ? "Добавить издателя" : "Редактировать издателя");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PublisherController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAudiobook(AudioBook audioBook) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/audiobook.fxml"));
            Pane page =  loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(audioBook == null ? "Добавить аудиокнигу" : "Редактировать аудиокнигу");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AudiobookController controller = loader.getController();
            controller.setAudiobook(audioBook);
            controller.setDialogStage(dialogStage);
            controller.setApp(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


   /* public boolean showReturnBookDialog(IssuedBook book) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/issuebook.fxml"));
            Pane page =  loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Выдать книгу");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            IssuebookContoller controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);
            controller.setIssuedBook(book);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }*/

    public boolean showLoginDialog() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("ui/view/login.fxml"));
        Pane page = null;
        try {
            page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Авторизация");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            LoginController controller = loader.getController();
            //noinspection unchecked
            controller.setApp(this);
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public boolean isAdminRights() {
        return rights == Constants.RIGHTS_TEACHER;
    }

    public static void main(String[] args) {
        App.launch(args);
    }
}
