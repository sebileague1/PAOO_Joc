package Entity.Creature;
import Entity.Current_Direction;
import Game.*;
import Bullet.Bullet;
import Item.Item;
import Entity.Types.Entity_Types;
import java.awt.*;
import java.util.Random;
import static Entity.Types.Bullet_Appereance_Offset.Bullet_Appereance_Types.*;

public class Enemy extends Shooter{
    protected long lastMoveTimer, moveCoolDown = 2000, moveTimer = moveCoolDown;

    public Enemy(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type)
    {
        super(handler, x, y, tank_type);
        this.moveCoolDown = Entity_Types.MoveCoolDown(this.tank_type);
    }

    @Override
    public void die(){
        super.die();
        Random rand = new Random();
        int n = rand.nextInt(100);
        GenerateItemByTankType(this.tank_type, n);
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    private void getInput(){

        Random rand = new Random();
        int n = rand.nextInt(2);
        if (n == 0){
            checkAttacks();
        }
        moveTimer += System.currentTimeMillis() - lastMoveTimer;
        lastMoveTimer = System.currentTimeMillis();
        if (moveTimer < moveCoolDown){
            return;
        }
        xMove = 0;
        yMove = 0;
        n = rand.nextInt(110);
        if (n < 25) {
            yMove = -speed;
            current_direction = Current_Direction.up;
            set_Bounds_Dimension(true);
        }else
        if (n < 50) {
            yMove = speed;
            current_direction = Current_Direction.down;
            set_Bounds_Dimension(true);
        } else

        if (n < 75) {
            xMove = -speed;
            current_direction = Current_Direction.left;
            set_Bounds_Dimension(false);
        } else

        if (n < 100) {
            xMove = speed;
            current_direction = Current_Direction.right;
            set_Bounds_Dimension(false);
        }
        else if (n < 110){
            xMove = 0;
            yMove = 0;
        }
        moveTimer = 0;
    }

    protected void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }
        if (tank_type == Entity_Types.Tank_Type.tank_4){
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, quarter));
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, threequarter));
        }
        else {
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, middle));
        }
        attackTimer = 0;
    }

    @Override
    public void addItem(Item item) {
        switch(item.getId()){
            case 0:
            case 1:
                this.health += 50;
                break;
            case 2:
                this.tank_type = Entity_Types.Tank_Type.tank_4;
                int temp_health = this.health;
                init();
                if (this.health < temp_health){
                    this.health = temp_health + 50;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        drawHealth(g);
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    private void GenerateItemByTankType(Entity_Types.Tank_Type tank_type, int n){
        switch(tank_type){
            case tank_1:
                GenerateItem(n, 50, 45, 5);
                break;
            case tank_2:
                GenerateItem(n, 40, 45, 15);
                break;
            case tank_3:
                GenerateItem(n, 30, 45, 25);
                break;
            default:
                GenerateItem(n, 20, 45, 35);
                break;
        }
    }

    protected void drawHealth(Graphics g){
        Font fnt1 = new Font("arial", Font.BOLD, 15);
        String text = Integer.toString(health);
        int text_width = g.getFontMetrics().stringWidth(text);
        g.setColor(Color.red);
        g.setFont(fnt1);
        g.drawString(text, (int) (x - handler.getGameCamera().getxOffset() + bounds.x + bounds.width/2 - text_width/2), (int) (y - handler.getGameCamera().getyOffset()));
    }

    private void GenerateItem(int n, int probability_coin, int probability_health, int probability_upgrade){
        if (n <probability_coin){
            handler.getWorld().getItemManager().addItem(Item.gold_chest.createNew((int)x, (int)y));
        }
        else if (n < probability_health + probability_coin){
            handler.getWorld().getItemManager().addItem(Item.health_chest.createNew((int)x, (int)y));
        }
        else if (n < probability_health + probability_coin + probability_upgrade){
            handler.getWorld().getItemManager().addItem(Item.upgraded_chest.createNew((int)x, (int)y));
        }
    }

}
