import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Game extends Application {
	private GridPane root;
	private Button[][] grid;
	private int size = 4;
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
	    grid = new Button[size][size];
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
	                Random rand = new Random(); 
	                grid[x][y] = new Button();
	                grid[x][y].setShape(new Circle(5));
	                grid[x][y].setStyle(
	                		"-fx-border-width: 3; "+
	                		"-fx-border-color: #ff4500; "+
	                		"-fx-background-color: transparent; "+
	                        "-fx-font-size: 38px; "+
	                        "-fx-text-fill: #ff4500; "
	                );

	                grid[x][y].setText(String.valueOf(matrix[x][y])); 
	                final int a = x, b = y;
	                grid[a][b].setOnAction(new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(ActionEvent event) {
	                    	System.out.println(matrix[a][b]);
	                    	if (grid[a][b].getStyle().contains("#ff4500")) {
	                    		grid[a][b].setStyle( 
	                    			 "-fx-border-width: 3; "+
	                	             "-fx-border-color: #1B1B1B; "+
	                    			 "-fx-background-color: transparent; "+
	                    			 "-fx-font-size: 38px; "+
	             	                 "-fx-text-fill: #1B1B1B; ");
	                    		play[a][b] = 0;
	                    } else {
	                    	 grid[a][b].setStyle( 
	                    			 "-fx-border-width: 3; "+
	             	                 "-fx-border-color: #ff4500; "+
	                    			 "-fx-background-color: transparent; "+
	             	                 "-fx-font-size: 38px; "+
	             	                 "-fx-text-fill: #ff4500; ");
	                    	 play[a][b] = matrix[a][b];
	                    }
	                    if (java.util.Arrays.deepEquals(play, solution)) {
	                    	Alert alert = new Alert(AlertType.INFORMATION);
	                    	alert.setTitle("Winner");
	                    	alert.setHeaderText(null);
	                    	alert.setContentText("Congratulations! You solved the puzzle :)");
	                    	alert.showAndWait();
	                    } else {
	                    	mx.print(play);
	                    }
	                    
	                }});
	                root.add(grid[x][y], y, x);
	                root.setMargin(grid[x][y], new Insets(10));
	            }
	    }        

	    BorderPane border = new BorderPane();
	    border.setStyle("-fx-background-color: black;");
	    border.setTop(addHBox());
	    border.setBottom(addHBox());
	    border.setLeft(addVBox()); 
	    border.setRight(addVBox()); 
	    border.setCenter(root);
	    root.setPadding(new Insets(100));
	    
	    Scene scene = new Scene(border);
	    stage.setTitle("Logicum");
	    stage.setScene(scene);
	    stage.show();
	}
	
	private HBox addHBox() {
		HBox hbox = new HBox();
	    hbox.setStyle("-fx-background-color: black;");
	    hbox.setPrefSize(500, 100);
	    return hbox;
	}
	
	private VBox addVBox() {
		VBox vbox = new VBox();
	    vbox.setStyle("-fx-background-color: black;");
	    vbox.setPrefSize(100, 300);
	    return vbox;
	}
}
