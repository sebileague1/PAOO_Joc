package Entity.Dimensions;

import Entity.Types.Entity_Types;


public class Tank_Type_Bounds_Dimensions {

    public static int GetBoundsX(Entity_Types.Tank_Type tank_type){
        switch(tank_type){
            case tank_1:
            case tank_2:
            case tank_3:
            case tank_4:
                return 6;
            default:
                return 12;
        }
    }

    public static int GetBoundsY(Entity_Types.Tank_Type tank_type){
        switch(tank_type){

            case tank_1:
            case tank_2:
            case tank_3:
            case tank_4:
                return 5;
            default:
                return 6;
        }
    }

    public static int GetBoundsWidth(Entity_Types.Tank_Type tank_type){
        switch(tank_type){
            case tank_1:
            case tank_2:
            case tank_3:
            case tank_4:
                return 52;
            default:
                return 52;
        }
    }

    public static int GetBoundsHeight(Entity_Types.Tank_Type tank_type){
        switch(tank_type){
            case player_level_1:
            default:
                return 54;
        }
    }

}
