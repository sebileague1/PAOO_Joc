package Entity;

import Entity.Creature.Player;
import Game.*;
import java.awt.*;


public abstract class Entity {
    public static final int DEFAULT_HEALTH = 40;


    protected float x, y;
    protected Handler handler;
    protected boolean active = true;
    protected Rectangle bounds;
    protected int health = DEFAULT_HEALTH;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract void die();

    public void hurt(int amount){
        if (health > 0 ) {
            health -= amount;
            if (health <= 0) {
                active = false;
                die();
            }
        }

        if (this instanceof Player){
            System.out.println("Health" + health);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    protected int width, height;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0,0, width, height);
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
            if (e.equals(this)){
                continue;
            }

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public int getBoundsX() {
        return bounds.x;
    }

    public int getBoundsY() {
        return bounds.y;
    }

    public int getBoundsWidth() {
        return bounds.width;
    }

    public int getBoundsHeight() {
        return bounds.height;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
