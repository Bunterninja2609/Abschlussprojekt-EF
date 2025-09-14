package my_project.model.biomes;

import com.sun.javafx.geom.Vec2d;
import my_project.model.PerlinNoise;
import my_project.model.Terrain;
import my_project.model.blocks.*;

public class Mountains extends Biome {
    public static Block generate(PerlinNoise noise, PerlinNoise biomes, double x, double y, int height) {
        int block = generateBasicTerrain(noise, biomes, x, y);
        block = alterCurrentBlocks(noise, biomes, x, y, block);
        return returnBlock(block, x, y);
    }
    protected static int generateBasicTerrain(PerlinNoise noise, PerlinNoise biomes, double x, double y) {
        if (y > Terrain.getGroundheight(x, noise) && y < Terrain.getGroundheight(x, noise)*1.1+3) {
            return 2;
        } else if (y == Terrain.getGroundheight(x, noise)) {
            return 6;
        } else if (y > Terrain.getGroundheight(x, noise)*1.1+3) {
            return 3;
        }
        return 1;
    }
    protected static int alterCurrentBlocks(PerlinNoise noise, PerlinNoise biomes, double x, double y, int block){
        return block;
    }

    public static int getFloorHeight(double x, PerlinNoise noise) {
        return (int)(noise.getValue(x*2)*50+30);
    }
}
