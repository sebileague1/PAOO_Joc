package Item;
import Bullet.Bullet;
import Entity.Creature.Enemy;
import Entity.Entity;
import Game.Assets;
import Game.Handler;
import Tile.Tile;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    public static Item[] items = new Item[256];
    public static Item gold_chest = new Item(Assets.gold_chest, "Gold_Chest", 0);
    public static Item health_chest = new Item(Assets.health_chest, "Health_Chest", 1);
    public static Item upgraded_chest = new Item(Assets.upgrade_chest, "Upgrade_Chest", 2);

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;


    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    protected int x, y, count;

    protected boolean pickedUp = false;

    protected Rectangle bounds;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;
        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;

    }

    public synchronized void tick(){
            if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
                pickedUp = true;
                handler.getWorld().getEntityManager().getPlayer().addItem(this);
            } else {
            for (Entity e : handler.getWorld().getEntityManager().getEntities()){
                if (e.getCollisionBounds(0f, 0f).intersects(bounds)){
                    if (e instanceof Enemy){
                        ((Enemy) e).addItem(this);
                    }
                    pickedUp = true;
                }
            }
                for (Bullet b : handler.getWorld().getBulletManager().getBullets()) {
                    if (b.getCollisionBounds(0f, 0f).intersects(bounds)) {
                        b.setActive(false);
                    }
                }
            }
    }

    public void render(Graphics g){
        if (handler == null){
            return;
        }
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x + Tile.TILEWIDTH/2 - bounds.width/2;
        this.y = y + Tile.TILEHEIGHT/2 - bounds.height/2;
        bounds.x = this.x;
        bounds.y = this.y;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
}
