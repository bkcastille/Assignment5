import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
   * In this method, you will need to generate the GUI of this calculator. Use 
different kinds of panes for alignment
   * This method will also implement the event handlers for each button press. You 
may elect to divide the load among multiple methods if you prefer.
   */
 protected BorderPane getPane() {
BorderPane mainPane = new BorderPane();
//Your code goes here .....

    return mainPane;
  }
 //----------------------------------------------------------
 /**
  * This method checks the accumulator value and by calling getAccumValue(). based 
on this value it either calls the 
  * face.makeHappy() or  face.makeSad() and sets the face to happy/sad in txtFace
  * Will be called whenever one of the operation buttons is pressed
  */
 public void display(){
//Your code goes here .....
}
}
//*********************************************************
class Calculator
{
  private float currentAccumValue;
  public Calculator (){
currentAccumValue = 0.0f;
  }
  public void add(float inputValue){
      currentAccumValue+= inputValue;
  }
  public void subtract(float inputValue){
      currentAccumValue-= inputValue;
  }
  public void multiply(float inputValue){
      currentAccumValue*= inputValue;
  }
  public void divide(float inputValue){
  if(inputValue == 0) {
          JOptionPane.showMessageDialog(null, "Error: You cannot divide by 0!", 
"Error",
                  JOptionPane.INFORMATION_MESSAGE);
  } else {
              currentAccumValue /= inputValue;
  }
  }
  public void clearAccum(){
      currentAccumValue = 0;
  }
  public float getAccumValue(){
      return currentAccumValue;
  }
}
//*********************************************************

class Face
{
  private boolean faceState;
  public Face(){
makeHappy();
  }
  public void makeHappy(){
      faceState = true;
  }
  public void makeSad(){
      faceState = false;
  }
  public boolean getFaceState(){
      return faceState;
  }
}
