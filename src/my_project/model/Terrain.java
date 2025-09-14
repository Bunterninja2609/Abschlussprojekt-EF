package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramController;
import my_project.model.biomes.*;
import my_project.model.blocks.Block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    private Chunk[][] chunks;
    private static PerlinNoise noise;
    private static PerlinNoise biomeNoise;
    private ArrayList<Chunk> loadedChunks;

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
        for (Chunk chunk : loadedChunks) {
            chunk.drawBorder(drawTool);
        }
        for (Chunk chunk : loadedChunks) {
            chunk.draw(drawTool);
        }
    }

    public void update(double dt) {
        for (Chunk chunk : loadedChunks) {
            chunk.update(dt);
        }
        for (Chunk chunk : loadedChunks) {
            chunk.changeUpdatedStatus(false);
        }
    }
    public void updateOnTick() {
        for (Chunk chunk : loadedChunks) {
            chunk.updateOnTick();
        }
        for (Chunk chunk : loadedChunks) {
            chunk.changeTickUpdatedStatus(false);
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

    public BlockSpace getBlockSpaceByPosition(double x, double y){
        x = convertPositionToBlockGrid(x, y).x;
        y = convertPositionToBlockGrid(x, y).y;
        return getBlockSpaceByBlockGrid((int) x, (int) y);
    }
    public BlockSpace getBlockSpaceByBlockGrid(int x, int y){
        Chunk chunk = getChunkByBlockGrid(x, y);
        if (chunk != null) {
            return chunk.getBlockSpaceByBlockGridPosition(x, y);
        }
        return null;
    }



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
        int height = (int)ProgramController.extrapolate(10, 20, biomeNoise.getValue(x), "sine");

        if (biomeNoise.getValue(x) < 0) {
            return Plains.generate(noise, biomeNoise, x, y, height);
        }
        return Mountains.generate(noise, biomeNoise, x, y, height);


    }

    public static double getGroundheight(double x, PerlinNoise noise) {
        double flux = 0.1;
        if (ProgramController.isBetween(-1, 0-flux, biomeNoise.getValue(x))) {
            //plains
            return Plains.getFloorHeight(x, noise);
        }else if (ProgramController.isAt(0, flux, biomeNoise.getValue(x))) {
            //übergang plains zu mountains
            double mask = ((ProgramController.clamp(0-flux, 0+flux, biomeNoise.getValue(x))/flux)+1)/2;
            return (int) (ProgramController.extrapolate(Plains.getFloorHeight(x, noise), Mountains.getFloorHeight(x, noise), mask, "sine"));
        } else if (ProgramController.isBetween(0+flux, 1, biomeNoise.getValue(x))) {
            //mountains
            return Mountains.getFloorHeight(x, noise);
        } else return 0;
    }
    public void loadChunk(Chunk chunk) {

        if (!loadedChunks.contains(chunk)) {
            //System.out.println("loaded chunk");
            loadedChunks.add(chunk);
        }
    }
    public void unloadChunk(Chunk chunk) {
        if (loadedChunks.contains(chunk)) {
            //System.out.println("unloaded chunk");
            loadedChunks.remove(chunk);
        }
    }

    public void setBlock(int x, int y, Class<? extends Block> blockType) {
        getBlockSpaceByBlockGrid(x, y).setNewBlock(blockType);
    }
    public void setBlock(int x, int y, Block block) {
        getBlockSpaceByBlockGrid(x, y).setBlock(block);
    }
}