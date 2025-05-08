package my_project.model;

import com.sun.javafx.geom.Vec2d;

public class Chunk {
    Vec2d gridPosition;
    Vec2d size;
    public Chunk(Vec2d gridPosition, Vec2d size) {
        this.gridPosition = gridPosition;
        this.size = size;
    }
}
