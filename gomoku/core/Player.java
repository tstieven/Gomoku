/**
 * This file is part of the Gomoku project.
 * MOravid Mar 20, 2018
 * "THE TEA-WARE LICENSE":
 * You can do whatever the 'beep' you want with this stuff.
 * If we meet some day, and you think this is worth it
 * you can make me a cup of tea in return. 
 */
package gomoku.core;

import javafx.scene.paint.Color;

public class Player {

	/** Color of this player's stone. */
	private Color color;
	/** Name of this player. */
	private final String name;

	/**
	 * Create a new player with given name. The created player will have stones of
	 * the given color.
	 */
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	/** Returns this player's stone color. */
	public Color getColor() {
		return color;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

}
