/**
 * This file is part of the Gomoku project.
 * MOravid Mar 19, 2018
 * "THE TEA-WARE LICENSE":
 * You can do whatever the 'beep' you want with this stuff.
 * If we meet some day, and you think this is worth it
 * you can make me a cup of tea in return. 
 */
package gomoku.core.model;

import gomoku.core.Player;

public class Spot {

	/** position */
	public final int x, y;

	private Player occupant;

	Spot(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Remove the occupant of this place. */
	void clear() {
		occupant = null;
	}

	/** Set the occupant of this place. */
	void setOccupant(Player player) {
		this.occupant = player;
	}

	/** Return the owner of the stone placed on this place. */
	public Player getOccupant() {
		return occupant;
	}

	public boolean isEmpty() {
		return occupant == null;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
}
