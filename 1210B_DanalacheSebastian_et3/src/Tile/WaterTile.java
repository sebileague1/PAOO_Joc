package Tile;

import Game.Assets;
public class WaterTile extends Tile {
    public WaterTile(int id) {
        super(Assets.water, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
