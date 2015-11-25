package com.galt.library;

import com.galt.library.core.model.Book;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

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

        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:mysql://localhost:3306/lab?user=root&password=root");
            Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
            List<Book> books = dao.queryForAll();
            for(Book book : books) {
                System.out.println(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        App.launch(args);
    }
}
