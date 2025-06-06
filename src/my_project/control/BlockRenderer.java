package my_project.control;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.Terrain;
import com.sun.javafx.geom.*;
import my_project.model.blocks.Block;

import java.util.ArrayList;

public class BlockRenderer {
    private Terrain terrain;
    public BlockRenderer() {
        terrain = new Terrain(new Vec2d(50, 50), (int)(Math.random()* 10000));
        System.out.println("BlockRenderer created");
    }
    public void draw(DrawTool drawTool) {
        terrain.draw(drawTool);
    }

    public void update(double dt) {
        terrain.update(dt);
    }
    public Terrain getTerrain() {
        return terrain;
    }
}
