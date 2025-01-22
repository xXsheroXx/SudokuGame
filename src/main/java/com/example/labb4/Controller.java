package com.example.labb4;


import com.example.labb4.model.SaveLoadGame;
import com.example.labb4.model.SudokuLogic;
import com.example.labb4.model.Block;
import com.example.labb4.view.GUI;
import com.example.labb4.view.PopUp;

import java.io.IOException;

public class Controller {
    private int selectedButton = 0;
    private boolean isFileSaved = false;

    private SudokuLogic model;
    private GUI view;

    public Controller(SudokuLogic model, GUI view) {
        this.model = model;
        this.view = view;
    }

    public void handleSaveGameClick(){
        try {
            SaveLoadGame.saveGame(model.getTableAsArray());
            isFileSaved = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleLoadGameClick() {
        try {
            Block[][] loadedSudokuTable = SaveLoadGame.loadGame();
            model.updateTable(loadedSudokuTable);
            view.updateSudokuTable();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void handleButtonClickValue(int value) {
        if (value < 1 || value > 12) throw new IllegalStateException("Value out of range.");
        this.selectedButton = value;
        System.out.println(selectedButton);
    }


    public void handleTileClick(int row, int col) {
        if (selectedButton == 0 || selectedButton > 10) return;

        if (selectedButton == 10) model.clearBlock(row, col);
        else model.updateBlock(row, col, this.selectedButton);
        view.updateSudokuTable();
        if (model.checkIfGameOver()) {
            handleEndGame();
            return;
        };
    }

    public void handleExitButtonClick() {
        if (isFileSaved) System.exit(0);
        boolean choice = PopUp.exitPopUp();
        if (choice) {
            try {
                SaveLoadGame.saveGame(model.getTableAsArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(0);
    }
    public void handleAboutButtonClick() {
        PopUp.aboutPopUp();
    }

    public void handleCheckButtonClick () {
        PopUp.checkPopUp(model.checkTable());
    }

    public void handleHintButtonClick() {

        model.giveHint();
//        view.highlightTile(index);
        view.updateSudokuTable();
        if (model.checkIfGameOver()) {
            handleEndGame();
            return;
        }
    }

    public void handleClearButtonClick() {
        model.clearTable(false);
        view.updateSudokuTable();
    }

    public void handleNewGameClick() {
        model.clearTable(true);
        model.getTable().randomizeSudokuBoard();
        view.updateSudokuTable();
    }

    public void handleDifficultyClick(SudokuLevel level) {
        model.getTable().changeDifficulty(level);
        view.updateSudokuTable();
    }

    public void handleEndGame() {
        PopUp.endGamePopUp();
        model.clearTable(true);
        model.getTable().randomizeSudokuBoard();
        view.updateSudokuTable();
    }

}
