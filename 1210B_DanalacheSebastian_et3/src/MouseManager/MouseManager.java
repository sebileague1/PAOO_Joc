package MouseManager;
import Game.Handler;
import UI.UIManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    public boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;
    private Handler handler;

    public MouseManager(Handler handler){
        this.handler = handler;
    }

    public void setUIManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    public UIManager getUIManager(){
        return uiManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
        }
        else if (e.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
        }
        else if (e.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
        }

        if (uiManager != null){
            uiManager.onMouseRelease(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (uiManager != null){
            uiManager.onMouseMove(e);
        }
    }
}
