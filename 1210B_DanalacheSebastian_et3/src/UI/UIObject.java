package UI;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x, y;
    protected int height, width;
    protected Rectangle bounds;
    protected boolean hovering = false;
    public UIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if (bounds.contains(e.getX(), e.getY())){
            hovering = true;
        }
        else{
           hovering = false;
        }
    }

    public void onMouseRelease(MouseEvent e){
        if (hovering){
            onClick();
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
