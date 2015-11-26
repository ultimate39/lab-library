package com.galt.library;

import com.galt.library.core.model.Author;
import com.galt.library.core.model.Book;
import com.galt.library.core.model.Genre;
import com.galt.library.core.model.Publisher;
import com.galt.library.ui.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Grishechko on 25.11.2015.
 */
public class App extends Application {

    Stage primaryStage;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("ui/view/main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Библиотека");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setMinWidth(610);
        primaryStage.setMinHeight(600);
        Main main = loader.getController();
        main.setApp(this);
    }

    public boolean showBookDialog(Book book) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ui/view/book.fxml"));
            Pane page =  loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(book == null ? "Добавить книгу" : "Редактировать книгу");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
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

    public static void main(String[] args) {
        App.launch(args);
    }
}
