package Game;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 64, height = 64;
    public static BufferedImage player, soil, grass, grass_2, grass_3, tree, water, mountain, heart, enemy_counter, number_of_coins, townGrass, townGrassDestroyed;
    public static BufferedImage[] tank_1, tank_2, tank_3, tank_4, player_level_1;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_spritesheet.png"));
        SpriteSheet tanks_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tank/spritesheet.png"));


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
    }
}
