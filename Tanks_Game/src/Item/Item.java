package Item;

import Entity.Creature.Enemy;
import Entity.Entity;
import Game.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    public static Item[] items = new Item[256];
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    protected int x, y, count;
    protected boolean pickedUp = false;
    protected Rectangle bounds; //zona de coliziune a obiectului

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;
        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        items[id] = this;
    }

    //Verifică dacă obiectul a fost intersectat cu jucătorul sau cu alte entități, daca este colectat
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
                }
            }
    }


