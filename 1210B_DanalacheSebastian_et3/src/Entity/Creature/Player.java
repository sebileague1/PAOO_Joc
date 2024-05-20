package Entity.Creature;
import AudioPlayer.AudioPlayer;
import Entity.Current_Direction;
import Entity.Types.Bullet_Types;
import Entity.Types.Entity_Types;
import Game.*;
import Bullet.Bullet;
import Item.Item;
import State.State;

import java.awt.*;
import static Entity.Types.Bullet_Appereance_Offset.Bullet_Appereance_Types.*;
import static Entity.Types.Entity_Types.*;

public class Player extends Shooter{
    static private int points;
    static private int upgrade_level = 0;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, GetTankByLevel(upgrade_level));
        current_direction = Current_Direction.down;
        init();
        EnemyBullet = false;
    }

    @Override
    public void die() {
        super.die();
        handler.getGame().gameState.getAudioPlayer().close();
        State.setState(handler.getGame().loseState);
        handler.getGame().loseState.getAudioPlayer().play();
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        checkAttacks();

    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
            current_direction = Current_Direction.up;
            set_Bounds_Dimension(true);
            return;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            current_direction = Current_Direction.down;
            set_Bounds_Dimension(true);
            return;
        }

        if (handler.getKeyManager().left) {
            xMove = -speed;
            current_direction = Current_Direction.left;
            set_Bounds_Dimension(false);
            return;
        }

        if (handler.getKeyManager().right) {
            xMove = speed;
            current_direction = Current_Direction.right;
            set_Bounds_Dimension(false);
        }
    }

    protected void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown){
            return;
        }

        if (handler.getKeyManager().space) {
            if (tank_type == Entity_Types.Tank_Type.player_level_2) {
                AudioPlayer audioPlayer = new AudioPlayer("/sound/bullet/rocket.wav");
                audioPlayer.play();

                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, Bullet_Types.Bullet_Type.bullet_3, middle));
                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, one_seventeenth));
                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, six_seventeenth));
            } else {
                AudioPlayer audioPlayer = new AudioPlayer("/sound/bullet/bullet_1.wav");
                audioPlayer.play();
                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, middle));
            }
        }
        else{
            return;
        }
        attackTimer = 0;
    }

    @Override
    public void addItem(Item item) {
        switch(item.getId()){
            case 0:
                points += 50;
                System.out.println("Item 1 taken");
                break;
            case 1:
                this.health += 30;
                points += 20;
                System.out.println("Item 2 taken");

                break;
            case 2:
                upgrade_level = 1;
                this.tank_type = Tank_Type.player_level_2;
                points += 50;
                init();
                System.out.println("Item 3 taken");

                break;
            default:
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public static void AddPoints(int points){
        Player.points += points;
    }

    public static int GetScore(){
        return Player.points;
    }

    public int getNumberOfCoins() {
        return points;
    }

    public void setNumberOfCoins(int amount) {
        points = amount;
    }

    public void setUpgrade_level(int level){
        upgrade_level = level;
    }
}
