package com.example.labb4.model;

import java.io.Serializable;

public class Block implements Serializable {

    /*
    value is the value of the block
     */
    private int value;

    /*
    isLocked is true if the block is locked
     */
    private boolean isLocked;

    /*
    isHighlighted is true if the block is highlighted
     */
    private boolean isHighlighted;

    /*
    solution is the solution to the sudoku
    */
    private int solution;

    /*
    Constructor
     */
    public Block(int value, int solution, boolean isLocked) {
        this.value = value;
        this.isLocked = isLocked;
        this.solution = solution;
        isHighlighted = false;
    }


    public boolean getIsHighlighted() {
        return isHighlighted;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public int getValue() {
        return value;
    }

    int getSolution() {
        return solution;
    }

    /*
    setValue sets the value of the block
     */
    boolean setValue(int value) {
        if (value < 0 || value > 9) throw new IllegalStateException("Illegal Value for block");
        if (this.isLocked) return false;
        this.value = value;
        return true;
    }

    void setHighlighted(boolean highlighted) {
        this.isHighlighted = highlighted;
    }

    void setLocked(boolean locked) {
        this.isLocked = locked;
    }
}
