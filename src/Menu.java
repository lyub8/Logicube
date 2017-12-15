import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Menu extends Application {
	private Stage stage;

	public Menu() {

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		stage = primaryStage;
		StackPane stack = new StackPane();
		stack.setStyle("-fx-background-color: black; -fx-alignment: center;");
		Button start = new Button("start");
		start.setStyle("-fx-font-family: monospace; "+ "-fx-font-weight: bold; " + "-fx-background-radius: 500; " + "-fx-border-radius: 500; " + "-fx-border-width: 3; " + "-fx-border-color: #9acd32; "
				+ "-fx-background-color: transparent; " + "-fx-font-size: 40px; " + "-fx-text-fill: #9acd32; "
				+ "-fx-padding: 10 100; ");
		
		start.setOnMouseEntered(e -> start.setStyle("-fx-font-family: monospace; "+ "-fx-font-weight: bold; " + "-fx-background-radius: 500; " + "-fx-border-radius: 500; "+ "-fx-border-width: 3; " + "-fx-border-color: black; "
						+ "-fx-background-color: #9acd32; " + "-fx-font-size: 40px; " + "-fx-text-fill: black; "
						+ "-fx-padding: 10 100; "));
		
		start.setOnMouseExited(e -> start.setStyle("-fx-font-family: monospace; "+ "-fx-font-weight: bold; " + "-fx-background-radius: 500; "+ "-fx-border-radius: 500; "+ "-fx-border-width: 3; " + "-fx-border-color: #9acd32; "
				+ "-fx-background-color: transparent; " + "-fx-font-size: 40px; " + "-fx-text-fill: #9acd32; "
				+ "-fx-padding: 10 100; "));
		
		start.setOnAction(e -> startGame());
		
		stack.getChildren().add(start);

		Scene scene = new Scene(stack);
		stage.setTitle("Logicube");
		stage.setWidth(400);
		stage.setHeight(300);
		stage.setScene(scene);
		stage.show();
	}
	
	private void startGame() {
		Game g = new Game();
		g.start(new Stage());
		stage.close();
	}

}
