package Entity.AnimationObjects;

import Game.Animation;
import Game.Assets;
import Game.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationObject {
    protected float x, y;
    protected Handler handler;
    protected boolean active = true;
    protected Animation explosion;
    protected final int const_speed = 5;
    protected int lifeTime;
    protected BufferedImage current_image;
    protected int image_width, image_height;
    private int offsetX, offsetY;


    public AnimationObject(Handler handler, float x, float y, int width, int height, int offsetX, int offsetY){
        this.x = x;
        this.y = y;
        this.image_width = width;
        this.image_height = height;
        this.handler = handler;
        this.lifeTime = Assets.bullet_explosion_animation.length;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        explosion = new Animation(const_speed, Assets.bullet_explosion_animation);
    }

    public void tick(){
        current_image = explosion.getCurrentFrame();
        explosion.tick();
        lifeTime--;
        if (lifeTime == 0){
            active = false;
        }
    }

    public void render(Graphics g){
        g.drawImage(current_image, (int) (x - handler.getGameCamera().getxOffset()+offsetX/2-image_width/2), (int) (y - handler.getGameCamera().getyOffset()+offsetX/2-image_height/2), image_width, image_height, null);
    }

    public boolean isActive() {
        return active;
    }

}
