package com.template.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.template.controller.MainController;
import com.template.model.dao.FilmesDAO;
import com.template.model.dao.IFilmesDAO;
import com.template.service.FilmesService;
import com.template.service.IFilmesService;
import com.template.validator.FilmesValidator;
import com.template.validator.IFilmesValidator;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        IFilmesDAO filmesDAO = new FilmesDAO();
        IFilmesValidator filmesValidator = new FilmesValidator();
        IFilmesService filmesService = new FilmesService(filmesDAO, filmesValidator);

        URL fxmlLocation = getClass().getResource("/com/template/main.fxml");
        if (fxmlLocation == null) {
            fxmlLocation = getClass().getResource("/main.fxml");
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == MainController.class) {
                return new MainController(filmesService);
            }
            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(loader.load(), 700, 500);

        stage.setTitle("CRUD Filmes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}