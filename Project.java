//Adam Schaumburg
//CSC 1054 Project
//Apr 2023


import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;  
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Project extends Application
{
   
   private BorderPane border = new BorderPane();//create the border pane
   private GridPane gridPane = new GridPane();//gridpane
   private HBox hBoxTop = new HBox();//hBox
   private Label topLabel = new Label("The text Field");
   private GamePane gamePane;
   private ButtonListener buttonListener = new ButtonListener();
   private GamePane[][] board = new GamePane[4][4];//Use a 2D array to reference gamePane positions
   private int posX = 0;
   private int posY = 0;
   
   public void start(Stage stage)
   {
      gridPane.setVgap(10);
      gridPane.setHgap(10);
      border.setPrefSize(600,600);     
      hBoxTop.setPrefSize(600,10);
      hBoxTop.setAlignment(Pos.TOP_CENTER);
      VBox vBoxLeft = new VBox();
      vBoxLeft.setPrefSize(40,600); 
      hBoxTop.getChildren().add(topLabel);
      hBoxTop.setStyle("-fx-background-color: LIGHTGREY;");
      border.setTop(hBoxTop);
      border.setLeft(vBoxLeft);
      border.setCenter(gridPane);
   
      for (int i = 0; i < 4; i++) {//Build the game board
         for (int j = 0; j < 4; j++) {
            GamePane gamePane = new GamePane();
            gamePane.setPrefSize(125, 125);  
            board[i][j] = gamePane;//puts each gamePane object in the 2D array
            gridPane.add(gamePane, i, j);
            int posY = GridPane.getColumnIndex(gamePane);//finds the column of the gamePane
            int posX = GridPane.getRowIndex(gamePane);
            //System.out.println(" row " + posX + " column " +posY);
               
         }
      } 
      
      for(int i =0;i<4; i++){//set the initial position invisible
         for(int j = 0; j<4; j++){
            board[i][j].setCircle(true);
            board[0][2].setCircle(false);
            board[i][j].draw();
            
         }
      }
      for(int i =0;i<4; i++){//call the checkMoves method to make sure the correct buttons become visible
         for(int j = 0; j<4; j++){
            checkMoves();
         
         }
      }
      
   
      border.setCenter(gridPane);  
      Scene scene = new Scene(border, 600, 600);
      stage.setScene(scene);
      stage.show();  
   }
  
   public void click(String direction){//click method changes which circles are visible based on the input direction
      
      int counter = 16;//counts down the number of balls left in the game
      int posI=0;
      int posJ=0;
      System.out.println("i= "+posI + " j= " + posJ);
      
      if(direction.equals("Kicked Down")){//if the top button is pushed, this gathers the position data
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            
               if (direction.equals("Kicked Down") && j <=1 && board[i][j + 1].getCircle() == true && board[i][j + 2].getCircle() == false) {
                  System.out.println("down");
                  posI = GridPane.getColumnIndex(board[i][j]);
                  posJ = GridPane.getRowIndex(board[i][j]);
                  
               }
            }
         }
         System.out.println("i= "+posI + " j= " + posJ);// kept outside of the for loop or all the circles in the row/column will change
         board[posI][posJ].setCircle(false);
         board[posI][posJ].draw();
         board[posI][posJ+1].setCircle(false);
         board[posI][posJ+1].draw();
         board[posI][posJ+2].setCircle(true);
         board[posI][posJ+2].draw(); 
         System.out.println("move I "+ posI + " move J "+(posJ+2));
      }
      else if(direction.equals("Kicked Up")){
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
               if (direction.equals("Kicked Up") && j >1 && board[i][j - 1].getCircle() == true && board[i][j - 2].getCircle() == false) {
                  System.out.println("up");
                  posI = GridPane.getColumnIndex(board[i][j]);
                  posJ = GridPane.getRowIndex(board[i][j]);
                  System.out.println("i= "+posI + " j= " + posJ);
               }
            }
         } 
         board[posI][posJ].setCircle(false);
         board[posI][posJ].draw();
         board[posI][posJ-1].setCircle(false);
         board[posI][posJ-1].draw();
         board[posI][posJ-2].setCircle(true);
         board[posI][posJ-2].draw();
      }
      else if(direction.equals("Kicked Right")){
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
               if (direction.equals("Kicked Right") && i <=1 && board[i+1][j].getCircle() == true && board[i+2][j].getCircle() == false) {
                  System.out.println("right");
                  posI = GridPane.getColumnIndex(board[i][j]);
                  posJ = GridPane.getRowIndex(board[i][j]);
                  System.out.println("i= "+posI + " j= " + posJ);
               }
            }
         } 
         board[posI][posJ].setCircle(false);
         board[posI][posJ].draw();   
         board[posI+1][posJ].setCircle(false); 
         board[posI+1][posJ].draw();
         board[posI+2][posJ].setCircle(true);
         board[posI+2][posJ].draw(); 
      }
      else if(direction.equals("Kicked Left")){
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
               if (direction.equals("Kicked Left") && i >1 && board[i-1][j].getCircle() == true && board[i-2][j].getCircle() == false) {
                  System.out.println("left");
                  posI = GridPane.getColumnIndex(board[i][j]);
                  posJ = GridPane.getRowIndex(board[i][j]);
                  System.out.println("i= "+posI + " j= " + posJ);
               }
            }
         } 
         board[posI][posJ].setCircle(false);
         board[posI][posJ].draw();
         board[posI-1][posJ].setCircle(false);
         board[posI-1][posJ].draw(); 
         board[posI-2][posJ].setCircle(true);
         board[posI-2][posJ].draw(); 
      }
   
      
      for(int i =0;i<4;i++){//search the board for invisible circles and retract that from the counter
         
         for(int j = 0;j<4; j++){
            board[i][j].draw();//redraw the board
            if(board[i][j].getCircle()==false){
               counter --;  
            }
            
            checkMoves();  //check moves
           
         }
      }
      if(counter==1){
         topLabel.setText("You Win");//if counter = 1 label changes to you win
      }
      
      else{
         topLabel.setText("Number of Balls Left "+ counter);
      }
      if (!hasMovesLeft()) { // check if there are no more circles and no more moves
         topLabel.setText("You lose!"); // display the "you lose" message
         return; // exit the method
      }
   }

   public void checkMoves() {
   
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {//checks around each pane to see if the surrounding circles are visible or not
                                       // and sets the appropriate buttons visible
            if (j <= 1 && board[i][j].getCircle()==true &&  board[i][j+1].getCircle()==true && board[i][j+2].getCircle()==false) {
               
               board[i][j].setTopButtonVisible(true); 
            }
            else{
               board[i][j].setTopButtonVisible(false); 
            }
            if (j >1 && board[i][j].getCircle()==true && board[i][j-1].getCircle()==true && board[i][j-2].getCircle()==false) {
               board[i][j].setBottomButtonVisible(true);
            }
            else{
               board[i][j].setBottomButtonVisible(false); 
            }
            if (i <=1 && board[i][j].getCircle()==true && board[i+1][j].getCircle()==true && board[i+2][j].getCircle()==false) {
               board[i][j].setLeftButtonVisible(true);
            }
            else{
               board[i][j].setLeftButtonVisible(false); 
            }
            if (i >1 && board[i][j].getCircle()==true && board[i-1][j].getCircle()==true && board[i-2][j].getCircle()==false) {
               board[i][j].setRightButtonVisible(true);
            }  
            else{
               board[i][j].setRightButtonVisible(false); 
            }
         }
      }
   
   }

   private boolean hasMovesLeft() { // check if there are any possible moves left
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            if (j <= 1 && board[i][j].getCircle()==true &&  board[i][j+1].getCircle()==true && board[i][j+2].getCircle()==false) {
               
               return true;
            }
         
            if (j >1 && board[i][j].getCircle()==true && board[i][j-1].getCircle()==true && board[i][j-2].getCircle()==false) {
               return true;
            }
         
            if (i <=1 && board[i][j].getCircle()==true && board[i+1][j].getCircle()==true && board[i+2][j].getCircle()==false) {
               return true;
            }
         
            if (i >1 && board[i][j].getCircle()==true && board[i-1][j].getCircle()==true && board[i-2][j].getCircle()==false) {
               return true;
            }  
         
         }
      }
      return false; // no possible moves found
   }
   


   public class ButtonListener implements EventHandler<ActionEvent> {
      public void handle(ActionEvent e) {
      
         int row = GridPane.getRowIndex((Node) e.getSource());//this gets the row and column of the button that was pushed
         int col = GridPane.getColumnIndex((Node) e.getSource());
      
         String direction = "";
           
         if(row == 0 && col == 1){// based on getSource, the direction is logged
            direction = ("Kicked Down");
            System.out.println("Kicked Down"); 
         }  
         else if(row == 1 && col == 2){
            direction = ("Kicked Left");
            System.out.println("Kicked Left");
         }  
         else if(row == 1 && col == 0 ){
            direction = ("Kicked Right");
            System.out.println("Kicked Right");
         }  
         else if(row == 2 && col == 1){
            direction = ("Kicked Up");
            System.out.println("Kicked Up");
         
         } 
         click(direction); //send the direction to the click method
      }
      
   }
 
   public class GamePane extends GridPane {//creates the canvas and methods to toggle the buttons and circle visibility
      private Canvas canvas = new Canvas(80, 80);
      private GraphicsContext gc = canvas.getGraphicsContext2D();
      private boolean ballIsVisible;
      private boolean topIsVisible;
      private boolean bottomIsVisible;
      private boolean leftIsVisible;
      private boolean rightIsVisible;
      private Button topButton = new Button("");
      private Button leftButton = new Button("");
      private Button rightButton = new Button("");
      private Button bottomButton = new Button("");
      
   
      public GamePane() {
      
         canvas = new Canvas(80, 80);
         add(canvas, 1, 1);
      
         topButton.setPrefSize(80, 20);
         topButton.setVisible(topIsVisible);
         add(topButton, 1, 0);
              
         leftButton.setPrefSize(20, 80);
         leftButton.setVisible(leftIsVisible);
         add(leftButton, 0, 1);
         
         rightButton.setPrefSize(20, 80);
         rightButton.setVisible(rightIsVisible);
         add(rightButton, 2, 1);
         
         bottomButton.setPrefSize(80, 20);
         bottomButton.setVisible(bottomIsVisible);
         add(bottomButton, 1, 2);
         
         topButton.setOnAction(buttonListener);
         leftButton.setOnAction(buttonListener);
         rightButton.setOnAction(buttonListener);
         bottomButton.setOnAction(buttonListener);   
      } 
      
      public void draw() {//draws the circles
         GraphicsContext gc = canvas.getGraphicsContext2D();
         gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
         
         if (ballIsVisible) {
            gc.setFill(Color.BLACK);
            gc.fillOval(0, 0, 80, 80);
         }   
         else if (!ballIsVisible){//if the circles are invisible the buttons are set to invisible
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            setTopButtonVisible(false);
            setBottomButtonVisible(false);
            setRightButtonVisible(false);
            setLeftButtonVisible(false);
         }
      }
   
      
      public boolean getCircle(){
         return ballIsVisible;
      }
      
      public void setCircle(boolean in){
         ballIsVisible = in;
         
      }
      public void setTopButtonVisible(boolean isVisible) {
         topIsVisible = isVisible;
         topButton.setVisible(isVisible);
      }
   
      public void setLeftButtonVisible(boolean isVisible) {
         leftIsVisible = isVisible;
         leftButton.setVisible(isVisible);
      }
   
      public void setRightButtonVisible(boolean isVisible) {
         rightIsVisible = isVisible;
         rightButton.setVisible(isVisible);
      }
   
      public void setBottomButtonVisible(boolean isVisible) {
         bottomIsVisible = isVisible;
         bottomButton.setVisible(isVisible);
      }
   }
   
   
   public static void main(String[] args)
   {
      launch(args);
     
   }
}