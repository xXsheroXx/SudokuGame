package com.example.labb4.model;

import com.example.labb4.SudokuLevel;

public class SudokuLogic {

    /*
        table is the sudoku table
    */
    private SudokuTable table;

    /*
    Constructor
     */
    public SudokuLogic(SudokuLevel level) {
        table = new SudokuTable(level);
    }

    public Block[][] getTableAsArray() {
        return table.getTable();
    }
    public SudokuTable getTable () {
        return table;
    }

    /*
    updateBlock updates the value of a block
    @param row is the row of the block
    @param col is the column of the block
    @param value is the value of the block
    @return void
     */
    public void updateBlock(int row, int col, int value) {
        table.setValue(row, col, value);
    }

    /*
    updateTable updates the sudoku table
    @param newTable is the new sudoku table
    @return void
     */
    public void updateTable(Block[][] newTable) {
        table.setTable(newTable);
    }

    /*
    checkTable checks if the sudoku table is correct
    @return boolean true if the table is correct
     */
    public boolean checkTable() {
        return table.checkTable();
    }


    /*
    giveHint gives a hint to the player
     */
    public void giveHint() {
        table.addRandomHint();
    }

    /*
    clearBlock clears a block
    @param row is the row of the block
    @param col is the column of the block
    @return void
     */
    public void clearBlock(int row, int col) {
        table.getTable()[row][col].setValue(0);
        table.incrementNrOfAvailableTiles();
    }

    /*
    clearTable clears the sudoku table
    @param isNewGame is true if it's a new game
    @return void
     */
    public void clearTable(boolean isNewGame){
        table.resetGame(isNewGame);
    }

    /*
    checkIfGameOver checks if the game is over
    @return boolean true if the game is over
     */
    public boolean checkIfGameOver() {
        return this.table.getNrOfAvailableTiles() == 0 && checkTable();
    }
}
