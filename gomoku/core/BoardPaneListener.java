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

public interface BoardPaneListener {
	/**
	 * Called when an unoccupied place is selected.
	 * 
	 * @param place
	 *            The selected place
	 */
	void placeSelected(Spot place);

}
