import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game extends Application {
	private GridPane root;
	private Button[][] grid;
	private int size = 15;
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

	    BorderPane border = new BorderPane();
	    border.setStyle("-fx-background-color: #9acd32;");
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
	    hbox.setStyle("-fx-background-color: #6495ed;");
	    hbox.setPrefSize(500, 100);
	    return hbox;
	}
	
	private VBox addVBox() {
		VBox vbox = new VBox();
	    vbox.setStyle("-fx-background-color: #6495ed;");
	    vbox.setPrefSize(100, 300);
	    return vbox;
	}
}
