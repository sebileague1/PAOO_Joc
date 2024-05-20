package Entity.Types;

import Game.Assets;
import java.awt.image.BufferedImage;

public class Bullet_Types {

    public enum Bullet_Type{
        bullet_1, bullet_2, bullet_3, bullet_4, bullet_5
    }

    public static float BulletSpeed(Bullet_Type e){
        switch (e){
            case bullet_1:
            case bullet_2:
            case bullet_3:
            case bullet_4:
                return 2.5f;
            case bullet_5:
                return 2f;
        }
        return 1f;
    }


    public static int FirePower(Bullet_Type e){
        switch (e){
            case bullet_1:
                return 2;
            case bullet_2:
                return 4;
            case bullet_3:
                return 6;
            case bullet_4:
                return 8;
            case bullet_5:
                return 10;
        }
        return 1;
    }

    public static BufferedImage[] BulletImages(Bullet_Type e){
        switch (e){
            case bullet_1:
                return Assets.bullet_1;
            case bullet_2:
                return Assets.bullet_2;
            case bullet_3:
                return Assets.bullet_3;
            case bullet_4:
                return Assets.bullet_4;
            case bullet_5:
                return Assets.bullet_5;

        }
        return Assets.tank_1;
    }
}

