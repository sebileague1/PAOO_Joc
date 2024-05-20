package Entity.Static_Entity;
import Game.Assets;
import Game.Handler;
import Item.Item;
import Tile.*;
import java.awt.*;
import java.util.Random;

public class Tree extends Static_Entity {
    public Tree(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        this.health = 100;
    }

    @Override
    public void die() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        if (n < 15) {
            handler.getWorld().getItemManager().addItem(Item.gold_chest.createNew((int) x, (int) y));
        } else if (n < 30) {
            handler.getWorld().getItemManager().addItem(Item.health_chest.createNew((int) x, (int) y));
        } else if (n < 40) {
            handler.getWorld().getItemManager().addItem(Item.upgraded_chest.createNew((int) x, (int) y));
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
