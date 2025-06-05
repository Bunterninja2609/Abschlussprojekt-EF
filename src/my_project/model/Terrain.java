package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramController;
import my_project.model.biomes.Biome;
import my_project.model.biomes.Ocean;
import my_project.model.blocks.*;
import my_project.model.blocks.Block;

import java.util.Random;

public class Terrain {
    private Chunk[][] chunks;
    private PerlinNoise noise;
    private PerlinNoise biomeNoise;
    private static double GROUNDHEIGHT = 40;

    private Random rand;
    public Terrain(Vec2d worldSize, int seed) {
        rand = new Random(seed);
        noise = new PerlinNoise(seed, 0.025, 4, 0.3, 3);
        biomeNoise = new PerlinNoise(seed + 1, 0.005, 6, 0.3, 3);
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
    public void update(double dt) {
        for (Chunk[] column : chunks) {
            for (Chunk chunk : column) {
                if (chunk.isLoaded()) {
                    chunk.update(dt);
                }
            }
        }
    }

    public Chunk getChunkByPosition(double x, double y) {
        int chunkX = (int) ProgramController.clamp( 0, chunks.length - 1,x / (Chunk.getSIZE().x * Block.getSIZE().x));
        int chunkY = (int) ProgramController.clamp( 0, chunks[0].length - 1,y / (Chunk.getSIZE().y * Block.getSIZE().y));

        return chunks[chunkX][chunkY];
    }
    public static double getGROUNDHEIGHT() {
        return GROUNDHEIGHT;
    }

    public Block generate(double x, double y) {
        double floorHeight = noise.getValue(x) * 30;
        double biomeHeight = biomeNoise.getValue(x) * 300;
        if (biomeNoise.getValue(x) < 0) {
            return Ocean.generate(noise, biomeNoise, x, y);
        }
        return Biome.generate(noise, biomeNoise, x, y);


    }
}