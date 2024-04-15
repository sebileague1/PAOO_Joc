package Entity.Static_Entity;

import Game.Assets;
import Game.Handler;
import Tile.Tile;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Town extends Static_Entity {
    protected BufferedImage image;

    public Town(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        this.health = 100;
        this.image = Assets.townGrass;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }
}