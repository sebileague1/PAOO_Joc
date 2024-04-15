package Tile;

import Game.Assets;

public class TreeTile extends Tile {

    public TreeTile(int id) {
        super(Assets.tree, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

    public boolean isNotTraverseble() {
        return true;
    }
}
