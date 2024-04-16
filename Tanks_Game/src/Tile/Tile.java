package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile grassTile_2 = new GrassTile(6);
    public static Tile grassTile_3 = new GrassTile(7);
    public static Tile dirtTile = new SoilTile(1);
    public static Tile waterTile = new WaterTile(2);
    public static Tile treeTile = new TreeTile(3);
    public static Tile mountainTile = new MountainTile(4);
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}
