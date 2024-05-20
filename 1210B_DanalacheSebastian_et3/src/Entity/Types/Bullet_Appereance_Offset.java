package Entity.Types;

public class Bullet_Appereance_Offset {
    public enum Bullet_Appereance_Types{
        middle, quarter, threequarter, one_seventeenth, six_seventeenth
    }

    public static int getNumarator(Bullet_Appereance_Types e){
        switch(e){
            case middle:
            case quarter:
            case one_seventeenth:
                return 1;
            case threequarter:
                return 3;
            case six_seventeenth:
                return 6;
        }
        return 0;
    }

    public static int getNumitor(Bullet_Appereance_Types e){
        switch(e){
            case middle:
                return 2;
            case quarter:
            case threequarter:
                return 4;
            case one_seventeenth:
            case six_seventeenth:
                return 7;
        }
        return 0;
    }
}
