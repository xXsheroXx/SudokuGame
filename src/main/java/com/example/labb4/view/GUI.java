package com.example.labb4.view;

import com.example.labb4.Controller;
import com.example.labb4.model.SudokuLogic;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class GUI {
    private SudokuLogic model;
    private BorderPane root;
    private GridView sudokuTable;
//    private BorderPane mainPane;
    private Controller controller;

    public GUI(SudokuLogic model) {
        this.model = model;

        root = new BorderPane();                        // Initiate root border pane
        controller = new Controller(model, this); // Init controller

        MenuBar menuBar = new MenuView(controller).getMenuBar();  // Build Menu
        root.setTop(menuBar);

//        mainPane = new BorderPane();         // Create pane for Playground

        // LEFT
        VBox leftPane = new LeftPane(controller).getvBox();       // Check Hint pane
        root.setLeft(leftPane);

        // CENTER
        sudokuTable = new GridView(model.getTableAsArray(), controller);        // Game Board pane
        TilePane numberPane = sudokuTable.getNumberPane();
//        numberPane.setAlignment(Pos.CENTER);
        root.setCenter(numberPane);

        // RIGHT
        VBox rightPane = new RightPane(controller).getvBox();     // Num button pane
        root.setRight(rightPane);

//        root.setCenter(mainPane);
    }

    public void updateSudokuTable() {
        sudokuTable.updateGrid(model.getTableAsArray());
//        mainPane.centerProperty();
        root.setCenter(sudokuTable.getNumberPane());
    }

//    public void highlightTile(int index) {
//        sudokuTable.highlightTile(index);
//    }

    public BorderPane getRootPane() {
        return root;
    }

}
