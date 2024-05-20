package Tile;

import Game.Assets;
import java.awt.image.BufferedImage;

public class GrassTile extends Tile {

    public GrassTile(int id) {
        super(getAssetById(id), id);
    }

    private static BufferedImage getAssetById(int id){
        switch(id){
            case 6:
                return Assets.grass_2;
            case 7:
                return Assets.grass_3;
            default:
                return Assets.grass;
        }
    }

    @Override
    public boolean isSolid(){
        return false;
    }
}
