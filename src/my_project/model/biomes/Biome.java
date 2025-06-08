package my_project.model.biomes;

import com.sun.javafx.geom.Vec2d;
import my_project.model.PerlinNoise;
import my_project.model.Terrain;
import my_project.model.blocks.*;

public class Biome {
    public static Block generate(PerlinNoise noise, PerlinNoise biomes, double x, double y) {
        int block = generateBasicTerrain(noise, biomes, x, y);
        block = alterCurrentBlocks(noise, biomes, x, y, block);
        return returnBlock(block, x, y);
    }
    protected static int generateBasicTerrain(PerlinNoise noise, PerlinNoise biomes, double x, double y) {
        if (y > Terrain.getGROUNDHEIGHT()) {
            return 2;
        } else if (y == Terrain.getGROUNDHEIGHT()) {
            return 6;
        }
        return 1;
    }
    protected static int alterCurrentBlocks(PerlinNoise noise, PerlinNoise biomes, double x, double y, int block){
        return block;
    }
    private static Block returnBlock(int block, double x, double y){
        switch (block){
            case 0:
                return new Debug(new Vec2d(x, y), 0);
            case 1:
                return new Air(new Vec2d(x, y));
            case 2:
                return new Dirt(new Vec2d(x, y));
            case 3:
                return new Stone(new Vec2d(x, y));
            case 4:
                return new Water(new Vec2d(x, y));
            case 5:
                return new Iron(new Vec2d(x, y));
            case 6:
                return new Grass(new Vec2d(x, y));
            default:
                return new Debug(new Vec2d(x, y), 0);
        }
    }
    private int getFloorHeight() {
        return 40;
    }

}
