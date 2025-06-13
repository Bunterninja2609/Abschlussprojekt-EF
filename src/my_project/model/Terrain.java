package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.BlockSpace;
import my_project.control.ProgramController;
import my_project.model.biomes.Biome;
import my_project.model.biomes.Ocean;
import my_project.model.blocks.Block;

import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    private Chunk[][] chunks;
    private PerlinNoise noise;
    private PerlinNoise biomeNoise;
    private ArrayList<Chunk> loadedChunks;
    private static double GROUNDHEIGHT = 64;

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
        loadedChunks = new ArrayList<>();
        for (int x = 0; x < worldSize.x; x++) {
            for (int y = 0; y < worldSize.y; y++) {
                chunks[x][y] = new Chunk(new Vec2d(x, y), this);
            }
        }
    }

    public void draw(DrawTool drawTool) {
        //System.out.println("Drawing Terrain");
        /*
        for (Chunk[] column : chunks) {
            for (Chunk chunk : column) {
                if (chunk.isLoaded()) {
                    chunk.draw(drawTool);
                }
            }
        }

         */
        for (Chunk chunk : loadedChunks) {
            //System.out.println("drawing chunk: ");
            chunk.draw(drawTool);
        }
    }
    public void update(double dt) {
        /*
        for (Chunk[] column : chunks) {
            for (Chunk chunk : column) {
                if (chunk.isLoaded()) {
                    chunk.update(dt);
                }
            }
        }
        */
        for (Chunk chunk : loadedChunks) {
            chunk.update(dt);
        }
    }

    public Chunk getChunkByPosition(double x, double y) {
        x = convertPositionToChunkGrid(x, y).x;
        y = convertPositionToChunkGrid(x, y).y;
        return getChunkByChunkGrid((int) x, (int) y);
    }
    public Chunk getChunkByBlockGrid(int x, int y){
        x = (int)convertBlockGridToChunkGrid(x, y).x;
        y = (int)convertBlockGridToChunkGrid(x, y).y;

        return getChunkByChunkGrid(x, y);
    }
    public Chunk getChunkByChunkGrid(int x, int y){
        //avoiding nullPointerExeption
        x = (int)ProgramController.clamp( 0, chunks.length - 1, x);
        y = (int)ProgramController.clamp( 0, chunks[0].length - 1, y);
        return chunks[x][y];
    }

    public Block getBlockByPosition(double x, double y) {
        x = convertPositionToBlockGrid(x, y).x;
        y = convertPositionToBlockGrid(x, y).y;
        return getBlockByBlockGrid((int) x, (int) y);
    }
    public Block getBlockByBlockGrid(int x, int y){
        Chunk chunk = getChunkByBlockGrid(x, y);
        if (chunk != null) {
            return chunk.getBlockByBlockGridPosition(x, y);
        }
        return null;
    }
    public Block getBlockByChunkGrid(int x, int y){
        x = (int)convertChunkGridToBlockGrid(x, y).x;
        y = (int)convertChunkGridToBlockGrid(x, y).y;
        return getBlockByBlockGrid(x, y);
    }
    /*
    public BlockSpace getBlockSpaceByPosition(double x, double y){
        x = convertPositionToBlockGrid(x, y).x;
        y = convertPositionToBlockGrid(x, y).y;
        return getBlockSpaceByBlockGrid((int) x, (int) y);
    }
    public BlockSpace getBlockSpaceByBlockGrid(int x, int y){
        
    }*/



    public static Vec2d convertChunkGridToBlockGrid(int x, int y){
        x = (int)(x * Chunk.getSIZE().x);
        y = (int)(y * Chunk.getSIZE().y);
        return new Vec2d(x, y);
    }
    public static Vec2d convertBlockGridToChunkGrid(int x, int y){
        x = (int)(x / Chunk.getSIZE().x);
        y = (int)(y / Chunk.getSIZE().y);
        return new Vec2d(x, y);
    }

    public static Vec2d convertPositionToChunkGrid(double x, double y){
        x = (int)(x / (Chunk.getSIZE().x * Block.getSIZE().x));
        y = (int)(y / (Chunk.getSIZE().y * Block.getSIZE().y));
        return new Vec2d(x, y);
    }
    public static Vec2d convertChunkGridToPosition(int x, int y){
        x = (int)(x * (Chunk.getSIZE().x * Block.getSIZE().x));
        y = (int)(y * (Chunk.getSIZE().y * Block.getSIZE().y));
        return new Vec2d(x, y);
    }

    public static Vec2d convertPositionToBlockGrid(double x, double y){
        x = (int)(x / Block.getSIZE().x);
        y = (int)(y / Block.getSIZE().y);
        return new Vec2d(x, y);
    }
    public static Vec2d convertBlockGridToPosition(int x, int y){
        x = (int)(x * Block.getSIZE().x);
        y = (int)(y * Block.getSIZE().y);
        return new Vec2d(x, y);
    }


    public Block generate(double x, double y) {
        double floorHeight = noise.getValue(x) * 30;
        double biomeHeight = biomeNoise.getValue(x) * 300;
        if (biomeNoise.getValue(x) < 0) {
            return Ocean.generate(noise, biomeNoise, x, y);
        }
        return Biome.generate(noise, biomeNoise, x, y);


    }


    public static double getGROUNDHEIGHT() {
        return GROUNDHEIGHT;
    }
    public void loadChunk(Chunk chunk) {

        if (!loadedChunks.contains(chunk)) {
            //System.out.println("loaded chunk");
            loadedChunks.add(chunk);
        }
    }
    public void unloadChunk(Chunk chunk) {
        if (loadedChunks.contains(chunk)) {
            //System.out.println("unaloaded chunk");
            loadedChunks.remove(chunk);
        }
    }
}