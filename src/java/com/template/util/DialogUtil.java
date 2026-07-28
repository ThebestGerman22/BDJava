package com.template.util;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class DialogUtil {

    public static void showError(String Mensagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(Mensagem);
        alert.showAndWait();

    }


    public static void showInfo(String Mensagem){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(Mensagem);
        alert.showAndWait();

    }

    public static boolean showConfirmation(String Mensagem){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(Mensagem);
        return alert.showAndWait().get() == ButtonType.OK;

    }

    public static void showWarning(String Mensagem){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(Mensagem);
        alert.showAndWait();

    }

}

