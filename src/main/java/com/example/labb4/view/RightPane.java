package com.example.labb4.view;

import com.example.labb4.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class RightPane {
    private final VBox vBox;
    private Button button;

    public RightPane(Controller controller) {
        vBox = new VBox(10);
        for (int i=1; i < 10; i++) {
            button = new Button(String.valueOf(i));
            int finalI = i;
            button.addEventHandler(ActionEvent.ACTION, e->controller.handleButtonClickValue(finalI));
            vBox.getChildren().add(button);
        }
        button = new Button("c");
        button.addEventHandler(ActionEvent.ACTION, e->controller.handleButtonClickValue(10));
        vBox.getChildren().add(button);
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,20,0,20));
    }

    public VBox getvBox() {
        return vBox;
    }

//    private class ButtonHandler implements EventHandler<ActionEvent> {
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