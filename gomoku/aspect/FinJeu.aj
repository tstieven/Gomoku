package gomoku.aspect;

import gomoku.core.model.*;
import gomoku.core.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

privileged public aspect FinJeu {

    private Boolean gameOver = false;
    private GraphicsContext gContext = null;
    private List<Spot> winningRow = new ArrayList<>(0);


    public List<Spot> Grid.getWin(){

        return this.winningStones;
    }

    after(Grid grid) : call (void notifyGameOver(Player)) && target (grid) && within(Grid){
        winningRow = grid.getWin();

    }

    pointcut callDrawStone(GraphicsContext gContext, Spot spot) : call(void drawStone(GraphicsContext, Spot)) && args(gContext, spot);


    after(GraphicsContext gContext, Spot p) : callDrawStone(gContext, p) {
        this.gContext = gContext;
    }


    after(Player winner) : call(void notifyGameOver(Player)) && args(winner) {
        double spotSize = 20;
        this.gameOver = true;
        for (Spot stone : this.winningRow) {
            this.gContext.setFill(Color.YELLOW);
            double x = spotSize + stone.x * spotSize; // center x
            double y = spotSize + stone.y * spotSize; // center y
            double r = spotSize / 2; // radius
            this.gContext.fillOval(x - r, y - r, r * 2, r * 2);
        }
    }

    void around(int x, int y, Player player) : call(void placeStone(int, int, Player)) && args(x, y, player) {
        if(!gameOver) {
            proceed(x,y,player);
        }
    }
}
