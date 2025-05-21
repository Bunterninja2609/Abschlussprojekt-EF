package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramController;
import my_project.model.blocks.*;
import my_project.model.blocks.Block;

import java.util.Random;

public class Terrain {
    private Chunk[][] chunks;
    private PerlinNoise noise;
    private Random rand;
    public Terrain(Vec2d worldSize, int seed) {
        rand = new Random(seed);
        noise = new PerlinNoise(45, 0.025, 6, 0.3, 3);
        System.out.println("Terrain created");
        System.out.println("World Size: " + worldSize.x + " x " + worldSize.y + " Chunks");
        System.out.println("  > " + (worldSize.x * Chunk.SIZE.x) + " x " + (worldSize.y * Chunk.SIZE.y) + " Blocks");
        System.out.println("Seed: " + seed);
        chunks = new Chunk[(int)worldSize.x][(int)worldSize.y];
        for (int x = 0; x < worldSize.x; x++) {
            for (int y = 0; y < worldSize.y; y++) {
                chunks[x][y] = new Chunk(new Vec2d(x, y), this);
            }
        }
    }

    public void draw(DrawTool drawTool) {
        for (Chunk[] column : chunks) {
            for (Chunk chunk : column) {
                if (chunk.isLoaded()) {
                    chunk.draw(drawTool);
                }
            }
        }
    }
    public Chunk getChunkByPosition(double x, double y) {
        int chunkX = (int) ProgramController.clamp( 0, chunks.length - 1,x / (Chunk.getSIZE().x * Block.getSIZE().x));
        int chunkY = (int) ProgramController.clamp( 0, chunks[0].length - 1,y / (Chunk.getSIZE().y * Block.getSIZE().y));

        return chunks[chunkX][chunkY];
    }
    public Block generate(double x, double y) {
        //TODO spätere implementierung des Perlin Noise
        double floorHeight = noise.getValue(x) * 30;
        double isOre = noise.getValue(x * 8, y * 8);
        int block = 0;
        int waterHeight = 30;
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