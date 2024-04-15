package Tile;

import Game.Assets;

public class SoilTile extends Tile {

    public SoilTile(int id) {
        super(Assets.soil, id);
    }

    @Override
    public boolean isSolid(){
        return false;
    }
}
