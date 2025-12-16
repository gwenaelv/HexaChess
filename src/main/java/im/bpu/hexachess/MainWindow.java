package im.bpu.hexachess;

import im.bpu.hexachess.model.Board;
import im.bpu.hexachess.ui.HexPanel;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class MainWindow {
	private Board board;
	private HexPanel hexPanel;
	@FXML private Canvas canvas;
	@FXML
	private void initialize() {
		board = new Board();
		hexPanel = new HexPanel(canvas, board);
	}
	@FXML
	private void restart() {
		hexPanel.restart();
	}
	@FXML
	private void rewind() {
		hexPanel.rewind();
	}
}