package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Terrain {
    private Chunk[][] chunks;
    public Terrain(Vec2d worldSize, int seed, Vec2d chunkSize) {

        chunks = new Chunk[(int)worldSize.x][(int)worldSize.y];
        for (int x = 0; x < worldSize.x; x++) {
            for (int y = 0; y < worldSize.y; y++) {
                chunks[x][y] = new Chunk(new Vec2d(x, y), new Vec2d(chunkSize.x, chunkSize.y));
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
}