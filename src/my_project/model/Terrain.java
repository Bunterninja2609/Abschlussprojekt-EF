package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.blocks.*;
import my_project.model.blocks.Block;

public class Terrain {
    private Chunk[][] chunks;
    public Terrain(Vec2d worldSize, int seed) {
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
                chunk.draw(drawTool);
            }
        }
    }
    public Block generate(double x, double y) {
        //TODO spätere implementierung des Perlin Noise

        int block = (int)(Math.random()*3);
        if (y < 30){
            block = 0;
        } else if (y < 55 ){
            block = 1;
        } else if (y < 60 && Math.random()<0.6){
            block = 1;
        } else {
            block = 2;
        }
        switch (block){
            case 0:
                return new Air(new Vec2d(x, y));
            case 1:
                return new Dirt(new Vec2d(x, y));
            case 2:
                return new Stone(new Vec2d(x, y));
        }
        return new Air(new Vec2d(x, y));
    }

}