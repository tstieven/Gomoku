package gomoku.aspect;

import gomoku.core.Player;
import javafx.scene.paint.Color;

public aspect AdversaireJ {

    private Player bluePlayer = new Player("Blue player", Color.BLUE);

    private Player redPlayer = new Player("Red Player", Color.RED);

    private boolean player = false;

    Player around() : call(Player getCurrentPlayer()) {
        if (!player) {
            player = true;
            return bluePlayer;
        } else {
            player = false;
            return redPlayer;
        }
    }
}
