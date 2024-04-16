package Tile;

import Game.Assets;

public class MountainTile extends Tile {

    public MountainTile(int id) {
        super(Assets.mountain, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

    public boolean isNotTraverseble() {
        return true;
    }
}
