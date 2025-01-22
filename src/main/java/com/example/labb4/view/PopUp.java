package com.example.labb4.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PopUp {
    private PopUp(){

    }

    public static void aboutPopUp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("Fill a 9x9 grid with numbers 1-9, no repeats in rows, columns, or boxes. Use logic, no guessing.");
        alert.showAndWait();
    }

    public static void checkPopUp(boolean result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Check");
        if (result) alert.setContentText("Looks Good to me");
        else
            alert.setContentText("Nah fam, try again");

        alert.showAndWait();
    }

    public static boolean exitPopUp() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit Game");
        alert.setTitle("Save Game");
        alert.setContentText("Do you want to save your progress?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    public static void endGamePopUp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setContentText("You have completed the game!");
        alert.showAndWait();
    }
}
