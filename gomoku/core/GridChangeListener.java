/**
 * This file is part of the Gomoku project.
 * MOravid Mar 19, 2018
 * "THE TEA-WARE LICENSE":
 * You can do whatever the 'beep' you want with this stuff.
 * If we meet some day, and you think this is worth it
 * you can make me a cup of tea in return. 
 */
package gomoku.core;

import gomoku.core.model.Spot;

public interface GridChangeListener {
	/** Called when a stone is placed. */
	void stonePlaced(Spot place);

	/** Called when the game is over. */
	void gameOver(Player winner);
}
