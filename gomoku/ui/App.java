/**
 * This file is part of the Gomoku project.
 * MOravid Mar 19, 2018
 * "THE TEA-WARE LICENSE":
 * You can do whatever the 'beep' you want with this stuff.
 * If we meet some day, and you think this is worth it
 * you can make me a cup of tea in return. 
 */
package gomoku.ui;

import gomoku.core.Player;
import gomoku.core.model.Grid;
import gomoku.core.model.Spot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

	private Grid gameGrid = new Grid();

	private Player Popeye = new Player("Mr. Green", Color.GREEN);

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gomoku Game!");

		Canvas playGround = new Borad(this.gameGrid, this::makeMove); // In Java 8, ceci is the equivalent of
																			// passing a ref to a methode (lambda exp et
																			// InnerClasse)
		BorderPane root = new BorderPane();
		root.setCenter(playGround);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void makeMove(Spot place) {
		gameGrid.placeStone(place.x, place.y, getCurrentPlayer());
	}

	private Player getCurrentPlayer() {
		return Popeye;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
