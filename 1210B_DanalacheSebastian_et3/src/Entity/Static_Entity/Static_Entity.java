package Entity.Static_Entity;

import Entity.Entity;
import Game.Handler;

public abstract class Static_Entity extends Entity {
    public Static_Entity(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }
}
