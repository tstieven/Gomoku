package gomoku.aspect;

import gomoku.core.Player;
import javafx.scene.paint.Color;

public aspect AdversaireJ {

    private Player bluePlayer = new Player("Green player", Color.BLUE);

    private Player redPlayer = new Player("Blue Player", Color.RED);

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
