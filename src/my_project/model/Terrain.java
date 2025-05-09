package my_project.model;

import com.sun.javafx.geom.Vec2d;

public class Terrain {
    Chunk[][] chunk;
    public Terrain(Vec2d chunkSize, int seed) {
        chunk = new Chunk[(int)chunkSize.x][(int)chunkSize.y];
    }
}