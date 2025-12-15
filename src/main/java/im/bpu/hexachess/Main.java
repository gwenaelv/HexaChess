package im.bpu.hexachess;

import im.bpu.hexachess.model.Board;
import im.bpu.hexachess.ui.HexPanel;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Board board = new Board();
		HexPanel hexPanel = new HexPanel(board);
		Button btnRestart = new Button("Restart");
		Button btnRewind = new Button("Rewind");
		HBox btnBox = new HBox(16);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(btnRestart, btnRewind);
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(hexPanel, 0, 0);
		gridPane.add(btnBox, 0, 1);
		btnRestart.setOnAction(ev -> hexPanel.restart());
		btnRewind.setOnAction(ev -> hexPanel.rewind());
		Scene scene = new Scene(gridPane);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}