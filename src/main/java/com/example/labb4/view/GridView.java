package com.example.labb4.view;

import com.example.labb4.Controller;
import com.example.labb4.GlobalConst;
import com.example.labb4.model.Block;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GridView {
    private final Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private TilePane numberPane;

    public GridView(Block[][] sudokuTable, Controller controller) {
        numberTiles = new Label[GlobalConst.GRID_SIZE][GlobalConst.GRID_SIZE];
        initNumberTiles(sudokuTable, controller);
        // ...
        numberPane = makeNumberPane();
        // ...
    }

    public void updateGrid(Block[][] sudokuTable) {
        // Update label text
        String blockValue;
        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                blockValue = String.valueOf(sudokuTable[row][col].getValue()==0 ? "" : sudokuTable[row][col].getValue());
                numberTiles[row][col].setText(blockValue);
            }
        }
        // Update Highlights
        updateHighlights(sudokuTable);
        // Rerender labels in numberPane
        numberPane = makeNumberPane();
    }

//    void highlightTile(int index) {
//        numberTiles[index/GlobalConst.GRID_SIZE][index%GlobalConst.GRID_SIZE]
//                .setStyle("-fx-border-color: black; -fx-border-width: 0.5px; -fx-background-color: #00ff00;");
//    }

    void updateHighlights(Block[][] sudokuTable) {
        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                if (sudokuTable[row][col].getIsHighlighted()){
                    numberTiles[row][col].setStyle("-fx-border-color: black; -fx-border-width: 0.5px; -fx-background-color: #00ff00;");
                } else {
                    numberTiles[row][col].setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
                }
            }
        }
    }


    // use this method to get a reference to the number (called by some other class)
    public TilePane getNumberPane() {
        return numberPane;
    }

    // called by constructor (only)
    private final void initNumberTiles(Block[][] sudokuTable, Controller controller) {
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 20);
        String blockValue;
        for (int row = 0; row < GlobalConst.GRID_SIZE; row++) {
            for (int col = 0; col < GlobalConst.GRID_SIZE; col++) {
                blockValue = String.valueOf(sudokuTable[row][col].getValue()==0 ? "" : sudokuTable[row][col].getValue());
                Label tile = new Label(blockValue);
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                tile.setFont(font);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // Normal style
//                tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                int finalRow = row;
                int finalCol = col;
                tile.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> controller.handleTileClick(finalRow, finalCol));
                // add new tile to grid
                numberTiles[row][col] = tile;
            }
        }
    }

    private final TilePane makeNumberPane() {

        int sectionSize = GlobalConst.SECTION_SIZE;
        int sectionPerRow = GlobalConst.SECTIONS_PER_ROW;

        if (numberPane != null) numberPane = null;
        numberPane = new TilePane();
        numberPane.setPrefColumns(sectionPerRow);
        numberPane.setPrefRows(sectionPerRow);
        numberPane.setAlignment(Pos.CENTER);

        int i = 0;
        for (int srow = 0; srow < sectionPerRow; srow++) {
            for (int scol = 0; scol < sectionPerRow; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(sectionSize);
                section.setPrefRows(sectionSize);
                section.setStyle( "-fx-border-color: black; -fx-border-width: 0.5px;");

                // add number tiles to this section
                for (int row = 0; row < sectionSize; row++) {
                    for (int col = 0; col < sectionSize; col++) {
                        // calculate which tile and add.
                        section.getChildren().add(
                                numberTiles[srow * sectionSize + row][scol * sectionSize + col]);
                    }
                }

                // add the section to the root tile pane
                numberPane.getChildren().add(section);

//                sections[srow][scol] = section;
            }
        }

        return numberPane;
    }

//    private EventHandler<MouseEvent> tileClickHandler = new EventHandler<MouseEvent>() {
//        @Override
//        public void handle(MouseEvent event) {
////            event.getSource()
//            for(int row = 0; row < GlobalConst.GRID_SIZE; row++) {
//                for(int col = 0; col < GlobalConst.GRID_SIZE; col++) {
//                    if(event.getSource() == numberTiles[row][col]) {
//                        // we got the row and column - now call the appropriate controller method, e.g.
//                        controller.handleTileClick(row, col);
//                        // then ...
//                        return;
//                    }
//                }
//            }
//        }
//    };
}
