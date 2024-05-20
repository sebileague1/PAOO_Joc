package Entity.Creature;
import AudioPlayer.AudioPlayer;
import Entity.AnimationObjects.AnimationObject;
import Entity.Current_Direction;
import Entity.Types.Bullet_Types;
import Entity.Types.Entity_Types;
import Game.Handler;
import Item.Item;
import java.awt.image.BufferedImage;
import static Entity.Dimensions.Tank_Type_Bounds_Dimensions.*;
import static Entity.Types.Entity_Types.TankSpeed;


public abstract class Shooter extends Creature{
    protected BufferedImage[] images;
    protected final int const_speed = 5;
    //Atack timer
    protected long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;
    protected Current_Direction current_direction;
    protected Entity_Types.Tank_Type tank_type;
    protected Bullet_Types.Bullet_Type bullet_type;
    protected boolean EnemyBullet = true;

    public Shooter(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        current_direction = Current_Direction.down;
        this.tank_type = tank_type;
        init();

    }

    @Override
    public void die(){
        handler.getWorld().getAnimationManager().addAnimationObject(new AnimationObject(handler, x ,y, 64, 64, this.getBoundsWidth(), this.getBoundsHeight()));
        AudioPlayer audio = new AudioPlayer("/sound/tank/explosion.wav");
        audio.play();
    }

    protected void init(){
        this.attackCooldown = Entity_Types.TankAttackTime(this.tank_type);
        this.speed = TankSpeed(this.tank_type);
        int healthToAdd = Entity_Types.TankHealth(this.tank_type);
        if (healthToAdd > this.health){
            this.health = healthToAdd;
        }
        this.bullet_type = Entity_Types.GetBulletType(this.tank_type);
        bounds.x = GetBoundsX(this.tank_type);
        bounds.y = GetBoundsY(this.tank_type);
        bounds.width = GetBoundsWidth(this.tank_type);
        bounds.height = GetBoundsHeight(this.tank_type);
        images = Entity_Types.TankImages(this.tank_type);
    }

    protected void set_Bounds_Dimension(boolean vertical){
        if(vertical){
            bounds.x = GetBoundsX(this.tank_type);
            bounds.y = GetBoundsY(this.tank_type);
            bounds.width = GetBoundsWidth(this.tank_type);
            bounds.height = GetBoundsHeight(this.tank_type);
        }
        else{
            bounds.x = GetBoundsY(this.tank_type);
            bounds.y = GetBoundsX(this.tank_type);
            bounds.width = GetBoundsHeight(this.tank_type);
            bounds.height = GetBoundsWidth(this.tank_type);

        }
    }

    public boolean isEnemyBullet() {
        return EnemyBullet;
    }

    protected abstract void checkAttacks();

    protected BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return images[3];
        }
        else if(xMove > 0){
            return images[1];
        }
        else if (yMove < 0){
            return images[0];
        }
        else if (yMove > 0){
            return images[2];
        }
        else{
            if (current_direction == Current_Direction.up){
                return images[0];
            }
            if (current_direction == Current_Direction.right){
                return  images[1];
            }
            if (current_direction == Current_Direction.down){
                return  images[2];
            }
            return images[3];
        }
    }

    public Current_Direction getCurrent_direction() {
        return current_direction;
    }

    abstract public void addItem(Item item);

}
