package com.example.labb4.view;

import com.example.labb4.Controller;
import com.example.labb4.SudokuLevel;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

public class MenuView {
    private MenuBar menuBar;

    public MenuView(Controller controller) {
        Menu fileMenu = new Menu("File");
        MenuItem loadGame = new MenuItem("Load Game");
        loadGame.addEventHandler(ActionEvent.ACTION, e->controller.handleLoadGameClick());
        MenuItem saveGame = new MenuItem("Save Game");
        saveGame.addEventHandler(ActionEvent.ACTION, e->controller.handleSaveGameClick());
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.addEventHandler(ActionEvent.ACTION, e->controller.handleExitButtonClick());
        fileMenu.getItems().addAll(loadGame, saveGame, exitGame);

        Menu gameMenu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        newGame.addEventHandler(ActionEvent.ACTION, e->controller.handleNewGameClick());
//        MenuItem difficulty = new MenuItem("Difficulty");

        Menu difficultyMenu = new Menu("Difficulty");
        MenuItem easy = new MenuItem("Easy");
        easy.addEventHandler(ActionEvent.ACTION, e->controller.handleDifficultyClick(SudokuLevel.EASY));
        MenuItem medium = new MenuItem("Medium");
        medium.addEventHandler(ActionEvent.ACTION, e->controller.handleDifficultyClick(SudokuLevel.MEDIUM));
        MenuItem hard = new MenuItem("Hard");
        hard.addEventHandler(ActionEvent.ACTION, e->controller.handleDifficultyClick(SudokuLevel.HARD));
        difficultyMenu.getItems().addAll(easy, medium, hard);
        //ArrayList<MenuItem> difficultyItems = new ArrayList<>();
        //difficultyItems.add(new MenuItem("Easy"));
        //difficultyItems.add(new MenuItem("Medium"));
        //difficultyItems.add(new MenuItem("Hard"));
        //difficultyMenu.getItems().addAll(difficultyItems);

        gameMenu.getItems().addAll(newGame, difficultyMenu);

        Menu helpMenu = new Menu("Help");
        MenuItem clear = new MenuItem("Clear");
        clear.addEventHandler(ActionEvent.ACTION, e->controller.handleClearButtonClick());
        MenuItem check = new MenuItem("Check");
        check.addEventHandler(ActionEvent.ACTION, e->controller.handleCheckButtonClick());
        MenuItem about = new MenuItem("About");
        about.addEventHandler(ActionEvent.ACTION, e->controller.handleAboutButtonClick());
        helpMenu.getItems().addAll(clear, check, about);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }


}
