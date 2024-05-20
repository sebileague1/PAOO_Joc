package Entity.Types;

import Game.Assets;
import java.awt.image.BufferedImage;

public class Entity_Types {

    public enum Tank_Type{
        tank_1, tank_2, tank_3, tank_4, player_level_1, player_level_2, robot
    }

    public static Tank_Type GetTankByLevel(int level){
        switch(level){
            case 0:
                return Tank_Type.player_level_1;
            default:
                return Tank_Type.player_level_2;
        }
    }

    public static Bullet_Types.Bullet_Type GetBulletType(Tank_Type tank_type) {
        switch (tank_type){
            case tank_1:
                return Bullet_Types.Bullet_Type.bullet_1;
            case player_level_1:
            case tank_2:
                return Bullet_Types.Bullet_Type.bullet_2;
            case tank_3:
                return Bullet_Types.Bullet_Type.bullet_3;
            case tank_4:
                return Bullet_Types.Bullet_Type.bullet_4;
            case player_level_2:
            case robot:
                return Bullet_Types.Bullet_Type.bullet_5;


        }
        return Bullet_Types.Bullet_Type.bullet_1;
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
            case player_level_2:
                return 2f;
            case robot:
                return 3.0f;
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
            case player_level_2:
                return 150;
            case player_level_1:
                return 100;
            case robot:
                return 350;
        }
        return 400;
    }

    public static int MoveCoolDown(Tank_Type e){
        switch (e){
            case robot:
                return 600;
            default:
                return 1800;
        }
    }

    public static long TankAttackTime(Tank_Type e){
        switch (e){
            case tank_1:
                return 500;
            case tank_2:
                return 550;
            case tank_3:
            case player_level_2:
                return 600;
            case tank_4:
                return 650;
            case player_level_1:
                return 300;
            case robot:
                return 400;
        }
        return 500;
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
            case player_level_2:
                return Assets.player_level_2;
        }
        return Assets.tank_1;
    }
}

