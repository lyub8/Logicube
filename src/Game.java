import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Game extends Application {
	private GridPane root;
	private Button[][] grid;
	private int size = 5;
	private Stage stage;
	private int[][] matrix;
	private int[][] solution;
	private int[][] play;
	private Matrix mx;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Game() {
		mx = new Matrix(size);
		matrix = mx.getMatrix();
		solution = mx.getSolution();
		play = givePlayMatrix();
		root = new GridPane();
	    grid = new Button[size+1][size+1];
	}
	
	private int[][] givePlayMatrix() {
		int[][] test = new int[size][size];
		
		for(int x=0; x<size; x++) {
	    	for(int y=0; y<size; y++) {
	    		test[x][y] = matrix[x][y];
	    	}
	    }
		return test;
	}
	
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
	    for(int y=0; y<size; y++) {
	    	for(int x=0; x<size; x++) {
	                grid[x][y] = new Button();
	                grid[x][y].setMaxSize(50, 50);
	                grid[x][y].setStyle(active());
	                grid[x][y].setText(String.valueOf(matrix[x][y])); 
	                final int a = x, b = y;
	                grid[a][b].setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {
	                    	if (grid[a][b].getStyle().contains("#ff4500")) {
	                    		grid[a][b].setStyle(disabled());
	                    		play[a][b] = 0;
	                    	} else {
	                    		grid[a][b].setStyle(active());
	                    		play[a][b] = matrix[a][b];
	                    	}
	                    	
	                    Pair<Boolean, Boolean> check = checkSums(a, b);
	                    if (check.getKey()) 
	                    	 grid[a][size].setStyle(correctSum());
	                    else 
	                    	grid[a][size].setStyle(incorrectSum());
	                    
	                    if (check.getValue()) 
	                    	 grid[size][b].setStyle(correctSum());
	                    else 
	                    	grid[size][b].setStyle(incorrectSum());
	                    
	                    if (java.util.Arrays.deepEquals(play, solution)) {
	                    	Alert alert = new Alert(AlertType.INFORMATION);
	                    	alert.setTitle("Winner");
	                    	alert.setHeaderText(null);
	                    	alert.setContentText("Congratulations! You solved the puzzle :)");
	                    	alert.showAndWait();
	                    	System.exit(0);
	                    } else 
	                    	mx.print(play);
	                    
	                }});
	                root.add(grid[x][y], y, x);
	                root.setMargin(grid[x][y], new Insets(10));
	            }
	    }        

	    BorderPane border = new BorderPane();
	    border.setStyle("-fx-background-color: black;");
	    Label top = new Label("			Press any number to activate or disable it.");
	    top.setFont(new Font("Courier", 22));
	    top.setTextFill(Color.rgb(154,205,50));
	    top.setPadding(new Insets(30));
	    top.setWrapText(true);
	    border.setTop(top);
	    
	    Label bot = new Label("Keep only the numbers needed so every row and column equals their sum.");
	    bot.setFont(new Font("Courier", 22));
	    bot.setTextFill(Color.rgb(154,205,50));
	    bot.setPadding(new Insets(30));
	    bot.setWrapText(true);
	    border.setBottom(bot);
	    
	    border.setLeft(addVBox()); 
	    border.setRight(addVBox()); 
	    border.setCenter(root);
	    root.setPadding(new Insets(10));
	    border.getCenter().setStyle("-fx-alignment: center;");
	    border.getTop().setStyle("-fx-alignment: center;");
	    border.getBottom().setStyle("-fx-alignment: center;");
	    
	    addSums();
	    
	    Scene scene = new Scene(border);
	    stage.setTitle("Logicube");
	    stage.setScene(scene);
	    stage.show();
	}
	
	private VBox addVBox() {
		VBox vbox = new VBox();
	    vbox.setStyle("-fx-background-color: black;");
	    vbox.setMinSize(20, 60);
	    vbox.setPrefSize(100, 300);
	    return vbox;
	}
	
	private void addSums() {
		List<Integer> sums = mx.getSums();
		for(int x=0; x<size; x++) {
             createButton(x, size);
			 createButton(size, x);
             grid[x][size].setText(String.valueOf(sums.get(x)));
             grid[size][x].setText(String.valueOf(sums.get(x+size)));
		}
	}
	
	private void createButton(int a, int b) {
		Button button = new Button();
		button.setMinSize(40, 40);
        button.setStyle(incorrectSum());
        grid[a][b] = button;
        root.add(grid[a][b], b, a);
        root.setMargin(grid[a][b], new Insets(20));
	}
	
	private Pair<Boolean, Boolean> checkSums(int row, int col) {
		boolean rowCheck = false, colCheck = false;
		int sum = 0;
		for (int x=0; x<size; x++) 
			sum += play[row][x];
		if (sum == Integer.parseInt(grid[row][size].getText()))
			rowCheck = true;
		
		sum = 0;
		for (int y=0; y<size; y++) 
			sum += play[y][col];
		if (sum == Integer.parseInt(grid[size][col].getText()))
			colCheck = true;
	
		return new Pair(rowCheck, colCheck);
	}
	
	private String active() {
		return   "-fx-border-width: 3; "+
                 "-fx-border-color: #ff4500; "+
                 "-fx-background-radius: 500; "+
                 "-fx-border-radius: 500; "+
                 "-fx-background-color: transparent; "+
                 "-fx-font-size: 30px; "+
                 "-fx-text-fill: #ff4500; ";
	}
	
	private String disabled() {
		return   "-fx-border-width: 3; "+
	             "-fx-border-color: #1B1B1B; "+
	             "-fx-background-radius: 500; "+
	             "-fx-border-radius: 500; "+
	             "-fx-background-color: transparent; "+
	             "-fx-font-size: 30px; "+
                 "-fx-text-fill: #1B1B1B; ";
	}
	
	private String correctSum() {
		return  "-fx-border-width: 2; "+
         		"-fx-border-color: #9acd32; "+
         		"-fx-background-color: transparent; "+
                "-fx-font-size: 28px; "+
                "-fx-text-fill: #9acd32; "+
                "-fx-padding: 2;";
	}
	
	private String incorrectSum() {
		return  "-fx-border-width: 2; "+
        		"-fx-border-color: #a9a9a9; "+
        		"-fx-background-color: transparent; "+
                "-fx-font-size: 28px; "+
                "-fx-text-fill: #a9a9a9; "+
                "-fx-padding: 2;";
	}
}
