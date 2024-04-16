package Entity;

import Entity.Creature.Enemy;
import Entity.Static_Entity.Town;
import Entity.Static_Entity.Tree;
import Game.Handler;
import static Entity.Types.Entity_Types.Tank_Type.*;

public class EntityFactory {
    private Handler handler;

    public EntityFactory(Handler handler){
        this.handler = handler;
    }

    public Entity ProduceEntity(int code, float x, float y){
        switch(code){
            case 5 :
                return new Town(handler, x, y);
            case 3 :
                return new Tree(handler, x, y);
            case -1 :
                return new Enemy(handler, x, y, tank_1);
            case -2 :
                return new Enemy(handler, x, y, tank_2);
            case -3 :
                return new Enemy(handler, x, y, tank_3);
            case -4 :
                return new Enemy(handler, x, y, tank_4);
            case -5 :
            default:
                return new Town(handler, x, y);
        }
    }
}
