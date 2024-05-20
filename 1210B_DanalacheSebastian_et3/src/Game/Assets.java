package Game;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 64, height = 64;

    public static BufferedImage player, soil, grass, grass_2, grass_3, tree, water, mountain, heart, enemy_counter, number_of_coins, townGrass, townGrassDestroyed;
    public static BufferedImage gold_chest, health_chest, upgrade_chest;
    public static BufferedImage pikachu_happy, pikachu_sad;

    public static BufferedImage[] bullet_1, bullet_2, bullet_3, bullet_4, bullet_5;
    public static BufferedImage[][] robot;
    public static BufferedImage[] tank_1, tank_2, tank_3, tank_4, player_level_1, player_level_2;
    public static BufferedImage[] bullet_explosion_animation;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_spritesheet.png"));
        SpriteSheet robot_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tank/robot.png"));
        SpriteSheet tanks_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tank/spritesheet.png"));
        SpriteSheet explosion_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/bullet/explosion.png"));


        player = sheet.crop(width * 0, height * 0, width, height);
        soil = ImageLoader.loadImage("/textures/tiles/soil.png");
        tree = ImageLoader.loadImage("/textures/tiles/tree.png");
        water = ImageLoader.loadImage("/textures/tiles/water.png");
        grass = ImageLoader.loadImage("/textures/tiles/grass.png");
        grass_2 = ImageLoader.loadImage("/textures/tiles/grass_2.png");
        grass_3 = ImageLoader.loadImage("/textures/tiles/grass_3.png");

        townGrass = ImageLoader.loadImage("/textures/tiles/townGrass.png");
        townGrassDestroyed = ImageLoader.loadImage("/textures/tiles/townGrassDestroyed.png");
        mountain = ImageLoader.loadImage("/textures/tiles/mountain.png");
        heart = ImageLoader.loadImage("/textures/other/heart.png");
        enemy_counter = ImageLoader.loadImage("/textures/other/enemy.png");
        number_of_coins = ImageLoader.loadImage("/textures/other/coin.png");

        gold_chest = ImageLoader.loadImage("/textures/chest/gold_chest.png");
        health_chest = ImageLoader.loadImage("/textures/chest/health_chest.png");
        upgrade_chest = ImageLoader.loadImage("/textures/chest/upgrade_chest.png");

        pikachu_happy = ImageLoader.loadImage("/textures/state/pikachu_win.png");
        pikachu_sad = ImageLoader.loadImage("/textures/state/pikachu_sad.png");

        robot = new BufferedImage[4][16];
        for (int i = 0; i < robot.length; i++){
            for (int j = 0; j < robot[0].length; j++){
                robot[i][j] = robot_sheet.crop(width * j, height * 2*i, width, height);
            }
        }

        tank_1 = new BufferedImage[4];
        for (int i = 0; i < tank_1.length; i++){
            tank_1[i] = tanks_sheet.crop(width * (4 + i), height * 0, width, height);;

        }

        tank_2 = new BufferedImage[4];
        for (int i = 0; i < tank_2.length; i++){
            tank_2[i] = tanks_sheet.crop(width * i, height * 1, width, height);
        }

        tank_3 = new BufferedImage[4];
        for (int i = 0; i < tank_3.length; i++){
            tank_3[i] = tanks_sheet.crop(width * i, height * 0, width, height);
        }

        tank_4 = new BufferedImage[4];
        for (int i = 0; i < tank_4.length; i++){
            tank_4[i] = tanks_sheet.crop(width * (4 + i), height * 1, width, height);
        }

        player_level_1 = new BufferedImage[4];
        for (int i = 0; i < player_level_1.length; i++){
            player_level_1[i] = tanks_sheet.crop(2*width * i, 2*height * 1, 2*width, 2*height);
        }

        player_level_2 = new BufferedImage[4];
        for (int i = 0; i < player_level_2.length; i++){
            player_level_2[i] = tanks_sheet.crop(2*width * i, 2*height * 2, 2*width, 2*height);
        }

        bullet_1 = new BufferedImage[4];
        bullet_1[0] = ImageLoader.loadImage("/textures/bullet/bullet_1/bullet_1_up.png");
        bullet_1[1] = ImageLoader.loadImage("/textures/bullet/bullet_1/bullet_1_right.png");
        bullet_1[2] = ImageLoader.loadImage("/textures/bullet/bullet_1/bullet_1_down.png");
        bullet_1[3] = ImageLoader.loadImage("/textures/bullet/bullet_1/bullet_1_left.png");

        bullet_2 = new BufferedImage[4];
        bullet_2[0] = ImageLoader.loadImage("/textures/bullet/bullet_2/bullet_2_up.png");
        bullet_2[1] = ImageLoader.loadImage("/textures/bullet/bullet_2/bullet_2_right.png");
        bullet_2[2] = ImageLoader.loadImage("/textures/bullet/bullet_2/bullet_2_down.png");
        bullet_2[3] = ImageLoader.loadImage("/textures/bullet/bullet_2/bullet_2_left.png");

        bullet_3 = new BufferedImage[4];
        bullet_3[0] = ImageLoader.loadImage("/textures/bullet/bullet_3/bullet_3_up.png");
        bullet_3[1] = ImageLoader.loadImage("/textures/bullet/bullet_3/bullet_3_right.png");
        bullet_3[2] = ImageLoader.loadImage("/textures/bullet/bullet_3/bullet_3_down.png");
        bullet_3[3] = ImageLoader.loadImage("/textures/bullet/bullet_3/bullet_3_left.png");

        bullet_4 = new BufferedImage[4];
        bullet_4[0] = ImageLoader.loadImage("/textures/bullet/bullet_4/bullet_4_up.png");
        bullet_4[1] = ImageLoader.loadImage("/textures/bullet/bullet_4/bullet_4_right.png");
        bullet_4[2] = ImageLoader.loadImage("/textures/bullet/bullet_4/bullet_4_down.png");
        bullet_4[3] = ImageLoader.loadImage("/textures/bullet/bullet_4/bullet_4_left.png");

        bullet_5 = new BufferedImage[4];
        bullet_5[0] = ImageLoader.loadImage("/textures/bullet/bullet_5/bullet_5_up.png");
        bullet_5[1] = ImageLoader.loadImage("/textures/bullet/bullet_5/bullet_5_right.png");
        bullet_5[2] = ImageLoader.loadImage("/textures/bullet/bullet_5/bullet_5_down.png");
        bullet_5[3] = ImageLoader.loadImage("/textures/bullet/bullet_5/bullet_5_left.png");

        bullet_explosion_animation = new BufferedImage[16];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                bullet_explosion_animation[4*i+j] = explosion_sheet.crop(width * i, height * j, width, height);
            }
        }

    }
}
