package com.example.labb4.model;

import com.example.labb4.GlobalConst;
import com.example.labb4.SudokuLevel;

import java.util.Random;

public class SudokuTable {

    /*
        table is the sudoku table
     */
    private Block[][] table;

    /*
    nrOfAvailableTiles is the number of available tiles
     */
    private int nrOfAvailableTiles = GlobalConst.GRID_SIZE * GlobalConst.GRID_SIZE;


    /*
    Constructor
     */
    public SudokuTable(SudokuLevel level) {
        generateSudokuLevel(level);
        randomizeSudokuBoard();
    }

    /*
    changeDifficulty changes the difficulty of the sudoku
    @param level is the new difficulty
    @return void
     */
    public void changeDifficulty(SudokuLevel level) {
        generateSudokuLevel(level);
        randomizeSudokuBoard();
    }


    Block[][] getTable() {
        return table;
    }

    public int getNrOfAvailableTiles() {
        return nrOfAvailableTiles;
    }

    /*
    incrementNrOfAvailableTiles increments the number of available tiles
    @return void
     */
    void incrementNrOfAvailableTiles() {
        nrOfAvailableTiles++;
    }

    void setTable(Block[][] newTable) {
        table = newTable;
    }
    void setValue(int row, int col, int value) {
        if (table[row][col].setValue(value)) nrOfAvailableTiles--;
    }

    /*
    checkTable checks if the sudoku table is correct
    @return boolean true if the table is correct
     */
    boolean checkTable() {
        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                if (table[row][col].getValue() == 0) continue;
                if (table[row][col].getValue() == table[row][col].getSolution()) continue;
                return false;
            }
        }
        return true;
    }

    /*
    addRandomHint adds a random hint to the sudoku table
    @return void
     */
    void addRandomHint() {
        // Generate random int between 0 and nrOfAvailableTiles
        int randomIndex = (int) (Math.random() * nrOfAvailableTiles);
        int count = 0;
        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                if (table[row][col].getValue() == 0) {
                    if (count == randomIndex) {
                        table[row][col].setValue(table[row][col].getSolution());
                        table[row][col].setHighlighted(true);
                        table[row][col].setLocked(true);

                        nrOfAvailableTiles--;
                    }
                    count++;
                }
            }
        }
    }

    /*
    generateSudokuLevel generates a sudoku level
    @param level is the level of the sudoku
    @return void
     */
    private void generateSudokuLevel(SudokuLevel level) {
        String map = switch (level) {
            case EASY -> SudokuMaps.easy;
            case HARD -> SudokuMaps.hard;
            default -> SudokuMaps.medium;
        };
        this.table = convertMapToBlockMatrix(map);
    }

    /*
    convertMapToBlockMatrix converts a map to a block matrix
    @param map is the map to be converted
    @return Block[][] is the block matrix
     */
    private Block[][] convertMapToBlockMatrix(String map) {
        if (map.length() != GlobalConst.GRID_SIZE * GlobalConst.GRID_SIZE * 2)
            throw new IllegalArgumentException("representation length " +
                    map.length());
        Block[][] values = new Block[GlobalConst.GRID_SIZE][GlobalConst.GRID_SIZE];
        char[] charRepresentation = map.toCharArray();
        int charIndex = 0;
        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                values[row][col] = new Block(
                        convertCharToSudokuInt(charRepresentation[charIndex]),
                        convertCharToSudokuInt(charRepresentation[charIndex + 81]),
                        charRepresentation[charIndex] != '0'
                );
                if (charRepresentation[charIndex] != '0') nrOfAvailableTiles--;
                charIndex++;
            }
        }
        return values;
    }


    /*
    convertCharToSudokuInt converts a char to a sudoku int
    @param ch is the char to be converted
    @return int is the converted char
     */
    private static int convertCharToSudokuInt(char ch) {
        if (ch < '0' || ch > '9') throw new IllegalArgumentException("character " +
                ch);
        return ch - '0';
    }


    /*
    randomizeSudokuBoard randomizes the sudoku board
    @return void
     */
    public void randomizeSudokuBoard() {
        Block[][] randomizedBoard = mirrorHorizontally(this.table);

        Random randGen = new Random();
        randomizedBoard = swapBlocks(randomizedBoard, randGen.nextInt(1,10), randGen.nextInt(1, 10));

        this.table = randomizedBoard;
    }

    /*
    mirrorHorizontally mirrors the sudoku board horizontally
    @param board is the board to be mirrored
    @return Block[][] is the mirrored board
     */
    private Block[][] mirrorHorizontally(Block[][] board) {
        Block[][] mirroredBoard = new Block[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                mirroredBoard[row][col] = board[8 - row][col];
            }
        }
        return mirroredBoard;
    }

    /*
    swapBlocks swaps two blocks in the sudoku board
    @param board is the board to be swapped
    @param value1 is the first value to be swapped
    @param value2 is the second value to be swapped
    @return Block[][] is the swapped board
     */
    private Block[][] swapBlocks(Block[][] board, int value1, int value2) {
        Block[][] swappedBoard = new Block[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col].getValue() == value1) {
                    swappedBoard[row][col] = new Block(value2, value2, true);
                } else if (board[row][col].getValue() == value2) {
                    swappedBoard[row][col] = new Block(value1, value1, true);
                } else {
                    swappedBoard[row][col] = board[row][col];
                }
            }
        }
        return swappedBoard;
    }


    /*
    resetGame resets the game
    @param isNewGame is true if it's a new game
    @return void
     */
    void resetGame(boolean isNewGame) {
        for (int row=0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col=0; col < GlobalConst.GRID_SIZE; col++) {
                if(table[row][col].getIsLocked()){
                    if (isNewGame && table[row][col].getIsHighlighted()) {
                        table[row][col].setLocked(false);
                        table[row][col].setHighlighted(false);
                        table[row][col].setValue(0);
                        nrOfAvailableTiles++;
                    }
                    continue;
                }
                table[row][col].setValue(0);
                nrOfAvailableTiles++;

            }
        }
    }


    /*
    shuffleTable shuffles the table
    @return void
     */

}
