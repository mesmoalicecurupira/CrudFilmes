package com.template;

import javafx.application.Application; // Adicionada a importação
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Adicionado o "extends Application" aqui na linha 8
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        // Ele vai buscar o main.fxml na mesma pasta do Main.java
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load(),600,400);

        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args); // Passar o 'args' aqui ajuda o JavaFX na inicialização
    }
}