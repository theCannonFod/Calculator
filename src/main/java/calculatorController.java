package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class calculatorController {

    @FXML
    TextField screen;

    public void exitProgram(MouseEvent mouseEvent) {

        System.exit(0);

    }

    public void minimiseProgram(MouseEvent mouseEvent) {

        Stage primaryStage;
        primaryStage = (Stage) ((Button) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);

    }

    public String getText() {

        return screen.getText();

    }

    public void clickEquals (MouseEvent mouseEvent) {

        if (screen.getText().equals("")){


        }else {

            tryCalculate();

        }

    }

    public void tryCalculate () {

        Boolean complete = false;

        while (complete == false) {


            try {

                calculate();
                complete = true;

            } catch (ScriptException e) {

                if (screen.getText().substring(screen.getText().length() - 1).equals("=")) {

                    screen.setText(screen.getText().substring(0, screen.getText().length() - 1));

                } else {

                    screen.setText("ERROR");
                    complete = true;

                }

            }

        }



    }

    public void calculate() throws ScriptException {

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = getText();
        String ans = engine.eval(foo).toString();
        screen.setText(ans);

    }

    public void clickClearScreen(MouseEvent mouseEvent) {

        clearScreen();

    }

    public void clearScreen() {

        screen.clear();

    }


    public void append(MouseEvent mouseEvent) {

        Button sourceButton = (Button) mouseEvent.getSource();
        screen.setText(screen.getText() + sourceButton.getText());

    }

    public void backSpace() {

        if (screen.getText().equals("")){


        }else {

            screen.setText(screen.getText().substring(0, screen.getText().length() - 1));

        }


    }

    public void clickBackSpace(MouseEvent mouseEvent) {

        backSpace();

    }

    public void appendWithKey(KeyEvent keyEvent) {

        screen.setFocusTraversable(true);
        screen.requestFocus();

        if (screen.getText().equals("ERROR")) {

            clearScreen();
            screen.setText(screen.getText() + keyEvent.getText());

        } else {

            screen.setText(screen.getText() + keyEvent.getText());

            if (keyEvent.getCode().toString() == "ENTER"){

                tryCalculate();

            }

            if (keyEvent.getCode().toString() == "BACK_SPACE"){

                backSpace();

            }

        }



    }


}
