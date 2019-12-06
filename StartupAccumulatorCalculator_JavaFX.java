import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;


public class StartupAccumulatorCalculator_JavaFX extends Application {

    private TextField txtOutput = new TextField();
    TextField txtFace = new TextField();

    private float inputValue;

    private Calculator calculator = new Calculator();
    private Face face = new Face();

    public static void main(String[] args) {
        launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene by calling the getPane() method and place it in the stage
        Scene scene = new Scene(getPane(), 200, 250);
        primaryStage.setTitle("Fun Calculator"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }


    /**
     * getPane() returns a pane to be used with the scene of this calculator.
     * In this method, you will need to generate the GUI of this calculator. Use different kinds of panes for alignment
     * This method will also implement the event handlers for each button press. You may elect to divide the load among multiple methods if you prefer.
     */
    protected BorderPane getPane() {

        BorderPane mainPane = new BorderPane();
        //Your code goes here .....

        //create UI
        //button grid
        GridPane buttonsPane = new GridPane();
        buttonsPane.setHgap(8);
        buttonsPane.setVgap(8);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setPadding(new Insets(10));
        mainPane.setPadding(new Insets(30));

        //create new hbox, set spacing to 8px and set position to center
        HBox hbox = new HBox(txtOutput, txtFace);
        hbox.setSpacing(8);
        hbox.setAlignment(Pos.CENTER);

        //finally, add text boxes and buttons to pane
        mainPane.setTop(hbox);
        mainPane.setCenter(buttonsPane);


        //create array and set values for the buttons
        Object[][] buttons = new Object[][]{{7, 8, 9, "/"}, {4, 5, 6, "*"}, {1, 2, 3, "-"}, {null, 0, "C", "+"}};

        //loop until grid is 4 rows by 4 columns
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                //If grid pane is at i, add a button. If af j, add a dummy node
                if (buttons[i][j] != null) {
                    Button b = new Button(buttons[i][j].toString());

                    //process button click event
                    b.setOnAction(e -> handleClick(b.getText()));

                    //Add grid at column j, row i
                    buttonsPane.add(b, j, i);

                }
                else {
                    //Leave space blank
                    buttonsPane.add(new Label(""), j, i);
                }
            }
        }


        return mainPane;
    }
    //----------------------------------------------------------

    /**
     * This method checks the accumulator value and by calling getAccumValue(). based on this value it either calls the
     * face.makeHappy() or  face.makeSad() and sets the face to happy/sad in txtFace
     * Will be called whenever one of the operation buttons is pressed
     */

    public void display() {

        //Your code goes here .....

        //if-else to determine if happy or sad
        if (calculator.getAccumValue() <= 0) {
            face.makeSad();
        }

        else {
            face.makeHappy();
        }

        //set values for happy and sad faces
        if (face.getFaceState()) {
            txtFace.setText(":-)");
        }

        else {
            txtFace.setText(":-(");
        }
    }


    //event handler for when buttons are clicked
    private void handleClick(String str) {

        //pull in clicked value
        char c = str.toUpperCase().charAt(0);

        //if clicked value is a character, assign it to inputValue
        if (Character.isDigit(c)) {
            inputValue = Character.digit(c, 10);
        }

        else
            {
            //if not a character, determine and perform function
            switch (c) {

                case '+':
                    calculator.add(inputValue);
                    txtOutput.setText("" + calculator.getAccumValue());
                    display();
                    break;

                case '-':
                    calculator.subtract(inputValue);
                    txtOutput.setText("" + calculator.getAccumValue());
                    display();
                    break;

                case '*':
                    calculator.multiply(inputValue);
                    txtOutput.setText("" + calculator.getAccumValue());
                    display();
                    break;

                case '/':
                    calculator.divide(inputValue);
                    txtOutput.setText("" + calculator.getAccumValue());
                    display();
                    break;

                case 'C':
                    calculator.clearAccum();
                    txtFace.setText("");
                    txtOutput.setText("");
                    break;

            }

        }

    }

}


//*********************************************************
class Calculator {
    private float currentAccumValue;

    public Calculator() {
        currentAccumValue = 0.0f;
    }

    public void add(float inputValue) {
        currentAccumValue += inputValue;
    }

    public void subtract(float inputValue) {
        currentAccumValue -= inputValue;
    }

    public void multiply(float inputValue) {
        currentAccumValue *= inputValue;
    }

    public void divide(float inputValue) {
        if (inputValue == 0) {
            JOptionPane.showMessageDialog(null, "Error: You cannot divide by 0!", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            currentAccumValue /= inputValue;
        }
    }

    public void clearAccum() {
        currentAccumValue = 0;
    }

    public float getAccumValue() {
        return currentAccumValue;
    }
}
//*********************************************************

class Face {
    private boolean faceState;

    public Face() {
        makeHappy();
    }

    public void makeHappy() {
        faceState = true;
    }

    public void makeSad() {
        faceState = false;
    }

    public boolean getFaceState() {
        return faceState;

    }
}
