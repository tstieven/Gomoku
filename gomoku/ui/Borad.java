/**
 * This file is part of the Gomoku project.
 * MOravid Mar 19, 2018
 * "THE TEA-WARE LICENSE":
 * You can do whatever the 'beep' you want with this stuff.
 * If we meet some day, and you think this is worth it
 * you can make me a cup of tea in return. 
 */
package gomoku.ui;

import gomoku.core.BoardPaneListener;
import gomoku.core.GridChangeListener;
import gomoku.core.Player;
import gomoku.core.model.Grid;
import gomoku.core.model.Spot;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Borad extends Canvas implements GridChangeListener {

	/** Number of pixels between horizontal/vertical lines of the board. */
	private double spotSize = 20;
	private Grid boardModel;

	public Borad(Grid b, BoardPaneListener lstnr) {
		super(20 * 15, 20 * 15);
		this.boardModel = b;
		b.addBoardChangeListener(this);

		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				Spot place = locatePlace((int) me.getX(), (int) me.getY());
				if (place != null && place.isEmpty()) {
					lstnr.placeSelected(place);

				}
			}
		});
		drawGrid();
	}

	private void drawGrid() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.ANTIQUEWHITE);
		gc.setStroke(Color.BLACK);
		// number of v/h lines including boarder lines
		final int lines = boardModel.size() + 2;

		// paint background
		gc.fillRect(0, 0, spotSize * (lines - 1), spotSize * (lines - 1));

		// draw vertical lines
		double x = 0; // placeSize;
		for (int i = 0; i < lines; i++) {
			gc.strokeLine(x, 0, x, spotSize * (lines - 1));
			x += spotSize;
		}

		// draw horizontal lines
		double y = 0; // placeSize;
		for (int i = 0; i < lines; i++) {
			gc.strokeLine(0, y, spotSize * (lines - 1), y);
			y += spotSize;
		}
	}

	private void drawStones() {

		GraphicsContext gc = this.getGraphicsContext2D();
		for (Spot s : boardModel.lstSpot()) {
			if (!s.isEmpty()) {
				drawStone(gc, s);
			}
		}
	}

	private void drawStone(GraphicsContext gc, Spot p) {
		gc.setFill((p.getOccupant()).getColor());

		double x = spotSize + p.x * spotSize; // center x
		double y = spotSize + p.y * spotSize; // center y
		double r = spotSize / 2; // radius
		gc.fillOval(x - r, y - r, r * 2, r * 2);
	}

	private Spot locatePlace(double x, double y) {
		// Panel
		//
		// X---X---X-- O: placeable
		// |PS | | X: Not placeable
		// X---O---O--
		//

		final double boardSize = boardModel.size();
		final double boarder = spotSize; // * 1;
		// recognize R pixels from an intersection
		final double R = spotSize / 2 - 2;

		// off board?
		if (x < boarder - R || y < boarder - R || x > spotSize * (boardSize + 1) - (spotSize - R)
				|| y > spotSize * (boardSize + 1) - (spotSize - R)) {
			return null;
		}

		double px = 0;
		// -+-R)--(-R-+-
		double dx = (x - boarder) % spotSize;
		if (dx <= R) {
			px = (x - boarder) / spotSize;
		} else if (dx >= spotSize - R) {
			px = (x - boarder) / spotSize + 1;
		} else {
			return null;
		}

		double py = 0;
		double dy = (y - boarder) % spotSize;
		if (dy <= R) {
			py = (y - boarder) / spotSize;
		} else if (dy >= spotSize - R) {
			py = (y - boarder) / spotSize + 1;
		} else {
			return null;
		}
		return boardModel.at((int) px, (int) py);
	}

	@Override
	public void stonePlaced(Spot place) {
		drawStones();
	}

	@Override
	public void gameOver(Player winner) {
		System.out.println("bingoooo! the " + winner.getName() + " wins");
	}
}
