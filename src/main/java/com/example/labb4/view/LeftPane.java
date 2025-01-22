package com.example.labb4.view;

import com.example.labb4.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftPane {
    private VBox vBox;
    private Button button;

    public LeftPane(Controller controller) {
        vBox = new VBox(20);
        button = new Button("Check");
        button.addEventHandler(ActionEvent.ACTION, e->controller.handleCheckButtonClick());
        vBox.getChildren().add(button);
        button = new Button("Hint");
        button.addEventHandler(ActionEvent.ACTION, e->controller.handleHintButtonClick());
        vBox.getChildren().add(button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(0,10,0,10));
    }

    public VBox getvBox (){
        return vBox;
    }

//    private class ButtonHandler implements EventHandler<ActionEvent> {
//
//        private final int index;
//        public ButtonHandler(int index) {
//            this.index = index;
//        }
//
//        public int getIndex() {
//            return index;
//        }
//
//        @Override
//        public void handle(ActionEvent actionEvent) {
//
//        }
//    }
}
