package com.template.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogUtil {
    public static void showError (String mensagem)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public static boolean showConfirmation(String mensagem)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmacao");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait().get() == ButtonType.OK;
    }
    public static void showAlert (String mensagem)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public static void showInfo (String mensagem)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("informacao");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
