package Entity.Types;

import Game.Assets;
import java.awt.image.BufferedImage;

public class Entity_Types {

    public enum Tank_Type{
        tank_1, tank_2, tank_3, tank_4, player_level_1
    }

    public static Tank_Type GetTankByLevel(int level){
        switch(level){
            case 0:
                return Tank_Type.player_level_1;
            default:
                return Tank_Type.player_level_1;
        }
    }


    public static float TankSpeed(Tank_Type e){
        switch (e){
            case tank_1:
            case tank_3:
                return 2f;
            case tank_4:
                return 1.5f;
            case tank_2:
            case player_level_1:
                return 2f;
        }
        return 1f;
    }

    public static int TankHealth(Tank_Type e){
        switch (e){
            case tank_1:
                return 30;
            case tank_2:
                return 50;
            case tank_3:
                return 80;
            case tank_4:
            case player_level_1:
                return 100;
        }
        return 400;
    }


    public static BufferedImage[] TankImages(Tank_Type e){
        switch (e){
            case tank_1:
                return Assets.tank_1;
            case tank_2:
                return Assets.tank_2;
            case tank_3:
                return Assets.tank_3;
            case tank_4:
                return Assets.tank_4;
            case player_level_1:
                return Assets.player_level_1;
        }
        return Assets.tank_1;
    }
}

