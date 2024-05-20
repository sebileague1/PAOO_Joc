package State;
import Entity.Types.Entity_Types;
import Game.Assets;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;
import java.awt.*;
import static Entity.Types.Entity_Types.*;
import static Entity.Types.Entity_Types.Tank_Type.*;


public class HelpState extends State {
    private UIManager uiManager;

    public HelpState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 140, handler.getGame().getHeight()-100, 280, 50, () -> {
            isUIManagerActive = false;
            if (hasGameStarted){
                handler.getGame().intermediateMenuState.setUIManagerActive(true);
                State.setState(handler.getGame().intermediateMenuState);
            }
            else{
                handler.getGame().menuState.setUIManagerActive(true);
                State.setState(handler.getGame().menuState);
            }
        }, "Return To Menu"));
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
        if (handler.getKeyManager().esc){
            if (hasGameStarted){
                handler.getGame().intermediateMenuState.setUIManagerActive(true);
                State.setState(handler.getGame().intermediateMenuState);
            }
            else{
                handler.getGame().menuState.setUIManagerActive(true);
                State.setState(handler.getGame().menuState);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        if (isUIManagerActive){
            uiManager.render(g);
        }
        drawControl(g, 10, 130);
        drawBulletProperties(g, 10, 270);
        drawChestProperties(g, 10, 360);
    }

    private void drawChestProperties(Graphics g, int x, int y) {
        //Chest Types
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 25);
        Font fnt2 = new Font("Comic Sans MS", Font.BOLD, 15);

        g.setColor(Color.CYAN);
        g.setFont(fnt1);
        g.drawString("Chest Types", x, y);

        g.setColor(Color.white);
        g.setFont(fnt2);
        g.drawString("Chest", x , y+35);
        g.setColor(Color.yellow);
        g.drawString("Coins", x, y+65);
        g.drawString("50   20   50", x + 80, y+65);

        g.setColor(Color.red);
        g.drawString("Health", x, y+85);
        g.drawString("-    30    -", x + 82, y+85);

        g.setColor(Color.green);
        g.drawString("Upgrade", x, y+105);
        g.drawString("no   no   yes", x + 82, y+105);

        Font fnt3 = new Font("Comic Sans MS", Font.BOLD, 10);
        g.setFont(fnt3);
        g.setColor(Color.MAGENTA);
        g.drawString("Beware that these chests can make enemy stronger as well!!!", x, y+125);

        g.drawImage(Assets.gold_chest, x + 70, y + 10, 32, 32, null);
        g.drawImage(Assets.health_chest, x + 110, y + 10,32, 32,  null);
        g.drawImage(Assets.upgrade_chest, x + 150, y + 10, 32, 32, null);
    }

    private void drawControl(Graphics g, int x , int y){
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 25);
        g.setColor(Color.CYAN);
        g.setFont(fnt1);
        g.drawString("Controls", x, y);

        Font fnt2 = new Font("Comic Sans MS", Font.BOLD, 15);
        g.setColor(Color.white);
        g.setFont(fnt2);
        g.drawString("w = move up", x, y+20);
        g.drawString("s = move down", x, y+40);
        g.drawString("d = move right", x, y+60);
        g.drawString("a = move left", x, y+80);
        g.drawString("space = shoot", x, y+100);

        g.drawLine(x, y+110, x+250, y+110);
    }

    private void drawBulletProperties(Graphics g, int x , int y){
        //Bullet Types
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 25);
        Font fnt2 = new Font("Comic Sans MS", Font.BOLD, 15);

        g.setColor(Color.CYAN);
        g.setFont(fnt1);
        g.drawString("Bullet Types", x, y);

        g.setColor(Color.white);
        g.setFont(fnt2);
        g.drawString("Bullet", x , y+25);
        g.drawImage(Assets.bullet_1[1], x+70, y+10, null);
        g.drawImage(Assets.bullet_2[1], x+100, y+10, null);
        g.drawImage(Assets.bullet_3[1], x+130, y+10, null);
        g.drawImage(Assets.bullet_4[1], x+160, y+10, null);
        g.drawImage(Assets.bullet_5[1], x+190, y+10, null);
        g.drawString("Damage   2   4   6   8   10", x , y+45);
        g.drawLine(x, y+55, x+250, y+55);

    }


    private void drawTankProperties(Graphics g, int x, int y, Entity_Types.Tank_Type tank_type){

        String health = Integer.toString(TankHealth(tank_type));
        String speed = Float.toString(TankSpeed(tank_type));
        String fire_rate = Integer.toString((int)TankAttackTime(tank_type));

        g.setColor(Color.red);
        g.drawString(health, x , y);
        g.setColor(Color.blue);
        g.drawString(speed, x , y + 30);
        g.setColor(Color.YELLOW);
        g.drawString(fire_rate, x , y + 60);
    }

    public UIManager getUiManager() {
        return uiManager;
    }
}
