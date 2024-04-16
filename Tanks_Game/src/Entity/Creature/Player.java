package Entity.Creature;

import Entity.Current_Direction;
import Game.*;
import Item.Item;
import java.awt.*;
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
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this); //centrează camera jocului pe jucător
    }

    //verifică tastele apăsate de utilizator,determina directia in care se misca jucatorrul
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

    @Override
    public void addItem(Item item) {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    public void setNumberOfCoins(int amount) {
        points = amount;
    }

}
