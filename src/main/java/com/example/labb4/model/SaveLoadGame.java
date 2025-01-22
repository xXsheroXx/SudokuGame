package com.example.labb4.model;

import com.example.labb4.GlobalConst;
import javafx.stage.FileChooser;

import java.io.*;

public class SaveLoadGame {

    private SaveLoadGame(){}

    /*
    saveGame saves the current game to a file
    @param blocks is the current sudoku table
    @return void
    @throws IOException if the file can't be saved
     */
    public static void saveGame(Block[][] blocks) throws IOException{

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ser Files", "*.sudoku"));
        File file = fileChooser.showSaveDialog(null);

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                out.writeObject(blocks[row][col]);
            }
        }
    }


    /*
    loadGame loads a saved game from a file
    @return Block[][] the loaded sudoku table
    @throws IOException if the file can't be loaded
    @throws ClassNotFoundException if the class can't be found
     */
    public static Block[][] loadGame() throws IOException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ser Files", "*.sudoku"));
        File file = fileChooser.showOpenDialog(null);

        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        Block[][] loadedSudokuTable = new Block[GlobalConst.GRID_SIZE][GlobalConst.GRID_SIZE];

        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                loadedSudokuTable[row][col] = (Block) in.readObject();
            }
        }
        return loadedSudokuTable;
    }
}
