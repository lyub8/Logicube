import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {
	private GridPane root;
	private Button[][] grid;
	private int size = 5;
	private Stage stage;
	private int[][] matrix;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Game() {
		Matrix mx = new Matrix(size);
		matrix = mx.getMatrix();
		root = new GridPane();
	    grid = new Button[size][size];
	}
	
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
	    for(int y=0; y<size; y++) {
	    	for(int x=0; x<size; x++) {
	                Random rand = new Random(); 
	                
	                grid[x][y] = new Button();   
	                grid[x][y].setText(String.valueOf(matrix[x][y])); 
	                grid[x][y].setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {
	                        System.out.println("yeheei");
	                    }
	                });
	                root.add(grid[x][y], y, x);
	            }
	    }        

	    Scene scene = new Scene(root, 500, 500);
	    stage.setTitle("Random Binary Matrix (JavaFX)");
	    stage.setScene(scene);
	    stage.show();
	}
}
