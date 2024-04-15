package Entity.Creature;
import Entity.Current_Direction;
import Game.*;
import Item.Item;
import Entity.Types.Entity_Types;
import java.awt.*;
import java.util.Random;


public class Enemy extends Shooter{
    protected long lastMoveTimer, moveCoolDown = 2000, moveTimer = moveCoolDown;

    public Enemy(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type)
    {
        super(handler, x, y, tank_type);
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

    @Override
    public void addItem(Item item) {
    }

    @Override
    public void render(Graphics g) {
        drawHealth(g);
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }


    protected void drawHealth(Graphics g){
        Font fnt1 = new Font("arial", Font.BOLD, 15);
        String text = Integer.toString(health);
        int text_width = g.getFontMetrics().stringWidth(text);
        g.setColor(Color.red);
        g.setFont(fnt1);
        g.drawString(text, (int) (x - handler.getGameCamera().getxOffset() + bounds.x + bounds.width/2 - text_width/2), (int) (y - handler.getGameCamera().getyOffset()));
    }


}
