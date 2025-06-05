package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Cage extends GraphicalObject {
    GraphicalObject parent;
    double distance;
    double thickness;
    Collider upCollider;
    Collider downCollider;
    Collider leftCollider;
    Collider rightCollider;
    public Cage(double x, double y, double width, double height, double distance, double thickness) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.distance = distance;
        this.thickness = thickness;
        createCollider();
    }
    public Cage(GraphicalObject parent, double distance, double thickness) {
        this.parent = parent;
        this.distance = distance;
        this.thickness = thickness;
        createCollider();
    }

    @Override
    public void update(double dt) {
        if (parent != null) {
            x = parent.getX();
            y = parent.getY();
            width = parent.getWidth();
            height = parent.getHeight();
        }
        upCollider.update(dt);
        downCollider.update(dt);
        leftCollider.update(dt);
        rightCollider.update(dt);
    }
    @Override
    public void draw(DrawTool drawTool) {
        upCollider.draw(drawTool);
        downCollider.draw(drawTool);
        leftCollider.draw(drawTool);
        rightCollider.draw(drawTool);
    }
    private void createCollider() {
        upCollider = new Collider(this, "up", distance, thickness);
        downCollider = new Collider(this, "down", distance, thickness);
        leftCollider = new Collider(this, "left", distance, thickness);
        rightCollider = new Collider(this, "right", distance, thickness);

    }
    public Collider getUpCollider() {
        return upCollider;
    }
    public Collider getDownCollider() {
        return downCollider;
    }
    public Collider getLeftCollider() {
        return leftCollider;
    }
    public Collider getRightCollider() {
        return rightCollider;
    }
}
