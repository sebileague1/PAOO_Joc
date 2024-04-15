package Entity.Static_Entity;
import Game.Assets;
import Game.Handler;
import Tile.*;
import java.awt.*;

public class Tree extends Static_Entity {
    public Tree(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        this.health = 100;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }
}
