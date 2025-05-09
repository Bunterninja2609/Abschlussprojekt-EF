package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;

public class Terrain {
    Chunk[][] chunks;
    public Terrain(Vec2d chunkSize, int seed) {
        chunks = new Chunk[(int)chunkSize.x][(int)chunkSize.y];
    }

    public void draw(DrawTool drawTool) {
        for (Chunk[] row : chunks) {
            for (Chunk chunk : row) {
                chunk.draw(drawTool);
            }
        }
    }
}