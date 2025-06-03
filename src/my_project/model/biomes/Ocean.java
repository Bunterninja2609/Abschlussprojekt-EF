package my_project.model.biomes;

import com.sun.javafx.geom.Vec2d;
import my_project.model.PerlinNoise;
import my_project.model.blocks.*;

public class Ocean extends Biome {

    public static Block generate(PerlinNoise noise, PerlinNoise elevation, double x, double y) {

        double isOre = noise.getValue(x * 8, y * 8);
        int block = 0;
        int waterHeight = 30;
        double floorHeight = noise.getValue(x) * 30 + elevation.getValue(x) * 30;
        //System.out.println("floorHeight: " + floorHeight);

        if (y < 30 + floorHeight) {
            block = 0;
        } else if (y < (40 + Math.pow(floorHeight, 3) *0.01) ) {
            block = 1;
        } else {
            block = 2;
        }

        if (block == 0 && y >= waterHeight) {
            block = 3;
        }
        if (block == 2 && isOre >= 0.3) {
            block = 4;
        }


        //return new Debug(new Vec2d(x, y), n);

        switch (block){
            case 0:
                return new Air(new Vec2d(x, y));
            case 1:
                return new Dirt(new Vec2d(x, y));
            case 2:
                return new Stone(new Vec2d(x, y));
            case 3:
                return new Water(new Vec2d(x, y));
            case 4:
                return new Iron(new Vec2d(x, y));
            default:
                return new Air(new Vec2d(x, y));
        }
    }
}
