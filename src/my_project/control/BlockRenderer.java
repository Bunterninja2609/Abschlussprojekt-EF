package my_project.control;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.Terrain;

import java.util.ArrayList;

public class BlockRenderer {
    private Terrain terrain;
    public BlockRenderer() {
        terrain = new Terrain(new Vec2d(12, 8), (int)(Math.random()* 10000));
    }
    public void draw(DrawTool drawTool) {}
}
