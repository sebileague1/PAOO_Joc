package Bullet;
import Entity.AnimationObjects.AnimationObject;
import Entity.Creature.Enemy;
import Entity.Creature.Shooter;
import Entity.Entity;
import Entity.Types.Bullet_Appereance_Offset;
import Entity.Types.Bullet_Types;
import Entity.Types.Bullet_Appereance_Offset.Bullet_Appereance_Types;
import Game.Handler;
import Tile.Tile;
import static Entity.Types.Bullet_Types.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet  extends Entity {
    private final int image_size = 20;
    private float xMove, yMove;
    private float speed;
    private int firepower;
    private boolean enemy_fire = true;
    BufferedImage current_image;
    protected Bullet_Types.Bullet_Type bullet_type;
    protected Shooter hostShooter;

    public Bullet(Handler handler, Shooter shooter,  Bullet_Type bullet_type, Bullet_Appereance_Types appereance_type){
        super(handler, shooter.getX() + shooter.getBoundsX() , shooter.getY() + shooter.getBoundsY(), 10, 10);
        this.bullet_type = bullet_type;
        this.firepower = Bullet_Types.FirePower(bullet_type);
        BufferedImage [] images = Bullet_Types.BulletImages(bullet_type);
        this.enemy_fire = shooter.isEnemyBullet();
        this.speed = Bullet_Types.BulletSpeed(bullet_type);
        bounds.x = image_size/4;
        bounds.y = image_size/4;
        xMove = 0;
        yMove = 0;
        hostShooter = shooter;
        int numarator = Bullet_Appereance_Offset.getNumarator(appereance_type);
        int numitor = Bullet_Appereance_Offset.getNumitor(appereance_type);

        switch (shooter.getCurrent_direction()){
            case up :
                current_image = images[0];
                yMove = -speed;
                this.x +=  shooter.getBoundsWidth() * numarator/numitor - image_size/2;
                this.y -= image_size;
                break;
            case right:
                current_image = images[1];
                xMove = speed;
                this.y += shooter.getBoundsHeight()*numarator/numitor - image_size/2;
                this.x += shooter.getHeight();
                break;
            case down:
                current_image = images[2];
                yMove = speed;
                this.x += shooter.getBoundsWidth()*(numitor - numarator)/numitor- image_size/2;
                this.y += shooter.getHeight();
                break;
            case left:
                current_image = images[3];
                xMove = -speed;
                this.y +=  shooter.getBoundsHeight()*(numitor - numarator)/numitor - image_size/2;
                this.x -= image_size;

                break;

        }
    }

    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for (Bullet b : handler.getWorld().getBulletManager().getBullets()){
            if (b.equals(this) ){
                continue;
            }

            if (b.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                b.setActive(false);
                return true;
            }
        }


        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
            if (e.equals(hostShooter) ){
                continue;
            }

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                if (!(e instanceof Enemy && this.enemy_fire)){
                    e.hurt(firepower);
                }
                return true;
            }
        }
        return false;
    }

    public void die() {
        handler.getWorld().getAnimationManager().addAnimationObject(new AnimationObject(handler, x ,y, 32, 32, this.getBoundsWidth(), this.getBoundsHeight()));
    }

    public void tick(){
        if (active) {
            move();
            if (checkEntityCollisions(xMove, 0f)) {
                active = false;
            } else if (checkEntityCollisions(0f, yMove)) {
                active = false;
            }
        }
    }

    public void render(Graphics g){

        g.drawImage(current_image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 20, 20, null);

    }

    public void move(){
        moveX();
        moveY();
    }

    public void moveX(){
        if (xMove >0){
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)){
                x += xMove;
            }
            else{
                active = false;
            }
        }
        else if (xMove < 0){
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)){
                x += xMove;
            }
            else{
                active = false;
            }
        }
    }

    public void moveY(){
        if (yMove < 0){
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
            else{
                active = false;
            }
        }
        else if (yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
            else{
                active = false;
            }
        }

    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isNotTraverseble();
    }

}
