package Entity;

import Entity.Creature.Player;
import Entity.Creature.Shooter;
import Game.Handler;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;// ține evidența tuturor entităților din world
    protected int counter = 0;

    //pentru numărul de inamici
    public int getCounter() {
        return counter;
    }

    //comparator pentru a sorta intitatile in functie de pozitia lor pe axa y
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b){
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()){
                return -1;
            }
            return 1;
        }
    };

    public EntityManager(Handler handler){
        this.handler = handler;
        entities = new ArrayList<Entity>();
    }

    public void addPlayer(Player player){
        this.player = player;
        addEntity(player);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }


    //aici actualizeaza starea tuturor entitatilor din lista si daca nu e activa ii sa remove
    public void tick(){
        Iterator<Entity> it = entities.iterator();

        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if (!e.isActive()){
                if ((e instanceof Shooter) && !(e instanceof Player)){
                    counter--;
                }
                it.remove();
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g){
        for (Entity e : entities){
            e.render(g);
        }
    }
    //verifica daca entitatea este inamic sau player
    public void addEntity(Entity e){
        if ((e instanceof Shooter) && !(e instanceof Player)){
            counter++;
        }
        entities.add(e);
    }
}
