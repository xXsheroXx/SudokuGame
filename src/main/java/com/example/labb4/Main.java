package com.example.labb4;

import com.example.labb4.model.Block;
import com.example.labb4.model.SudokuLogic;
import com.example.labb4.view.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        SudokuLogic model = new SudokuLogic(SudokuLevel.EASY);

        GUI view = new GUI(model);

        BorderPane root = view.getRootPane();

        Scene scene = new Scene(root, 450, 400);

        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }
}
